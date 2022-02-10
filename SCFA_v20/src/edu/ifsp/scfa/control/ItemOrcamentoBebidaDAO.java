/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.control;

import edu.ifsp.scfa.model.ItemOrcamentoBebida;
import edu.ifsp.scfa.util.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Macelan
 */
public class ItemOrcamentoBebidaDAO {

    public void salvar(ItemOrcamentoBebida item) {

        EntityManager em = Conexao.getConexao();

        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        System.out.println("salvou o item");

    }

    public void excluir(ItemOrcamentoBebida item) {

        EntityManager em = Conexao.getConexao();

        em.getTransaction().begin();
        ItemOrcamentoBebida itemBebida = em.merge(item);
        em.remove(itemBebida);
        em.getTransaction().commit();
        System.out.println("removeu o item");

    }

    /**
     * METODO LISTA TODOS AS BEBIDAS CONTIDAS NO ORÇAMENTO COM O ID RECEBIDO POR
     * PARAMETRO
     *
     * @param idOrcamento
     * @return
     */
    public List listarTodos(Integer idOrcamento) {
        EntityManager em = Conexao.getConexao();
        List<ItemOrcamentoBebida> lista = null;

        try {
            em.getTransaction().begin();
            TypedQuery<ItemOrcamentoBebida> sql = em.createQuery("select i FROM ItemOrcamentoBebida i where i.orcamento.idOrcamento=:a", ItemOrcamentoBebida.class);
            sql.setParameter("a", idOrcamento);
            lista = sql.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao consultar os item de bebida! " + e);
        }
        return lista;

    }
}
