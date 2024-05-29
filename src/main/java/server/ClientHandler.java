/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import jakarta.persistence.EntityManager;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author dudam
 */
public class ClientHandler extends Thread {

    private Socket clientSocket;
    private EntityManager em;

    public ClientHandler(Socket socket, EntityManager em) {
        this.clientSocket = socket;
        this.em = em;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Routes routes = new Routes(em);
            String line;
            while ((line = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line)) {
                    System.out.println("Conexão encerrada.");
                    out.println("Conexão encerrada.");
                    break;
                }
                System.out.println("Recebido do cliente: " + line);
                String response = routes.handleRequest(line);
                System.out.println("Enviado: "+response);
                out.println(response);
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado!");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
