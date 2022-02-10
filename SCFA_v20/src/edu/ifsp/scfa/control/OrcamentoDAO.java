/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.control;

import edu.ifsp.scfa.model.ItemOrcamentoAlimento;
import edu.ifsp.scfa.model.ItemOrcamentoBebida;
import javax.persistence.EntityManager;
import edu.ifsp.scfa.model.Orcamento;
import edu.ifsp.scfa.util.Conexao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;


/**
 *
 * @author macel
 */
public class OrcamentoDAO implements Serializable {
/**
 * METODO SALVA UM ORCAMENTO VAZIO E  RETORNA O ID
     * @param orc
 * @return 
 */
    public Orcamento novoOrcamento(Orcamento orc){
        EntityManager em = Conexao.getConexao();
         {
            try {
                em.getTransaction().begin();
                em.persist(orc);
                em.getTransaction().commit();
              //  JOptionPane.showMessageDialog(null, "Orçamento criado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println("Erro ao tentar salvar " + e.getMessage());
            }

        }
         return orc;
        
    }
    
    
    
    
    public void salvar(Orcamento a) {
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
    public void remover(Orcamento f) {
        EntityManager em = Conexao.getConexao();
        Orcamento orcamento;// crio uma variavel tipo funcionario
        try {

            em.getTransaction().begin();
            orcamento = em.merge(f);//recebe o parametro nesta variavel funcionario criada
            em.remove(orcamento);
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
    public Orcamento buscarPorId(Integer id) {// aqui devolve um objeto o Integer pode trabalhar com NULL o inte não
        EntityManager em = Conexao.getConexao();//abre conexão
        Orcamento f;
        try {
            em.getTransaction().begin();
            f = em.find(Orcamento.class, id);// f recebe o objeto que  veio por parametro
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
        List<Orcamento> lista; //aqui temos uma lista
        try {
            em.getTransaction().begin();// abre transação
            TypedQuery consulta = em.createQuery("From Orcamento f", Orcamento.class);// crio uma TypedQuery que recebe uma query no JPA não precisa colocar select
            lista = consulta.getResultList();// recebe a consulta que vem como uma lista
            em.getTransaction().commit();// Fechar Transação
            return lista; // retorna lista que recebeu
        } catch (Exception e) {
            System.out.println("Erro ao listar " + e.getMessage());
        }
        return null;

    }

    public List pesquisarOrcamentos(String comando) {

        EntityManager em = Conexao.getConexao();
        List<Orcamento> lista = null;
        try {
            em.getTransaction().begin();
            TypedQuery consulta = em.createQuery(comando, Orcamento.class);
            lista = consulta.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());

        }
        return lista;
    }

    /**
     * METODO REALIZA UMA CONSULTA DE TODOS OS ITENS DO ORÇAMENTO BEBIDAS E
     * ALIMENTOS E DEVOLVE UMA LISTAGEM CONTENDO OS RESULTADOS.
     *
     * @param id
     * @return
     */
//    public List listarTodosItensDoOrcamento(Integer id) {
//        /* 
//        select b.item_quantidade as quantidade,b.item_valor as valor_untiario,(b.item_quantidade * b.item_valor) 
//        as valorTotal,x.nome as produto from itemorcamentobebida b inner join bebida x on b.idBebida = x.idBebida where b.idOrcamento = 1*/
//        List<ItemOrcamentoAlimento> alimentos = null;
//        List<ItemOrcamentoBebida> bebidas = null;
//        EntityManager em = Conexao.getConexao();
//        TypedQuery query = em.createQuery("SELECT b FROM ItemOrcamentoBebida b WHERE b.orcamento.idOrcamento=:parametro", ItemOrcamentoBebida.class);
//        TypedQuery query2 = em.createQuery("SELECT a FROM ItemOrcamentoAlimento a WHERE a.orcamento.idOrcamento =:parametro2", ItemOrcamentoAlimento.class);
//        
//        query.setParameter("parametro", id);
//        query2.setParameter("parametro2", id);
//        bebidas = query.getResultList();
//        alimentos = query2.getResultList();
//        
//        // passo em teste
////        List<ClasseAuxiliar> lista = new ArrayList();// lista para exibir os dados
////
////        for (ItemOrcamentoAlimento i : alimentos) {
////            ClasseAuxiliar z = new ClasseAuxiliar();
////            z.setNomeProduto(i.getAlimento().getNome());
////            z.setNumeroItem(i.getNumeroItem());
////            z.setOrcamento(i.getOrcamento());
////            z.setQuantidade(i.getQuantidade());
////            z.setValorUnitario(i.getValorUnitario());
////            lista.add(z);
//
////        }
////        for (ItemOrcamentoBebida b : bebidas) {
////            ClasseAuxiliar x = new ClasseAuxiliar();
////            x.setNomeProduto(b.getBebida().getNome());
////            x.setNumeroItem(b.getNumeroItem());
////            x.setOrcamento(b.getOrcamento());
////            x.setQuantidade(b.getQuantidade());
////            x.setValorUnitario(b.getValorUnitario());
////            lista.add(x);
////
////        }
////
////        //resultado.addAll(alimentos);
////        //resultado.addAll(bebidas); 
////        return lista;
////    }

}
