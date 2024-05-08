/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Candidato;
import entities.LoginCandidato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author dudam
 */
public class LoginCandidatoDAO {

    private EntityManager entityManager;

    public LoginCandidatoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public LoginCandidato findLoginByCandidato(Candidato candidato) {
        try {
            TypedQuery<LoginCandidato> query = entityManager.createQuery(
                    "SELECT c FROM LoginCandidato c WHERE c.candidato = :candidato", LoginCandidato.class
            );
            query.setParameter("candidato", candidato);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("ERRO AO ENCONTRAR LOGIN CANDIDATO");
            return null;
        }
    }
    
    public LoginCandidato findCandidatoByToken(String token) {
        try {
            TypedQuery<LoginCandidato> query = entityManager.createQuery(
                    "SELECT c FROM LoginCandidato c WHERE c.token = :token", LoginCandidato.class
            );
            query.setParameter("token", token);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("ERRO AO ENCONTRAR LOGIN CANDIDATO");
            return null;
        }
    }

    public void createLoginCandidato(LoginCandidato loginCandidato) {
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
            System.out.println("CONEXÃO JÁ ESTABELECIDA");
        }
        
        try {
            entityManager.persist(loginCandidato);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
            
            throw ex;
        }
    }

    public boolean deleteLoginCandidato(String token) {
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
            System.out.println("CONEXÃO JÁ ESTABELECIDA");
        }
        try {

            LoginCandidato loginCandidato = this.findCandidatoByToken(token);
            if (loginCandidato != null) {
                entityManager.remove(loginCandidato);
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
