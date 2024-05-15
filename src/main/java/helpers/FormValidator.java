/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class FormValidator {

    public static String checkEmail(JSONObject request, String operacao) {
        JSONObject responseJson = new JSONObject();

        try {
            if (!isValidEmail(request.getString("email"))) {
                responseJson.put("operacao", operacao);
                responseJson.put("status", 404);
                responseJson.put("mensagem", "Email inválido");

                return responseJson.toString();
            }
        } catch (JSONException ex) {
            responseJson.put("operacao", operacao);
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Email deve ser uma string");

            return responseJson.toString();
        }

        return "OK";
    }

    public static String checkNome(JSONObject request, String operacao) {
        JSONObject responseJson = new JSONObject();

        try {
            if (request.getString("nome") == null || request.getString("nome").length() < 6 || request.getString("nome").length() > 30) {
                responseJson.put("operacao", operacao);
                responseJson.put("status", 404);
                responseJson.put("mensagem", "Nome do usuário inválido");

                return responseJson.toString();
            }
        } catch (JSONException ex) {
            responseJson.put("operacao", operacao);
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Nome deve ser uma string");

            return responseJson.toString();
        }

        return "OK";
    }

    public static String checkSenha(JSONObject request, String operacao) {
        JSONObject responseJson = new JSONObject();

        try {
            String senha = String.valueOf(request.getString("senha"));
            System.out.println(senha.length());
            if (senha == null || senha.length() < 3 || senha.length() > 8) {
                responseJson.put("operacao", operacao);
                responseJson.put("status", 404);
                responseJson.put("mensagem", "Senha deve ser de 3 a 8 caracteres");

                return responseJson.toString();
            }
        } catch (JSONException ex) {
            responseJson.put("operacao", operacao);
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Senha deve ser String");

            return responseJson.toString();
        }

        return "OK";
    }

    public static boolean checkKeys(JSONObject request, String... keys) {
        for (String key : keys) {
            if (!request.has(key)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.length() < 7 || email.length() > 50) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
