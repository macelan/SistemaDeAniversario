/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.control;

import edu.ifsp.scfa.model.ItemOrcamentoBebida;
import edu.ifsp.scfa.model.ItemOrcamentoUtensilio;
import edu.ifsp.scfa.util.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Macelan
 */
public class ItemOrcamentoUtensilioDAO {

    public void salvar(ItemOrcamentoUtensilio item) {

        EntityManager em = Conexao.getConexao();

        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        System.out.println("salvou o item");

    }
    
    public void excluir(ItemOrcamentoUtensilio item) {

        EntityManager em = Conexao.getConexao();

        em.getTransaction().begin();
        ItemOrcamentoUtensilio itemUtensilio = em.merge(item);
        em.remove(itemUtensilio);
        em.getTransaction().commit();
        System.out.println("removeu o item");

    }
    
    /**
     * METODO LISTA TODOS os utensilios CONTIDos NO ORÇAMENTO COM O ID RECEBIDO
     * POR PARAMETRO
     *
     * @param idOrcamento
     * @return
     */
    public List listarTodos(Integer idOrcamento) {
        EntityManager em = Conexao.getConexao();
        List<ItemOrcamentoUtensilio> lista = null;

        try {
            em.getTransaction().begin();
            TypedQuery<ItemOrcamentoUtensilio> sql = em.createQuery("select i FROM ItemOrcamentoUtensilio i where i.orcamento.idOrcamento=:a", ItemOrcamentoUtensilio.class);
            sql.setParameter("a", idOrcamento);
            lista = sql.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao consultar os item de utensilio! " + e);
        }
        return lista;

    }
}
