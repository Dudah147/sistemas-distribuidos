/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.EmpresaDAO;
import helpers.FormValidator;
import jakarta.persistence.EntityManager;
import entities.Empresa;
import org.json.JSONObject;
import java.util.UUID;

/**
 *
 * @author dudam
 */
public class EmpresaController {

    private EmpresaDAO empresaDAO;

    public EmpresaController(EntityManager em) {
        this.empresaDAO = new EmpresaDAO(em);
    }

    public String cadastrarEmpresa(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "razaoSocial", "senha", "cnpj", "descricao", "ramo");
        if (!hasKeys) {
            responseJson.put("operacao", "cadastrarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida cnpj
        if (!(response = FormValidator.checkCnpj(request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida senha
        if (!(response = FormValidator.checkSenha(request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        Empresa isEmpresaEmail = this.empresaDAO.findEmpresaByEmail(request.getString("email"));
        if (isEmpresaEmail != null) {
            responseJson.put("operacao", "cadastrarEmpresa");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "E-mail já cadastrado");

            return responseJson.toString();
        }
        Empresa isEmpresaCnpj = this.empresaDAO.findEmpresaByCnpj(request.getString("cnpj"));
        if (isEmpresaCnpj != null) {
            responseJson.put("operacao", "cadastrarEmpresa");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "CNPJ já cadastrado");

            return responseJson.toString();
        }
        
        // Envia dados ao banco
        Empresa newEmpresa = new Empresa();
        newEmpresa.setEmail(request.getString("email"));
        newEmpresa.setRazaoSocial(request.getString("razaoSocial"));
        newEmpresa.setSenha(request.getString("senha"));
        newEmpresa.setCnpj(request.getString("cnpj"));
        newEmpresa.setDescricao(request.getString("descricao"));
        newEmpresa.setRamo(request.getString("ramo"));

        try {
            this.empresaDAO.createEmpresa(newEmpresa);
            String uuid = UUID.randomUUID().toString();

            responseJson.put("operacao", "cadastrarEmpresa");
            responseJson.put("status", 201);
            responseJson.put("token", uuid);

            return responseJson.toString();
        } catch (Exception ex) {
            responseJson.put("operacao", "cadastrarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Erro ao tentar cadastrar Empresa");

            return responseJson.toString();
        }

    }

    public String visualizarEmpresa(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token");
        if (!hasKeys) {
            responseJson.put("operacao", "visualizarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "visualizarEmpresa")).equals("OK")) {
            return response;
        }
        
        // Valida token
        if(!(response = this.empresaDAO.validToken(request)).equals("OK")){
            return response;
        }

        // Find no banco
        Empresa empresa = this.empresaDAO.findEmpresaByEmail(request.getString("email"));
        if (empresa == null) {
            responseJson.put("operacao", "visualizarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Email não encontrado");

            return responseJson.toString();
        }

        responseJson.put("operacao", "visualizarEmpresa");
        responseJson.put("status", 201);
        responseJson.put("razaoSocial", empresa.getRazaoSocial());
        responseJson.put("cnpj", empresa.getCnpj());
        responseJson.put("senha", empresa.getSenha());
        responseJson.put("descricao", empresa.getDescricao());
        responseJson.put("ramo", empresa.getRamo());

        return responseJson.toString();
    }

    public String atualizarEmpresa(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "razaoSocial", "senha", "cnpj", "descricao", "ramo", "token");
        if (!hasKeys) {
            responseJson.put("operacao", "atualizarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "atualizarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida cnpj
        if (!(response = FormValidator.checkCnpj(request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida senha
        if (!(response = FormValidator.checkSenha(request, "atualizarEmpresa")).equals("OK")) {
            return response;
        }
        
        // Valida token
        if(!(response = this.empresaDAO.validToken(request)).equals("OK")){
            return response;
        }
        
        // Verifica se o CNPJ está em uso
         Empresa isEmpresaCnpj = this.empresaDAO.findEmpresaByCnpj(request.getString("cnpj"));
        if (isEmpresaCnpj != null && !isEmpresaCnpj.getEmail().equals(request.getString("email"))) {
            responseJson.put("operacao", "atualizarEmpresa");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "CNPJ já cadastrado");

            return responseJson.toString();
        }

        // Envia dados ao banco
        boolean success = this.empresaDAO.updateEmpresa(request.getString("email"), request.getString("senha"), request.getString("razaoSocial"), request.getString("cnpj"), request.getString("descricao"), request.getString("ramo"));
        if (success) {
            responseJson.put("operacao", "atualizarEmpresa");
            responseJson.put("status", 201);

            return responseJson.toString();
        } else {
            responseJson.put("operacao", "atualizarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "E-mail não encontrado");

            return responseJson.toString();
        }
    }

    public String apagarEmpresa(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token");
        if (!hasKeys) {
            responseJson.put("operacao", "apagarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "apagarEmpresa")).equals("OK")) {
            return response;
        }
        
        // Valida token
        if(!(response = this.empresaDAO.validToken(request)).equals("OK")){
            return response;
        }

        // Envia dados ao banco
        boolean success = this.empresaDAO.deleteEmpresaByEmail(request.getString("email"));
        if (success) {
            responseJson.put("operacao", "apagarEmpresa");
            responseJson.put("status", 201);

            return responseJson.toString();
        } else {
            responseJson.put("operacao", "apagarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "E-mail não encontrado");

            return responseJson.toString();
        }
    }
}
