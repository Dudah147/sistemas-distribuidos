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
@Table(name = "competenciaExperiencia")
public class CompetenciaExperiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompetenciaExperiencia;

    @ManyToOne
    @JoinColumn(name = "idCandidato", nullable = false, foreignKey = @ForeignKey(
            name = "FK_COMPETENCIA_CANDIDATO",
            foreignKeyDefinition = "FOREIGN KEY (idCandidato) REFERENCES candidato(idCandidato) ON UPDATE CASCADE ON DELETE CASCADE"
    ))
    private Candidato candidato;
    private String competencia;
    private int experiencia;

    public int getIdCompetenciaExperiencia() {
        return idCompetenciaExperiencia;
    }

    public void setIdCompetenciaExperiencia(int idCompetenciaExperiencia) {
        this.idCompetenciaExperiencia = idCompetenciaExperiencia;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "CompetenciaExperiencia{" + "idCompetenciaExperiencia=" + idCompetenciaExperiencia + ", candidato=" + candidato + ", competencia=" + competencia + ", experiencia=" + experiencia + '}';
    }

 

    
}
