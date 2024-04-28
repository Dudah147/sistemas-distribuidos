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

    public CandidatoController(EntityManager em, JSONObject request) {
        this.em = em;
        this.request = request;
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
        if (!(response = FormValidator.checkEmail(this.request)).equals("OK")) {
            return response;
        }

        // Valida nome
        if (!(response = FormValidator.checkNome(this.request)).equals("OK")) {
            return response;
        }

        // Valida senha
        if (!(response = FormValidator.checkSenha(this.request)).equals("OK")) {
            return response;
        }

        // Envia dados ao banco
        CandidatoDAO candidatoDAO = new CandidatoDAO(this.em);
        Candidato isCandidato = candidatoDAO.findCandidatoByEmail(this.request.getString("email"));
        if (isCandidato == null) {
            Candidato newCandidato = new Candidato();
            newCandidato.setEmail(this.request.getString("email"));
            newCandidato.setNome(this.request.getString("nome"));
            newCandidato.setSenha(this.request.getInt("senha"));

            try {
                candidatoDAO.createCandidato(newCandidato);
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
        return "";
    }

    public String atualizarCandidato() {
        return "";
    }

    public String apagarCandidato() {
        return "";
    }
}
