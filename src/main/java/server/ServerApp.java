/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author dudam
 */
public class ServerApp {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemasDistribuidos");
    private static EntityManager em = emf.createEntityManager();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            System.out.println("Servidor iniciado na porta 22222.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");
                new ClientHandler(clientSocket, em).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
