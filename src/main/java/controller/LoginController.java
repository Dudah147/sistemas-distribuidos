/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CandidatoDAO;
import DAO.LoginCandidatoDAO;
import entities.Candidato;
import entities.LoginCandidato;
import jakarta.persistence.EntityManager;
import org.json.JSONObject;
import helpers.FormValidator;
import java.sql.SQLException;
import java.util.UUID;
import org.json.JSONException;

/**
 *
 * @author dudam
 */
public class LoginController {

    private EntityManager em;
    private JSONObject request;
    private CandidatoDAO candidatoDAO;
    private LoginCandidatoDAO loginCandidatoDAO;

    public LoginController(EntityManager em, JSONObject request) {
        this.em = em;
        this.request = request;
        this.candidatoDAO = new CandidatoDAO(em);
        this.loginCandidatoDAO = new LoginCandidatoDAO(em);
    }

    public String loginCandidato() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email", "senha");
        if (!hasKeys) {
            responseJson.put("operacao", "loginCandidato");
            responseJson.put("status", 401);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "loginCandidato")).equals("OK")) {
            return response;
        }

        Candidato candidatoCredentials = this.candidatoDAO.findCandidatoByEmail(this.request.getString("email"));

        if (candidatoCredentials != null) {
            try {

                if (candidatoCredentials.getSenha().equals(this.request.getString("senha"))) {
                    LoginCandidato loginCandidato;
                    if ((loginCandidato = this.loginCandidatoDAO.findLoginByCandidato(candidatoCredentials)) != null) {
                        responseJson.put("operacao", "loginCandidato");
                        responseJson.put("status", 200);
                        responseJson.put("token", loginCandidato.getToken());

                        return responseJson.toString();
                    }

                    String uuid = UUID.randomUUID().toString();

                    loginCandidato = new LoginCandidato();
                    loginCandidato.setCandidato(candidatoCredentials);
                    loginCandidato.setToken(uuid);

                    this.loginCandidatoDAO.createLoginCandidato(loginCandidato);

                    responseJson.put("operacao", "loginCandidato");
                    responseJson.put("status", 200);
                    responseJson.put("token", uuid);

                    return responseJson.toString();

                }
            } catch (JSONException ex) {
                responseJson.put("operacao", "loginCandidato");
                responseJson.put("status", 401);
                responseJson.put("mensagem", "Senha deve ser numérica");

                return responseJson.toString();
            } catch (Exception ex) {
                responseJson.put("operacao", "loginCandidato");
                responseJson.put("status", 401);
                responseJson.put("mensagem", "Erro ao tentar cadastrar Login Candidato");

                return responseJson.toString();
            }

        }
        responseJson.put("operacao", "loginCandidato");
        responseJson.put("status", 401);
        responseJson.put("mensagem", "Login ou senha incorretos");

        return responseJson.toString();
    }

    public String loginEmpresa() {
        return "";
    }

    public String logout() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "token");
        if (!hasKeys) {
            responseJson.put("operacao", "logout");
            responseJson.put("status", 401);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        boolean success = this.loginCandidatoDAO.deleteLoginCandidato(this.request.getString("token"));
        if (success) {
            responseJson.put("operacao", "logout");
            responseJson.put("status", 204);

            return responseJson.toString();
        } else {
            responseJson.put("operacao", "logout");
            responseJson.put("status", 401);
            responseJson.put("mensagem", "Token inválido");

            return responseJson.toString();
        }
    }
}
