/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author dudam
 */
public class EmpresaDAO {

    private EntityManager entityManager;

    public EmpresaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Empresa findEmpresaByEmail(String email) {
        try {
            TypedQuery<Empresa> query = entityManager.createQuery(
                    "SELECT c FROM Empresa c WHERE c.email = :email", Empresa.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Empresa findEmpresaByCnpj(String cnpj) {
        try {
            TypedQuery<Empresa> query = entityManager.createQuery(
                    "SELECT c FROM Empresa c WHERE c.cnpj = :cnpj", Empresa.class
            );
            query.setParameter("cnpj", cnpj);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createEmpresa(Empresa empresa) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(empresa);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public boolean updateEmpresa(String email, String newPassword, String newRazaoSocial, String newCnpj, String newDescricao, String newRamo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
        }
        try {
            Empresa empresa = this.findEmpresaByEmail(email);

            if (empresa != null) {
                empresa.setSenha(newPassword);
                empresa.setRazaoSocial(newRazaoSocial);
                empresa.setCnpj(newCnpj);
                empresa.setDescricao(newDescricao);
                empresa.setRamo(newRamo);
                entityManager.merge(empresa);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmpresaByEmail(String email) {
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
        }
        try {

            Empresa empresa = this.findEmpresaByEmail(email);
            if (empresa != null) {
                entityManager.remove(empresa);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
