/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.EmpresaDAO;
import DAO.VagaDAO;
import entities.Empresa;
import entities.Vaga;
import helpers.FormValidator;
import helpers.Formater;
import helpers.ListarVagasDTO;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class VagaController {

    private EntityManager em;
    private VagaDAO vagaDAO;
    private EmpresaDAO empresaDAO;

    public VagaController(EntityManager em) {
        this.em = em;
        this.vagaDAO = new VagaDAO(em);
        this.empresaDAO = new EmpresaDAO(em);
    }

    public String cadastrar(JSONObject request) {
        JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token", "nome", "faixaSalarial", "descricao", "estado", "competencias");
        if (!hasKeys) {
            responseJson.put("operacao", "cadastrarVaga");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "cadastrarVaga")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.empresaDAO.validToken(request)).equals("OK")) {
            return response;
        }

        // Verifica se a empresa é válida
        Empresa empresa = this.empresaDAO.findEmpresaByEmail(request.getString("email"));

        if (empresa == null) {
            responseJson.put("operacao", "cadastrarVaga");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Empresa não encontrada");

            return responseJson.toString();
        }

        // Cadastra a vaga
        try {
            JSONArray competenciasArray = request.getJSONArray("competencias");
            String competencias = Formater.convertJSONArrayToString(competenciasArray);

            Vaga newVaga = new Vaga();
            newVaga.setCompetencias(competencias);
            newVaga.setDescricao(request.getString("descricao"));
            newVaga.setEmpresa(empresa);
            newVaga.setEstado(request.getString("estado"));
            newVaga.setFaixaSalarial(request.getFloat("faixaSalarial"));
            newVaga.setNome(request.getString("nome"));

            this.vagaDAO.createVaga(newVaga);

            responseJson.put("operacao", "cadastrarVaga");
            responseJson.put("status", 201);
            responseJson.put("mensagem", "Vaga cadastrada com sucesso");

            return responseJson.toString();
        } catch (Exception ex) {
            responseJson.put("operacao", "cadastrarVaga");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Erro ao tentar cadastrar Vaga: " + ex.getMessage());

            return responseJson.toString();
        }

    }

    public String visualizar(JSONObject request) {
        JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token", "idVaga");
        if (!hasKeys) {
            responseJson.put("operacao", "visualizarVaga");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "cadastrarVaga")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.empresaDAO.validToken(request)).equals("OK")) {
            return response;
        }

        // Verifica se a empresa é válida
        Empresa empresa = this.empresaDAO.findEmpresaByEmail(request.getString("email"));

        if (empresa == null) {
            responseJson.put("operacao", "visualizarVagaVaga");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Empresa não encontrada");

            return responseJson.toString();
        }

        try {
            Vaga vaga = this.vagaDAO.findVagaByID(request.getInt("idVaga"));
            if (vaga == null) {
                responseJson.put("operacao", "visualizarVaga");
                responseJson.put("status", 422);
                responseJson.put("mensagem", "Vaga não encontrada");
                return responseJson.toString();
            }

            JSONArray competencias = Formater.convertStringToJSONArray(vaga.getCompetencias());

            responseJson.put("operacao", "visualizarVagaVaga");
            responseJson.put("status", 201);
            responseJson.put("faixaSalarial", vaga.getFaixaSalarial());
            responseJson.put("descricao", vaga.getDescricao());
            responseJson.put("estado", vaga.getEstado());
            responseJson.put("competencias", competencias);

            return responseJson.toString();
        } catch (Exception ex) {
            responseJson.put("operacao", "visualizarVagaVaga");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Erro ao tentar visualziar Vaga: " + ex.getMessage());

            return responseJson.toString();
        }

    }

    public String atualizar(JSONObject request) {
        JSONObject responseJson = new JSONObject();

        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token", "nome", "faixaSalarial", "descricao", "estado", "competencias");
        if (!hasKeys) {
            responseJson.put("operacao", "atualizarVaga");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "atualizarVaga")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.empresaDAO.validToken(request)).equals("OK")) {
            return response;
        }

        // Verifica se a empresa é válida
        Empresa empresa = this.empresaDAO.findEmpresaByEmail(request.getString("email"));

        if (empresa == null) {
            responseJson.put("operacao", "visualizarVagaVaga");
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Empresa não encontrada");

            return responseJson.toString();
        }

        // Envia dados ao banco
        try {
            JSONArray competenciasArray = request.getJSONArray("competencias");
            String competencias = Formater.convertJSONArrayToString(competenciasArray);
            String resp = this.vagaDAO.updateVaga(request.getInt("idVaga"), empresa, request.getString("nome"), request.getString("descricao"), request.getString("estado"), request.getFloat("faixaSalarial"), competencias);

            if (!resp.equals("OK")) {
                responseJson.put("operacao", "atualizarVaga");
                responseJson.put("status", 422);
                responseJson.put("mensagem", resp);

                return responseJson.toString();
            }

            responseJson.put("operacao", "atualizarVaga");
            responseJson.put("status", 201);
            responseJson.put("mensagem", "Competencia/Experiencia atualizada com sucesso");
            return responseJson.toString();

        } catch (Exception ex) {
            responseJson.put("operacao", "atualizarVaga");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Erro ao resgatar campos: " + ex.getMessage());

            return responseJson.toString();
        }
    }

    public String apagar(JSONObject request) {
        JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token", "idVaga");
        if (!hasKeys) {
            responseJson.put("operacao", "apagarVaga");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "apagarVaga")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.empresaDAO.validToken(request)).equals("OK")) {
            return response;
        }

        // Verifica se a empresa é válida
        Empresa empresa = this.empresaDAO.findEmpresaByEmail(request.getString("email"));

        if (empresa == null) {
            responseJson.put("operacao", "apagarVaga");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Empresa não encontrada");

            return responseJson.toString();
        }

        try {
            String resp = this.vagaDAO.deleteVagaById(request.getInt("idVaga"));
            if (!resp.equals("OK")) {
                responseJson.put("operacao", "apagarVaga");
                responseJson.put("status", 422);
                responseJson.put("mensagem", resp);

                return responseJson.toString();
            }

            responseJson.put("operacao", "apagarVaga");
            responseJson.put("status", 201);
            responseJson.put("mensagem", "Vaga apagada com sucesso");

            return responseJson.toString();
        } catch (Exception ex) {
            responseJson.put("operacao", "apagarVaga");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Erro ao tentar apagar Vaga: " + ex.getMessage());

            return responseJson.toString();
        }
    }

    public String listarVagas(JSONObject request) {
        JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token");
        if (!hasKeys) {
            responseJson.put("operacao", "listarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "listarVagas")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.empresaDAO.validToken(request)).equals("OK")) {
            return response;
        }

        // Verifica se a empresa é válida
        Empresa empresa = this.empresaDAO.findEmpresaByEmail(request.getString("email"));

        if (empresa == null) {
            responseJson.put("operacao", "listarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Empresa não encontrada");

            return responseJson.toString();
        }

        try {
            List<ListarVagasDTO> resp = this.vagaDAO.findVagasByEmpresa(empresa);
            if (resp == null) {
                responseJson.put("operacao", "listarVagas");
                responseJson.put("status", 201);
                responseJson.put("vagas", new JSONArray());
                return responseJson.toString();
            }

            responseJson.put("operacao", "listarVagas");
            responseJson.put("status", 201);
            responseJson.put("vagas", resp);

            return responseJson.toString();
        } catch (Exception ex) {
            responseJson.put("operacao", "listarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Erro ao tentar listar Vagas: " + ex.getMessage());

            return responseJson.toString();
        }
    }

   public String filtrarVagas(JSONObject request){
       JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = FormValidator.checkKeys(request, "email", "token");
        if (!hasKeys) {
            responseJson.put("operacao", "listarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");

            return responseJson.toString();
        }

        String response;
        //Valida email
        if (!(response = FormValidator.checkEmail(request, "listarVagas")).equals("OK")) {
            return response;
        }

        // Valida token
        if (!(response = this.empresaDAO.validToken(request)).equals("OK")) {
            return response;
        }
       
       return "OK";
   }
}
