/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "Cliente" )
public class Cliente implements Serializable{
   
    //@Transient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;
    @Column(name = "nome",length = 70, nullable = false)
    private String nome;
    @Column(name = "endereco",length = 80, nullable = false)
    private String endereco;
    @Column(name = "telefone",length = 20, nullable = false)
    private String telefone;
    @Column(name = "cpf",length = 20, nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE) // usar f.setDataRegistro(new Date ()); na visão par pegar data automatica
    @Column(name = "dataCadastro")
    private Date dataCadastro;

    
    @OneToMany(mappedBy = "cliente")
    private List<Orcamento> orcamentos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer idCliente, String nome, String endereco, String telefone, String cpf, Date dataCadastro) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cpf=" + cpf + ", dataCadastro=" + dataCadastro + ", orcamentos=" + orcamentos + '}';
    }

    
}
