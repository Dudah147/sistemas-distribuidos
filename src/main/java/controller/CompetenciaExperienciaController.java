/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entities.*;
import DAO.CandidatoDAO;
import DAO.CompetenciaExperienciaDAO;
import helpers.CompetenciaExperienciaDTO;
import jakarta.persistence.EntityManager;
import org.json.JSONObject;
import helpers.FormValidator;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author dudam
 */
public class CompetenciaExperienciaController {

    private EntityManager em;
    private CandidatoDAO candidatoDAO;
    private CompetenciaExperienciaDAO competenciaExperienciaDAO;

    public CompetenciaExperienciaController(EntityManager em) {
        this.em = em;
        this.candidatoDAO = new CandidatoDAO(em);
        this.competenciaExperienciaDAO = new CompetenciaExperienciaDAO(em);
    }

    public String cadastrar(JSONObject request) {
        JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "competenciaExperiencia", "token");
        if (!hasKeys) {
            responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "cadastrarCompetenciaExperiencia")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.candidatoDAO.validToken(request)).equals("OK")) {
            return response;
        }

        // Validar se o email tem candidato
        Candidato candidato = this.candidatoDAO.findCandidatoByEmail(request.getString("email"));

        if (candidato == null) {
            responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Candidato não encontrado");

            return responseJson.toString();
        }

        // Cadastra as competências
        try {
            JSONArray competenciasArray = request.getJSONArray("competenciaExperiencia");
            String resp = this.competenciaExperienciaDAO.createCompetenciaExperiencia(competenciasArray, candidato);

            if (!resp.equals("OK")) {
                responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
                responseJson.put("status", 422);
                responseJson.put("mensagem", resp);

                return responseJson.toString();
            }

            responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
            responseJson.put("status", 201);
            responseJson.put("mensagem", "Competencia/Experiencia cadastrada com sucesso");
            return responseJson.toString();

        } catch (Exception e) {
            responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Erro ao resgatar Competencias");

            return responseJson.toString();
        }
    }

    public String visualizar(JSONObject request) {
        JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token");
        if (!hasKeys) {
            responseJson.put("operacao", "visualizarCompetenciaExperiencia");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "visualizarCompetenciaExperiencia")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.candidatoDAO.validToken(request)).equals("OK")) {
            return response;
        }

        // Validar se o email tem candidato
        Candidato candidato = this.candidatoDAO.findCandidatoByEmail(request.getString("email"));

        if (candidato == null) {
            responseJson.put("operacao", "visualizarCompetenciaExperiencia");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Candidato não encontrado");

            return responseJson.toString();
        }

        List<CompetenciaExperienciaDTO> competencias = this.competenciaExperienciaDAO.findCompetenciaExperienciaByCandidato(candidato);
        if (competencias == null) {
            responseJson.put("operacao", "visualizarCompetenciaExperiencia");
            responseJson.put("status", 201);
            responseJson.put("competenciaExperiencia", new JSONArray());
            return responseJson.toString();
        }
        responseJson.put("operacao", "visualizarCompetenciaExperiencia");
        responseJson.put("status", 201);
        responseJson.put("competenciaExperiencia", competencias);
        return responseJson.toString();
    }

    public String apagar(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        return responseJson.toString();
    }

    public String atualizar(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        return responseJson.toString();
    }
}
