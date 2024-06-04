/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.*;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class ClientApp {

    private String serverIp;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientApp(String serverIp) {
        this.serverIp = serverIp;
    }

    public boolean connect() {
        try {
            this.socket = new Socket(this.serverIp, 22222); 
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            System.out.println("Conectado ao servidor: " + this.serverIp);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String callServer(JSONObject request) {
        try {
            System.out.println("Enviado: " + request.toString());
            out.println(request.toString());
            String response = this.in.readLine();
            System.out.println("Recebido do Servidor: " + response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro na comunicação com o servidor";
        }
    }

}
