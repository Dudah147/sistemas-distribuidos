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
        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
//        ClientApp clientApp = new ClientApp("localhost");
//        clientApp.connect();
//
//        JSONObject request = new JSONObject();
//        JSONArray competenciaExpArray = new JSONArray();
//        
//        JSONObject comp1 = new JSONObject();
//        comp1.put("competencia", Competencia.COBOL);
//        comp1.put("experiencia", 2);
//        
//        JSONObject comp2 = new JSONObject();
//        comp2.put("competencia", Competencia.HTML);
//        comp2.put("experiencia", 0);
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
//        String response = clientApp.callServer(request);
//        System.out.println("Recebido do Servidor: " + response);
    }

}
