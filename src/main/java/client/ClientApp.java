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
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Conectado ao servidor. Digite " + '"' + "exit" + '"' + " para sair!");
            
            // Testando cadastrarUsuario
            // EMAIL INVALIDO
            JSONObject json = new JSONObject();
            json.put("operacao", "cadastrarCandidato");
            json.put("nome", "Usuario");
            json.put("email", "emailerrado");
            json.put("senha", 1234);
            
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());
            
            // SENHA INVALIDA
            json = new JSONObject();
            json.put("operacao", "cadastrarCandidato");
            json.put("nome", "Usuario");
            json.put("email", "pedro@gmail.com");
            json.put("senha", "1234");
            
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());
            
            // CADASTRO REALIZADO COM SUCESSO
            json = new JSONObject();
            json.put("operacao", "cadastrarCandidato");
            json.put("nome", "Usuario");
            json.put("email", "pedro@gmail.com");
            json.put("senha", 1234);
            
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());
            
            // EMAIL JÁ CADASTRADO
            json = new JSONObject();
            json.put("operacao", "cadastrarCandidato");
            json.put("nome", "Usuario");
            json.put("email", "pedro@gmail.com");
            json.put("senha", 1234);
            
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());
            
            
            // Testando Visualizar Candidato
            json = new JSONObject();
            json.put("operacao", "visualizarCandidato");
            json.put("email", "pedro@gmail.com");
            
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());
            
            while (scanner.nextLine().equals("exit")) {
                out.println("exit");
                System.out.println("Deconectando...");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
