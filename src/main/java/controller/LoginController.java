/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CandidatoDAO;
import DAO.EmpresaDAO;
import DAO.LoginCandidatoDAO;
import DAO.LoginEmpresaDAO;
import entities.Candidato;
import entities.Empresa;
import entities.LoginCandidato;
import entities.LoginEmpresa;
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
    private CandidatoDAO candidatoDAO;
    private EmpresaDAO empresaDAO;
    private LoginCandidatoDAO loginCandidatoDAO;
    private LoginEmpresaDAO loginEmpresaDAO;
    private String usuario;
    
    public LoginController(EntityManager em) {
        this.em = em;
        this.candidatoDAO = new CandidatoDAO(em);
        this.empresaDAO = new EmpresaDAO(em);
        this.loginCandidatoDAO = new LoginCandidatoDAO(em);
        this.loginEmpresaDAO = new LoginEmpresaDAO(em);
    }

    public String loginCandidato(JSONObject request) {
        this.usuario = "Candidato";
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "senha");
        if (!hasKeys) {
            responseJson.put("operacao", "loginCandidato");
            responseJson.put("status", 401);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "loginCandidato")).equals("OK")) {
            return response;
        }

        Candidato candidatoCredentials = this.candidatoDAO.findCandidatoByEmail(request.getString("email"));

        if (candidatoCredentials != null) {
            try {

                if (candidatoCredentials.getSenha().equals(request.getString("senha"))) {
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

    public String loginEmpresa(JSONObject request) {
        this.usuario = "Empresa";
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "senha");
        if (!hasKeys) {
            responseJson.put("operacao", "loginEmpresa");
            responseJson.put("status", 401);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "loginEmpresa")).equals("OK")) {
            return response;
        }

        Empresa empresaCredentials = this.empresaDAO.findEmpresaByEmail(request.getString("email"));

        if (empresaCredentials != null) {
            try {

                if (empresaCredentials.getSenha().equals(request.getString("senha"))) {
                    LoginEmpresa loginEmpresa;
                    if ((loginEmpresa = this.loginEmpresaDAO.findLoginByEmpresa(empresaCredentials)) != null) {
                        responseJson.put("operacao", "loginEmpresa");
                        responseJson.put("status", 200);
                        responseJson.put("token", loginEmpresa.getToken());

                        return responseJson.toString();
                    }

                    String uuid = UUID.randomUUID().toString();

                    loginEmpresa = new LoginEmpresa();
                    loginEmpresa.setEmpresa(empresaCredentials);
                    loginEmpresa.setToken(uuid);

                    this.loginEmpresaDAO.createLoginEmpresa(loginEmpresa);

                    responseJson.put("operacao", "loginEmpresa");
                    responseJson.put("status", 200);
                    responseJson.put("token", uuid);

                    return responseJson.toString();

                }
            } catch (JSONException ex) {
                responseJson.put("operacao", "loginEmpresa");
                responseJson.put("status", 401);
                responseJson.put("mensagem", "Senha deve ser numérica");

                return responseJson.toString();
            } catch (Exception ex) {
                responseJson.put("operacao", "loginEmpresa");
                responseJson.put("status", 401);
                responseJson.put("mensagem", "Erro ao tentar cadastrar Login Empresa");

                return responseJson.toString();
            }

        }
        responseJson.put("operacao", "loginEmpresa");
        responseJson.put("status", 401);
        responseJson.put("mensagem", "Login ou senha incorretos");

        return responseJson.toString();
    }
    // TODO: logoutEmpresa
    public String logout(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "token");
        if (!hasKeys) {
            responseJson.put("operacao", "logout");
            responseJson.put("status", 401);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }
           
        boolean success;
        if(this.usuario.equals("Candidato")) success = this.loginCandidatoDAO.deleteLoginCandidato(request.getString("token"));
        else success = this.loginEmpresaDAO.deleteLoginEmpresa(request.getString("token"));
        
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
