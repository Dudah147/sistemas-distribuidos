/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.persistence.EntityManager;
import org.json.JSONObject;

/**
 *
 * @author dudam
 */
public class LoginController {
    
    private EntityManager emf;
    private JSONObject request;
    
    public LoginController(EntityManager emf, JSONObject request){
        this.emf = emf;
        this.request = request;
    }
    
    public String loginCandidato(){
        return "";
    }
    
    public String loginEmpresa(){
        return "";
    }
    
    public String logout(){
        return "";
    }
}
