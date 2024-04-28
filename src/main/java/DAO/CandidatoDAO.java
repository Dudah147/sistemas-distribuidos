/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Candidato;
import jakarta.persistence.EntityManager;
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
        try{
            
            entityManager.getTransaction().begin();
            entityManager.persist(candidato);
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            System.out.println("ERRO AO TENTAR CADASTRAR USUARIO: " + ex.getMessage());
            throw ex;
        }
    }
}
