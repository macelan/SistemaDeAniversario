/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "Alimento")
public class Alimento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlimento", nullable = false)
    private Integer idAlimento;
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;
    @Column(name = "tipoAlimento", length = 30, nullable = false)
    private String tipoAlimento;
    @Column(name = "precoUnitario")
    private Double precoUnitario;
    //unidade se é unitario, kl, cento. etc....
    
//    @ManyToMany(mappedBy = "alimentos")
   // private List<Orcamento> orcamentos = new ArrayList<>();

    public Alimento() {
    }

    public Alimento(Integer idAlimento, String nome, String tipoAlimento, Double precoUnitario) {
        this.idAlimento = idAlimento;
        this.nome = nome;
        this.tipoAlimento = tipoAlimento;
        this.precoUnitario = precoUnitario;
    }

    

    public Integer getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "Alimento{" + "idAlimento=" + idAlimento + ", nome=" + nome + ", tipoAlimento=" + tipoAlimento + ", precoUnitario=" + precoUnitario + '}';
    }

    
}
