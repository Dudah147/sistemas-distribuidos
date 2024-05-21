/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Empresa;
import entities.LoginEmpresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author dudam
 */
public class LoginEmpresaDAO {

    private EntityManager entityManager;

    public LoginEmpresaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public LoginEmpresa findLoginByEmpresa(Empresa empresa) {
        try {
            TypedQuery<LoginEmpresa> query = entityManager.createQuery(
                    "SELECT c FROM LoginEmpresa c WHERE c.empresa = :empresa", LoginEmpresa.class
            );
            query.setParameter("empresa", empresa);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public LoginEmpresa findEmpresaByToken(String token) {
        try {
            TypedQuery<LoginEmpresa> query = entityManager.createQuery(
                    "SELECT c FROM LoginEmpresa c WHERE c.token = :token", LoginEmpresa.class
            );
            query.setParameter("token", token);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void createLoginEmpresa(LoginEmpresa loginEmpresa) {
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
        }
        
        try {
            entityManager.persist(loginEmpresa);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
            
            throw ex;
        }
    }

    public boolean deleteLoginEmpresa(String token) {
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
        } catch (Exception e) {
        }
        try {

            LoginEmpresa loginEmpresa = this.findEmpresaByToken(token);
            if (loginEmpresa != null) {
                entityManager.remove(loginEmpresa);
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
