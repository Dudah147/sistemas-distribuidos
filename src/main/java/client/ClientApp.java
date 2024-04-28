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
            Socket socket = new Socket("localhost", 22222);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            System.out.println("Conectado ao servidor. Digite " + '"' + "exit" + '"' + " para sair!");

            // Testando cadastrarUsuario
            System.out.println("-- TESTANDO CADASTRAR CANDIDATO --");
            // EMAIL INVALIDO
            JSONObject json = new JSONObject();
//            json.put("operacao", "cadastrarCandidato");
//            json.put("nome", "Usuario");
//            json.put("email", "emailerrado");
//            json.put("senha", 1234);
//
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//
//            // NOME INVALIDO
//            json = new JSONObject();
//            json.put("operacao", "cadastrarCandidato");
//            json.put("nome", 1234);
//            json.put("email", "pedro@gmail.com");
//            json.put("senha", "1234");
//
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//
//            // CADASTRO REALIZADO COM SUCESSO
//            json = new JSONObject();
//            json.put("operacao", "cadastrarCandidato");
//            json.put("nome", "Usuario");
//            json.put("email", "pedro@gmail.com");
//            json.put("senha", 1234);
//
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//
//            // EMAIL JÁ CADASTRADO
//            json = new JSONObject();
//            json.put("operacao", "cadastrarCandidato");
//            json.put("nome", "Usuario");
//            json.put("email", "pedro@gmail.com");
//            json.put("senha", 1234);
//
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//
//            // Testando Visualizar Candidato
//            System.out.println("-- TESTANDO VISUALIZAR CANDIDATO --");
//
//            // EMAIL INVALIDO
//            json = new JSONObject();
//            json.put("operacao", "visualizarCandidato");
//            json.put("email", "naoencontrado@gmail.com");
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//
//            // EMAIL NÃO ENCONTRADO
//            json = new JSONObject();
//            json.put("operacao", "visualizarCandidato");
//            json.put("email", "emailinvalido");
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//
//            // SUCESSO
//            json = new JSONObject();
//            json.put("operacao", "visualizarCandidato");
//            json.put("email", "pedro@gmail.com");
//
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());

//            // Testando Atualizar Candidato
//            System.out.println("-- TESTANDO ATUALIZAR CANDIDATO --");
//
//            // EMAIL NÃO ENCONTRADO
//            json = new JSONObject();
//            json.put("operacao", "atualizarCandidato");
//            json.put("nome", "Usuario");
//            json.put("email", "naoencontrado@gmail.com");
//            json.put("senha", 1234);
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//
//            // SUCESSO
//            json = new JSONObject();
//            json.put("operacao", "atualizarCandidato");
//            json.put("nome", "UsuarioAtualizado");
//            json.put("email", "pedro@gmail.com");
//            json.put("senha", 12345);
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//            
//            // Testando Apagar Candidato
//            System.out.println("-- TESTANDO APAGAR CANDIDATO --");
//
//            // EMAIL INVALIDO
//            json = new JSONObject();
//            json.put("operacao", "apagarCandidato");
//            json.put("email", "naoencontrado@gmail.com");
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
//            
//            // SUCESSO
//            json = new JSONObject();
//            json.put("operacao", "apagarCandidato");
//            json.put("email", "pedro@gmail.com");
//
//            out.println(json.toString());
//            System.out.println("Resposta do servidor: " + in.readLine());
            // Testando Login Candidato
            System.out.println("-- TESTANDO LOGIN CANDIDATO --");

            // EMAIL INVALIDO
            json = new JSONObject();
            json.put("operacao", "loginCandidato");
            json.put("email", "pedro@gmail.com");
            json.put("senha", 12345);
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());

            // SUCESSO
            json = new JSONObject();
            json.put("operacao", "loginCandidato");
            json.put("email", "pedro@gmail.com");
            json.put("senha", 1234);
            out.println(json.toString());
            String response = in.readLine();
            System.out.println("Resposta do servidor: " + response);
            String token = "";
            try {
                token = new JSONObject(response).getString("token");
                System.out.println(token);
            } catch (JSONException ex) {
                System.out.println("Erro ao tentar resgatar token");
            }

            // Testando Logout Candidato
            System.out.println("-- TESTANDO LOGOUT CANDIDATO --");

            // TOKEN NÃO ENCONTRADO
            json = new JSONObject();
            json.put("operacao", "logout");
            json.put("token", "XXXXXX");
            out.println(json.toString());
            System.out.println("Resposta do servidor: " + in.readLine());

            // TOKEN SUCESSO
            json = new JSONObject();
            json.put("operacao", "logout");
            json.put("token", token);
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
