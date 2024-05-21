/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;

/**
 *
 * @author dudam
 */
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int idEmpresa;
    private String email;
    private String razaoSocial;
    private String senha;
    private String cnpj;
    private String descricao;
    private String ramo;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    @Override
    public String toString() {
        return "Empresa{" + "idEmpresa=" + idEmpresa + ", email=" + email + ", razaoSocial=" + razaoSocial + ", senha=" + senha + ", cnpj=" + cnpj + ", descricao=" + descricao + ", ramo=" + ramo + '}';
    }
    
}
