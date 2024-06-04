/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.CandidatoController;
import controller.CompetenciaExperienciaController;
import controller.EmpresaController;
import controller.LoginController;
import controller.VagaController;
import jakarta.persistence.EntityManager;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class Routes {

    private CandidatoController candidatoController;
    private LoginController loginController;
    private EmpresaController empresaController;
    private CompetenciaExperienciaController competenciaExpController;
    private VagaController vagaController;

    public Routes(EntityManager em) {
        this.candidatoController = new CandidatoController(em);
        this.empresaController = new EmpresaController(em);
        this.loginController = new LoginController(em);
        this.competenciaExpController = new CompetenciaExperienciaController(em);
        this.vagaController = new VagaController(em);
    }

    public String handleRequest(String request) {
        JSONObject requestJSON = new JSONObject(request);
        try {
            return switch (requestJSON.getString("operacao")) {
                // --> Rotas CANDIDATO
                case "cadastrarCandidato" ->
                    this.candidatoController.cadastrarCandidato(requestJSON);
                case "visualizarCandidato" ->
                    this.candidatoController.visualizarCandidato(requestJSON);
                case "atualizarCandidato" ->
                    this.candidatoController.atualizarCandidato(requestJSON);
                case "apagarCandidato" ->
                    this.candidatoController.apagarCandidato(requestJSON);

                // --> Rotas EMPRESA
                case "cadastrarEmpresa" ->
                    this.empresaController.cadastrarEmpresa(requestJSON);
                case "visualizarEmpresa" ->
                    this.empresaController.visualizarEmpresa(requestJSON);
                case "atualizarEmpresa" ->
                    this.empresaController.atualizarEmpresa(requestJSON);
                case "apagarEmpresa" ->
                    this.empresaController.apagarEmpresa(requestJSON);

                // --> Rotas para LOGIN
                case "loginCandidato" ->
                    this.loginController.loginCandidato(requestJSON);
                case "loginEmpresa" ->
                    this.loginController.loginEmpresa(requestJSON);
                case "logout" ->
                    this.loginController.logout(requestJSON);

                // --> Rotas para COMPETENCIA
                case "cadastrarCompetenciaExperiencia" ->
                    this.competenciaExpController.cadastrar(requestJSON);
                case "visualizarCompetenciaExperiencia" ->
                    this.competenciaExpController.visualizar(requestJSON);
                case "atualizarCompetenciaExperiencia" ->
                    this.competenciaExpController.atualizar(requestJSON);
                case "apagarCompetenciaExperiencia" ->
                    this.competenciaExpController.apagar(requestJSON);

                // --> Rotas para VAGA
                case "cadastrarVaga" ->
                    this.vagaController.cadastrar(requestJSON);
                case "visualizarVaga" ->
                    this.vagaController.visualizar(requestJSON);
                case "atualizarVaga" ->
                    this.vagaController.atualizar(requestJSON);
                case "apagarVaga" ->
                    this.vagaController.apagar(requestJSON);
                case "listarVagas" ->
                    this.vagaController.listarVagas(requestJSON);
                case "filtrarVagas" ->
                    this.vagaController.filtrarVagas(requestJSON);
                default ->
                    "OPERACAO '" + requestJSON.getString("operacao") + "' NÃO ENCONTRADA";
            };
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return "Chave 'operacao' não encontrada";
        }
    }
}
