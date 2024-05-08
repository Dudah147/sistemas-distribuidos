/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CandidatoDAO;
import helpers.FormValidator;
import jakarta.persistence.EntityManager;
import entities.Candidato;
import org.json.JSONObject;
import java.util.UUID;

/**
 *
 * @author dudam
 */
public class CandidatoController {

    private EntityManager em;
    private JSONObject request;
    private CandidatoDAO candidatoDAO;

    public CandidatoController(EntityManager em, JSONObject request) {
        this.em = em;
        this.request = request;
        this.candidatoDAO = new CandidatoDAO(em);
    }

    public String cadastrarCandidato() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email", "nome", "senha");
        if (!hasKeys) {
            responseJson.put("operacao", "cadastrarCandidato");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "cadastrarCandidato")).equals("OK")) {
            return response;
        }

        // Valida nome
        if (!(response = FormValidator.checkNome(this.request, "cadastrarCandidato")).equals("OK")) {
            return response;
        }

        // Valida senha
        if (!(response = FormValidator.checkSenha(this.request, "cadastrarCandidato")).equals("OK")) {
            return response;
        }

        // Envia dados ao banco
        Candidato isCandidato = this.candidatoDAO.findCandidatoByEmail(this.request.getString("email"));
        if (isCandidato == null) {
            Candidato newCandidato = new Candidato();
            newCandidato.setEmail(this.request.getString("email"));
            newCandidato.setNome(this.request.getString("nome"));
            newCandidato.setSenha(this.request.getString("senha"));

            try {
                this.candidatoDAO.createCandidato(newCandidato);
                String uuid = UUID.randomUUID().toString();

                responseJson.put("operacao", "cadastrarCandidato");
                responseJson.put("status", 201);
                responseJson.put("token", uuid);

                return responseJson.toString();
            } catch (Exception ex) {
                responseJson.put("operacao", "cadastrarCandidato");
                responseJson.put("status", 404);
                responseJson.put("mensagem", "Erro ao tentar cadastrar Candidato");

                return responseJson.toString();
            }

        } else {
            responseJson.put("operacao", "cadastrarCandidato");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "E-mail já cadastrado");

            return responseJson.toString();
        }
    }

    public String visualizarCandidato() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email");
        if (!hasKeys) {
            responseJson.put("operacao", "visualizarCandidato");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "visualizarCandidato")).equals("OK")) {
            return response;
        }

        // Find no banco
        Candidato candidato = this.candidatoDAO.findCandidatoByEmail(this.request.getString("email"));
        if (candidato == null) {
            responseJson.put("operacao", "visualizarCandidato");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Email não encontrado");

            return responseJson.toString();
        }

        responseJson.put("operacao", "visualizarCandidato");
        responseJson.put("status", 201);
        responseJson.put("nome", candidato.getNome());
        responseJson.put("senha", candidato.getSenha());

        return responseJson.toString();
    }

    public String atualizarCandidato() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email", "nome", "senha");
        if (!hasKeys) {
            responseJson.put("operacao", "atualizarCandidato");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "atualizarCandidato")).equals("OK")) {
            return response;
        }

        // Valida nome
        if (!(response = FormValidator.checkNome(this.request, "atualizarCandidato")).equals("OK")) {
            return response;
        }

        // Valida senha
        if (!(response = FormValidator.checkSenha(this.request, "atualizarCandidato")).equals("OK")) {
            return response;
        }

        // Envia dados ao banco
        boolean success = this.candidatoDAO.updateCandidato(this.request.getString("email"), this.request.getString("senha"), this.request.getString("nome"));
        if (success) {
            responseJson.put("operacao", "atualizarCandidato");
            responseJson.put("status", 201);

            return responseJson.toString();
        } else {
            responseJson.put("operacao", "atualizarCandidato");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "E-mail não encontrado");

            return responseJson.toString();
        }
    }

    public String apagarCandidato() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email");
        if (!hasKeys) {
            responseJson.put("operacao", "apagarCandidato");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "apagarCandidato")).equals("OK")) {
            return response;
        }
        
        // Envia dados ao banco
        boolean success = this.candidatoDAO.deleteCandidatoByEmail(this.request.getString("email"));
        if (success) {
            responseJson.put("operacao", "apagarCandidato");
            responseJson.put("status", 201);

            return responseJson.toString();
        } else {
            responseJson.put("operacao", "apagarCandidato");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "E-mail não encontrado");

            return responseJson.toString();
        }
    }
}
