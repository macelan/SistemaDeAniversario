/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author macel
 */
@Entity
@Table(name = "Bebida")
public class Bebida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBebida", nullable = false)
    private Integer idBebida;
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;
    @Column(name = "tipoBebida", length = 30, nullable = false)
    private String tipoBebida;
    @Column(name = "precoUnitario", nullable = false)
    private Double precoUnitario;

//    @ManyToMany(mappedBy = "bebidas")
    //  private List<Orcamento> orcamentos = new ArrayList<>();
    public Bebida() {
    }

    public Bebida(Integer idBebida, String nome, String tipoBebida, Double precoUnitario) {
        this.idBebida = idBebida;
        this.nome = nome;
        this.tipoBebida = tipoBebida;
        this.precoUnitario = precoUnitario;
    }

    public Integer getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(Integer idBebida) {
        this.idBebida = idBebida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(String tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "Bebida{" + "idBebida=" + idBebida + ", nome=" + nome + ", tipoBebida=" + tipoBebida + ", precoUnitario=" + precoUnitario + '}';
    }

   

}
