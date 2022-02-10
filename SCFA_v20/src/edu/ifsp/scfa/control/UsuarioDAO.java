/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.control;

import javax.persistence.EntityManager;
import edu.ifsp.scfa.model.Usuario;
import edu.ifsp.scfa.util.Conexao;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author macel
 */
public class UsuarioDAO implements Serializable {

    public void salvar(Usuario a) {
        EntityManager em = Conexao.getConexao();
        {
            try {
                em.getTransaction().begin();
                em.merge(a);
                em.getTransaction().commit();
              //  JOptionPane.showMessageDialog(null, "Dados foram gravados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println("Erro ao tentar salvar " + e.getMessage());
            }

        }
    }

    /**
     * METODO REMOVE UM OBJETO DO TIPO FUNCIONARIO DO BANCO DE DADOS.
     *
     * @param f
     */
    public void remover(Usuario f) {
        EntityManager em = Conexao.getConexao();
        Usuario usuario;// crio uma variavel tipo funcionario
        try {

            em.getTransaction().begin();
            usuario = em.merge(f);//recebe o parametro nesta variavel funcionario criada
            em.remove(usuario);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Erro ao remover " + e.getMessage());
            if (!em.getTransaction().isActive()) {// se a conexão não estiver ativa
                em.getTransaction().begin();
                em.getTransaction().rollback();// desfaz transação
                em.getTransaction().commit();
            }

        }

    }

    /**
     * METODO REALIZA UMA BUSCA POR ID NO BANCO E RETORNA UM OBJETO
     * CORRESPONDENTE AO ID DESEJADO
     *
     * @param id
     * @return
     */
    public Usuario buscarPorId(Integer id) {// aqui devolve um objeto o Integer pode trabalhar com NULL o inte não
        EntityManager em = Conexao.getConexao();//abre conexão
        Usuario f;
        try {
            em.getTransaction().begin();
            f = em.find(Usuario.class, id);// f recebe o objeto que  veio por parametro
            em.getTransaction().commit();// fecha ransação
            return f;

        } catch (Exception e) {
            System.out.println("Bebida não encontrada! " + e.getMessage());

        }

        return null;
    }

    /**
     * METODO REALIZA A LISTAGEM DE TODOS OS OBJETOS DO TIPO FUNCIONARIO NO
     * BANCO DE DADOS
     *
     * @return
     */
    public List listarTodos() {
        EntityManager em = Conexao.getConexao();
        List<Usuario> lista; //aqui temos uma lista
        try {
            em.getTransaction().begin();// abre transação
            TypedQuery consulta = em.createQuery("From Usuario f", Usuario.class);// crio uma TypedQuery que recebe uma query no JPA não precisa colocar select
            lista = consulta.getResultList();// recebe a consulta que vem como uma lista
            em.getTransaction().commit();// Fechar Transação
            return lista; // retorna lista que recebeu
        } catch (Exception e) {
            System.out.println("Erro ao listar " + e.getMessage());
        }
        return null;

    }

    public List pesquisarUsuarios(String comando) {

        EntityManager em = Conexao.getConexao();
        {
            List<Usuario> lista = null;
            try {
                em.getTransaction().begin();
                TypedQuery consulta = em.createQuery(comando, Usuario.class);
                lista = consulta.getResultList();
                em.getTransaction().commit();

            } catch (Exception e) {
                System.out.println("Erro ao pesquisar " + e.getMessage());

            }
            return lista;
        }
    }

    public boolean listarPorLogin(String login) {
        // lista de usuários a ser retornada
        List<Usuario> lista;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = Conexao.getConexao();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Usuario> consulta;

        // criar a consulta 
        consulta = (TypedQuery<Usuario>) gerente.createQuery("SELECT u FROM Usuario u WHERE u.login = :login");

        // passando o nome a ser procurado como parâmetro para a query
        consulta.setParameter("login", login);

        // pegar o resultado da consulta realizada
        try {
            lista = consulta.getResultList();
            if (lista.size() > 0) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        }
        return false;
    }

    public boolean SenhaConfere(String login, String senha) {
        // lista de usuários a ser retornada
        List<Usuario> lista;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = Conexao.getConexao();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Usuario> consulta;

        // criar a consulta 
        consulta = (TypedQuery<Usuario>) gerente.createQuery("SELECT u FROM Usuario u WHERE u.login = :login and u.senha = :senha");

        // passando o nome a ser procurado como parâmetro para a query
        // consulta.setParamete("login", login , "senha", senha );
        // pegar o resultado da consulta realizada
        try {
            lista = consulta.getResultList();
            if (lista.size() > 0) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        }
        return false;
    }

    /**
     * METODO LOCALIZA UM USUARIO NO BANCO DE DADOS VERIFICANDO A SENHA E O
     * LOGIN E O RETORNA CASO ENCONTRADO
     *
     * @param login
     * @param senha
     * @return
     */
    public Usuario confirmaUsuario(String login, String senha) {
        // Uusario a ser retornado
        Usuario usuario = null;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager gerente = Conexao.getConexao();
        // Criar um identificar para uma consulta SQL
        try {
            // criar a consulta 
            TypedQuery<Usuario> consulta = gerente.createQuery("Select u FROM Usuario u where u.login=:a and u.senha=:b", Usuario.class);

            // passando o nome a ser procurado como parâmetro para a query
            consulta.setParameter("a", login);
            consulta.setParameter("b", senha);
            // pegar o resultado da consulta realizada
            usuario = (Usuario) consulta.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("USUARIO NÃO ENCONTRADO " + e);

        }

        return usuario;

    }
}
