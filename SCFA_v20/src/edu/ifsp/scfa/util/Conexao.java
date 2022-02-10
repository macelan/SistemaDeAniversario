/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author macel
 */
public class Conexao {

    private static EntityManagerFactory emf = null;

    public static EntityManager getConexao() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory ("SCFA_v20PU");
        }
        return emf.createEntityManager();
    }

}
