/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.*;

/**
 *
 * @author dudam
 */
@Entity
@Table(name = "loginCandidato")
public class LoginCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLoginCandidato;

    private String token;

    @ManyToOne
    @JoinColumn(name = "idCandidato", nullable = false, foreignKey = @ForeignKey(
            name = "FK_LOGIN_CANDIDATO",
            foreignKeyDefinition = "FOREIGN KEY (idCandidato) REFERENCES candidato(idCandidato) ON UPDATE CASCADE ON DELETE CASCADE"
    ))
    private Candidato candidato;

    public int getIdLoginCandidato() {
        return idLoginCandidato;
    }

    public void setIdLoginCandidato(int idLoginCandidato) {
        this.idLoginCandidato = idLoginCandidato;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    @Override
    public String toString() {
        return "LoginCandidato{" + "idLoginCandidato=" + idLoginCandidato + ", token=" + token + ", candidato=" + candidato + '}';
    }
}
