/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Candidato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author dudam
 */
public class CandidatoDAO {

    private EntityManager entityManager;

    public CandidatoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Candidato findCandidatoByEmail(String email) {
        try {
            TypedQuery<Candidato> query = entityManager.createQuery(
                    "SELECT c FROM Candidato c WHERE c.email = :email", Candidato.class
            );
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("ERRO AO ENCONTRAR CANDIDATO");
            return null;
        }
    }

    public void createCandidato(Candidato candidato) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(candidato);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public boolean updateCandidato(String email, String newPassword, String newUsername) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
            System.out.println("CONEXÃO JÁ ESTABELECIDA");
        }
        try {
            Candidato candidato = this.findCandidatoByEmail(email);

            if (candidato != null) {
                candidato.setSenha(newPassword);
                candidato.setNome(newUsername);
                entityManager.merge(candidato);
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

    public boolean deleteCandidatoByEmail(String email) {
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
            System.out.println("CONEXÃO JÁ ESTABELECIDA");
        }
        try {

            Candidato candidato = this.findCandidatoByEmail(email);
            if (candidato != null) {
                entityManager.remove(candidato);
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
