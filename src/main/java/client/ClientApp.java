/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import org.json.JSONException;
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
            Scanner ip = new Scanner(System.in);
            System.out.println("Digite o IP:");
            
            Socket socket = new Socket(ip.nextLine(), 22222);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            JSONObject json = new JSONObject();

            System.out.println("Conectado ao servidor.");
            while (true) {
                System.out.println("Digite o numero da operacao:");
                System.out.println("1- cadastrarCandidato");
                System.out.println("2- visualizarCandidato");
                System.out.println("3- atualizarCandidato");
                System.out.println("4- apagarCandidato");
                System.out.println("5- loginCandidato");
                System.out.println("6- logoutCandidato");
                System.out.println("7- EXIT");
            
            
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1 -> {
                        System.out.println("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.println("Email: ");
                        String email = scanner.nextLine();
                        System.out.println("Senha: ");
                        String senha = scanner.nextLine();
                        
                        json.put("operacao", "cadastrarCandidato");
                        json.put("nome", nome);
                        json.put("email", email);
                        json.put("senha", senha);
                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }
                    case 2 -> {
                        System.out.println("Email: ");
                        String email = scanner.nextLine();
                        
                        json.put("operacao", "visualizarCandidato");
                        json.put("email", email);
                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }
                    case 3 -> {
                        System.out.println("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.println("Email: ");
                        String email = scanner.nextLine();
                        System.out.println("Senha: ");
                        String senha = scanner.nextLine();
                        
                        json.put("operacao", "atualizarCandidato");
                        json.put("nome", nome);
                        json.put("email", email);
                        json.put("senha", senha);
                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }

                    case 4 -> {
                        System.out.println("Email: ");
                        String email = scanner.nextLine();
                        
                        json.put("operacao", "apagarCandidato");
                        json.put("email", email);
                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }

                    case 5 -> {

                        System.out.println("Email: ");
                        String email = scanner.nextLine();
                        System.out.println("Senha: ");
                        String senha = scanner.nextLine();
                        
                        json.put("operacao", "loginCandidato");
                        json.put("email", email);
                        json.put("senha", senha);
                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }

                    case 6 -> {
                        System.out.println("Token: ");
                        String token = scanner.nextLine();

                        
                        json.put("operacao", "logout");
                        json.put("token", token);
                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }

                    case 7 -> {
                        System.out.println("Deconectando...");
                        return;
                    }
                    default -> {
                        System.out.println("Opção não encontrada");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
