/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class ClientApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 22222);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Conectado ao servidor");
            
            JSONObject json = new JSONObject();
            json.put("operacao", "cadastrarCandidato");
            json.put("nome", "Pedro");
            json.put("email", "pedro@gmail.com");
            json.put("senha", 1234);

            
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
