/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class Routes {
    public static String handleRequest(String request){
        JSONObject jsonRequest = new JSONObject(request);
        return switch (jsonRequest.getString("operacao")) {
            case "cadastrarCandidato" -> "CREATE CANDIDATO";
            case "atualizarCandidato" -> "UPDATE CANDIDATO";
            default -> "ROTA NÃO ENCONTRADA";
        };
    }
}
