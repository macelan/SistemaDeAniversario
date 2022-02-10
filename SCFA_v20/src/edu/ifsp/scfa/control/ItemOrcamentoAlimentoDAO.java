/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.control;

import edu.ifsp.scfa.model.ItemOrcamentoAlimento;
import edu.ifsp.scfa.util.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Macelan
 */
public class ItemOrcamentoAlimentoDAO {

    public void salvar(ItemOrcamentoAlimento item) {

        EntityManager em = Conexao.getConexao();

        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        System.out.println("salvou o item");

    }

    public void excluir(ItemOrcamentoAlimento item) {

        EntityManager em = Conexao.getConexao();

        em.getTransaction().begin();
        ItemOrcamentoAlimento itemAlimento = em.merge(item);
        em.remove(itemAlimento);
        em.getTransaction().commit();
        System.out.println("removeu o item");

    }

    /**
     * METODO LISTA TODOS OS ALIMENTOS CONTIDO NO ORÇAMENTO COM O ID RECEBIDO
     * POR PARAMETRO
     *
     * @param idOrcamento
     * @return
     */
    public List listarTodos(Integer idOrcamento) {
        EntityManager em = Conexao.getConexao();
        List<ItemOrcamentoAlimento> lista = null;

        try {
            em.getTransaction().begin();
            TypedQuery<ItemOrcamentoAlimento> sql = em.createQuery("select i FROM ItemOrcamentoAlimento i where i.orcamento.idOrcamento=:a", ItemOrcamentoAlimento.class);
            sql.setParameter("a", idOrcamento);
            lista = sql.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao consultar os item de alimento! " + e);
        }
        return lista;

    }
}
