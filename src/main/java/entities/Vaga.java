/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author dudam
 */
@Entity
@Table(name="vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int idVaga;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "idEmpresa", nullable = false, foreignKey = @ForeignKey(
            name = "FK_VAGA_EMPRESA",
            foreignKeyDefinition = "FOREIGN KEY (idEmpresa) REFERENCES empresa(idEmpresa) ON UPDATE CASCADE ON DELETE CASCADE"
    ))
    private Empresa empresa;
    private float faixaSalarial;
    private String descricao;
    private String estado;
    private String competencias;

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Vaga{" + "idVaga=" + idVaga + ", nome=" + nome + ", empresa=" + empresa + ", faixaSalarial=" + faixaSalarial + ", descricao=" + descricao + ", estado=" + estado + ", competencias=" + competencias + '}';
    }

    public float getFaixaSalarial() {
        return faixaSalarial;
    }

    public void setFaixaSalarial(float faixaSalarial) {
        this.faixaSalarial = faixaSalarial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCompetencias() {
        return competencias;
    }

    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }

        
}
