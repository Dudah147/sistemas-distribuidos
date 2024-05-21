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

    private JSONObject request;
    private EmpresaDAO empresaDAO;

    public EmpresaController(EntityManager em, JSONObject request) {
        this.request = request;
        this.empresaDAO = new EmpresaDAO(em);
    }

    public String cadastrarEmpresa() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email", "razaoSocial", "senha", "cnpj", "descricao", "ramo");
        if (!hasKeys) {
            responseJson.put("operacao", "cadastrarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida cnpj
        if (!(response = FormValidator.checkCnpj(this.request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida senha
        if (!(response = FormValidator.checkSenha(this.request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        // Envia dados ao banco
        Empresa isEmpresa = this.empresaDAO.findEmpresaByEmail(this.request.getString("email"));
        if (isEmpresa == null) {
            Empresa newEmpresa = new Empresa();
            newEmpresa.setEmail(this.request.getString("email"));
            newEmpresa.setRazaoSocial(this.request.getString("razaoSocial"));
            newEmpresa.setSenha(this.request.getString("senha"));
            newEmpresa.setCnpj(this.request.getString("cnpj"));
            newEmpresa.setDescricao(this.request.getString("descricao"));
            newEmpresa.setRamo(this.request.getString("ramo"));

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

        } else {
            responseJson.put("operacao", "cadastrarEmpresa");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "E-mail já cadastrado");

            return responseJson.toString();
        }
    }

    public String visualizarEmpresa() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email");
        if (!hasKeys) {
            responseJson.put("operacao", "visualizarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "visualizarEmpresa")).equals("OK")) {
            return response;
        }

        // Find no banco
        Empresa empresa = this.empresaDAO.findEmpresaByEmail(this.request.getString("email"));
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

    public String atualizarEmpresa() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email", "razaoSocial", "senha", "cnpj", "descricao", "ramo");
        if (!hasKeys) {
            responseJson.put("operacao", "atualizarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "atualizarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida cnpj
        if (!(response = FormValidator.checkCnpj(this.request, "cadastrarEmpresa")).equals("OK")) {
            return response;
        }

        // Valida senha
        if (!(response = FormValidator.checkSenha(this.request, "atualizarEmpresa")).equals("OK")) {
            return response;
        }

        // Envia dados ao banco
        boolean success = this.empresaDAO.updateEmpresa(this.request.getString("email"), this.request.getString("senha"), this.request.getString("razaoSocial"), this.request.getString("cnpj"), this.request.getString("descricao"), this.request.getString("ramo"));
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

    public String apagarEmpresa() {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(this.request, "email");
        if (!hasKeys) {
            responseJson.put("operacao", "apagarEmpresa");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(this.request, "apagarEmpresa")).equals("OK")) {
            return response;
        }
        
        // Envia dados ao banco
        boolean success = this.empresaDAO.deleteEmpresaByEmail(this.request.getString("email"));
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
