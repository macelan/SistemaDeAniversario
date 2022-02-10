/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.pk;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Programar
 */
public class itemOrcamentoBebidaPK implements Serializable{
    private Integer numeroItem;
    private Integer orcamento;

    public itemOrcamentoBebidaPK() {
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public Integer getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Integer orcamento) {
        this.orcamento = orcamento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.numeroItem);
        hash = 83 * hash + Objects.hashCode(this.orcamento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final itemOrcamentoBebidaPK other = (itemOrcamentoBebidaPK) obj;
        if (!Objects.equals(this.numeroItem, other.numeroItem)) {
            return false;
        }
        if (!Objects.equals(this.orcamento, other.orcamento)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
