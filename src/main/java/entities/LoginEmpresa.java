/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author dudam
 */
@Entity
@Table(name = "loginEmpresa")
public class LoginEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLoginEmpresa;

    private String token;

    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false, foreignKey = @ForeignKey(
            name = "FK_LOGIN_EMPRESA",
            foreignKeyDefinition = "FOREIGN KEY (idEmpresa) REFERENCES empresa(idEmpresa) ON UPDATE CASCADE ON DELETE CASCADE"
    ))
    private Empresa empresa;

    public int getIdLoginEmpresa() {
        return idLoginEmpresa;
    }

    public void setIdLoginEmpresa(int idLoginEmpresa) {
        this.idLoginEmpresa = idLoginEmpresa;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "LoginEmpresa{" + "idLoginEmpresa=" + idLoginEmpresa + ", token=" + token + ", empresa=" + empresa + '}';
    }
    
    
}
