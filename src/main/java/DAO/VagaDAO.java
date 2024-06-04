/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Empresa;
import entities.Vaga;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author dudam
 */
public class VagaDAO {

    private EntityManager entityManager;

    public VagaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Vaga findVagaByID(int idVaga) {
        try {
            TypedQuery<Vaga> query = entityManager.createQuery(
                    "SELECT v FROM Vaga v WHERE v.idVaga = :idVaga", Vaga.class
            );
            query.setParameter("idVaga", idVaga);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Vaga> findVagasByEmpresa(Empresa empresa) {
        try {
            TypedQuery<Vaga> query = entityManager.createQuery(
                    "SELECT v FROM Vaga v WHERE v.empresa = :empresa", Vaga.class
            );
            query.setParameter("empresa", empresa);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createVaga(Vaga vaga) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
        }
        try {
            entityManager.persist(vaga);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public String updateVaga(int idVaga, Empresa empresa, String nome, String descricao, String estado, float faixaSalarial) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
        }
        try {
            Vaga vaga = this.findVagaByID(idVaga);

            if (vaga != null) {
                vaga.setEmpresa(empresa);
                vaga.setNome(nome);
                vaga.setDescricao(descricao);
                vaga.setEstado(estado);
                vaga.setFaixaSalarial(faixaSalarial);
                entityManager.merge(empresa);
                transaction.commit();
                return "OK";
            } else {
                return "Vaga não encontrada";
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return e.getMessage();
        }
    }

    public String deleteVagaById(int idVaga) {
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
        }
        try {

            Vaga vaga = this.findVagaByID(idVaga);
            if (vaga != null) {
                entityManager.remove(vaga);
                transaction.commit();
                return "OK";
            } else {
                 return "Vaga não encontrada";
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return e.getMessage();
        }
    }
    
}
