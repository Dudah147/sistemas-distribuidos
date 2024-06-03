/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.CompetenciaExperiencia;
import entities.Candidato;
import helpers.CompetenciaExperienciaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class CompetenciaExperienciaDAO {

    private EntityManager entityManager;
    private CandidatoDAO candidatoDAO;

    public CompetenciaExperienciaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.candidatoDAO = new CandidatoDAO(entityManager);
    }

    public List<CompetenciaExperienciaDTO> findCompetenciaExperienciaByCandidato(Candidato candidato) {

        try {
            TypedQuery<CompetenciaExperienciaDTO> query = this.entityManager.createQuery(
                "SELECT new CompetenciaExperienciaDTO(ce.competencia, ce.experiencia) FROM CompetenciaExperiencia ce WHERE ce.candidato = :candidato",
                CompetenciaExperienciaDTO.class
            );
            query.setParameter("candidato", candidato);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String createCompetenciaExperiencia(JSONArray competenciasArray, Candidato candidato) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            for (int i = 0; i < competenciasArray.length(); i++) {
                JSONObject competenciaJSON = competenciasArray.getJSONObject(i);
                CompetenciaExperiencia compExp = new CompetenciaExperiencia();
                compExp.setCandidato(candidato);
                compExp.setExperiencia(competenciaJSON.getInt("experiencia"));
                compExp.setCompetencia(competenciaJSON.getString("competencia"));

                entityManager.persist(compExp);

            }

            transaction.commit();
            return "OK";
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return ex.getMessage();
        }
    }
}
