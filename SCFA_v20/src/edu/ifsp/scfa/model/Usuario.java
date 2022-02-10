/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.model;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "Usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Column(name = "nome", length = 70, nullable = false)
    private String nome;
    @Column(name = "login",length = 80, nullable = false, unique = true)//, unique = true
    private String login;
    @Column(name = "senha",length = 80, nullable = false)
    private String senha;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nome, String login, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idUsuario);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }

    
    
    
    
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nome=" + nome + ", login=" + login + ", senha=" + senha + '}';
    }

   

}
