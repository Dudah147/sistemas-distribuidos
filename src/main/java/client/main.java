/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import javax.swing.SwingUtilities;
import view.Client;
import client.ClientApp;
import org.json.JSONArray;
import org.json.JSONObject;
import entities.*;

/**
 *
 * @author dudam
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
        ClientApp clientApp = new ClientApp("localhost");
        clientApp.connect();

        JSONObject request = new JSONObject();
        JSONArray competenciaExpArray = new JSONArray();

        JSONObject comp1 = new JSONObject();
        comp1.put("competencia", Competencia.PYTHON);
        comp1.put("experiencia", 20);
        
        JSONObject comp2 = new JSONObject();
        comp2.put("competencia", Competencia.PYTHON);
        comp2.put("experiencia", 0);
//        
//        competenciaExpArray.put(comp1);
//        competenciaExpArray.put(comp2);
//        
//        request.put("operacao", "cadastrarCompetenciaExperiencia");
//        request.put("email", "duda@email.com");
//        request.put("token", "f36672ad-c93f-40b0-b226-f2bbe3103d9f");
//        request.put("competenciaExperiencia", competenciaExpArray);

//        request.put("operacao", "visualizarCompetenciaExperiencia");
//        request.put("email", "duda@email.com");
//        request.put("token", "f36672ad-c93f-40b0-b226-f2bbe3103d9f");
// 
//        competenciaExpArray.put(Competencia.PYTHON.toString());
//        competenciaExpArray.put(Competencia.COBOL.toString());
//        request.put("operacao", "cadastrarVaga");
//        request.put("email", "empresa@email.com");
//        request.put("token", "2f78949d-cbbe-4887-8fe2-9828625ffc23");
//        request.put("competencias", competenciaExpArray);
//        request.put("faixaSalarial", 1099.23);
//        request.put("descricao", "DESCRICAO VAGAAAA");
//        request.put("estado", "divulgavel");
//        request.put("nome", "Vaga para Cargo X");
        
        request.put("operacao", "visualizarVaga");
        request.put("email", "empresa@email.com");
        request.put("token", "2f78949d-cbbe-4887-8fe2-9828625ffc23");
        request.put("idVaga", "2");
        String response = clientApp.callServer(request);
        System.out.println("Recebido do Servidor: " + response);
    }

}
