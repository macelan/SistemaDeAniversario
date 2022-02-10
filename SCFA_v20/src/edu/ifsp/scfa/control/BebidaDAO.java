/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.control;

import javax.persistence.EntityManager;
import edu.ifsp.scfa.model.Bebida;
import edu.ifsp.scfa.util.Conexao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author macel
 */
public class BebidaDAO implements Serializable{
  
     public void salvar(Bebida a) {
        EntityManager em = Conexao.getConexao();
        {
            try {
                em.getTransaction().begin();
                em.merge(a);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Dados foram gravados.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
    public void remover(Bebida f) {
        EntityManager em = Conexao.getConexao();
        Bebida bebida;// crio uma variavel tipo funcionario
        try {

            em.getTransaction().begin();
            bebida = em.merge(f);//recebe o parametro nesta variavel funcionario criada
            em.remove(bebida);
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
    public Bebida buscarPorId(Integer id) {// aqui devolve um objeto o Integer pode trabalhar com NULL o inte não
        EntityManager em = Conexao.getConexao();//abre conexão
        Bebida f;
        try {
            em.getTransaction().begin();
            f = em.find(Bebida.class, id);// f recebe o objeto que  veio por parametro
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
        List<Bebida> lista; //aqui temos uma lista
        try {
            em.getTransaction().begin();// abre transação
            TypedQuery consulta = em.createQuery("From Bebida f", Bebida.class);// crio uma TypedQuery que recebe uma query no JPA não precisa colocar select
            lista = consulta.getResultList();// recebe a consulta que vem como uma lista
            em.getTransaction().commit();// Fechar Transação
            return lista; // retorna lista que recebeu
        } catch (Exception e) {
            System.out.println("Erro ao listar " + e.getMessage());
        }
        return null;

    }
    
    public List pesquisarBebidas(String comando) {

        EntityManager em = Conexao.getConexao();
        List<Bebida> lista = null;
        try {
            em.getTransaction().begin();
            TypedQuery consulta = em.createQuery(comando, Bebida.class);
            lista = consulta.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());

        }
        return lista;
    }
}