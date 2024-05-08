/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.CandidatoController;
import controller.LoginController;
import jakarta.persistence.EntityManager;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class Routes {
    private JSONObject request;
    
    private CandidatoController candidatoController;
    private LoginController loginController;
    
    public Routes(String request, EntityManager em){
        this.request = new JSONObject(request);
        this.candidatoController = new CandidatoController(em, this.request);
        this.loginController = new LoginController(em, this.request);
    }
    
    public String handleRequest(){
        try{
            return switch (request.getString("operacao")) {
                // --> Rotas CANDIDATO
                case "cadastrarCandidato" ->
                    this.candidatoController.cadastrarCandidato();
                case "visualizarCandidato" ->
                    this.candidatoController.visualizarCandidato();
                case "atualizarCandidato" ->
                    this.candidatoController.atualizarCandidato();
                case "apagarCandidato" ->
                    this.candidatoController.apagarCandidato();

                // --> Rotas para LOGIN
                case "loginCandidato" ->
                    this.loginController.loginCandidato();
                case "logout" ->
                    this.loginController.logout();

                default ->
                    "OPERACAO '" + request.getString("operacao") +"' NÃO ENCONTRADA";
            };
        } catch (JSONException ex) {
            return "Chave 'operacao' não encontrada";
        }
    }
}
