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
@Table(name = "Utensilio")
public class Utensilio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtensilio", nullable = false)
    private Integer idUtensilio;
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;
    @Column(name = "tipoUtensilio", length = 30, nullable = false)
    private String tipoUtensilio;
    @Column(name = "precoUnitario", nullable = false)
    private Double precoUnitario;

//    @ManyToMany(mappedBy = "utensilios")
    //  private List<Orcamento> orcamentos = new ArrayList<>();
    public Utensilio() {
    }

    public Utensilio(Integer idUtensilio, String nome, String tipoUtensilio, Double precoUnitario) {
        this.idUtensilio = idUtensilio;
        this.nome = nome;
        this.tipoUtensilio = tipoUtensilio;
        this.precoUnitario = precoUnitario;
    }

    public Integer getIdUtensilio() {
        return idUtensilio;
    }

    public void setIdUtensilio(Integer idUtensilio) {
        this.idUtensilio = idUtensilio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUtensilio() {
        return tipoUtensilio;
    }

    public void setTipoUtensilio(String tipoUtensilio) {
        this.tipoUtensilio = tipoUtensilio;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "Utensilio{" + "idUtensilio=" + idUtensilio + ", nome=" + nome + ", tipoUtensilio=" + tipoUtensilio + ", precoUnitario=" + precoUnitario + '}';
    }

   
   

}
