/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.model;

import edu.ifsp.scfa.pk.itemOrcamentoUtensilioPK;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Programar
 */

@Entity
@Table(name = "itemorcamentoutencilio")
@IdClass(itemOrcamentoUtensilioPK.class)
public class ItemOrcamentoUtensilio implements Serializable {

    @Id
    @Column(name = "numeroItem", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numeroItem;
    
    @ManyToOne
    @JoinColumn(name = "idUtensilio")
    private Utensilio utensilio;
    @Id
    @ManyToOne
    @JoinColumn(name = "idOrcamento")
    private Orcamento orcamento;
    @Column(name = "item_quantidade",length = 5)
    private Integer quantidade;
    @Column(name = "item_valor",length = 10)
    private double valorUnitario;

    public ItemOrcamentoUtensilio() {
    }

    public ItemOrcamentoUtensilio(Integer numeroItem, Utensilio utensilio, Orcamento orcamento, Integer quantidade, double valorUnitario) {
        this.numeroItem = numeroItem;
        this.utensilio = utensilio;
        this.orcamento = orcamento;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public Utensilio getUtensilio() {
        return utensilio;
    }

    public void setUtensilio(Utensilio utensilio) {
        this.utensilio = utensilio;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return "ItemOrcamentoUtensilio{" + "numeroItem=" + numeroItem + ", utensilio=" + utensilio + ", orcamento=" + orcamento + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + '}';
    }
    
}
