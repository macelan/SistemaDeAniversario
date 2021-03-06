/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.view;

import edu.ifsp.scfa.control.BebidaDAO;
import edu.ifsp.scfa.control.ItemOrcamentoBebidaDAO;
import edu.ifsp.scfa.model.Bebida;
import edu.ifsp.scfa.model.ItemOrcamentoBebida;
import edu.ifsp.scfa.model.Orcamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Programar
 */
public class PesquisaBebida extends javax.swing.JDialog {

    /**
     * Creates new form PesquisaCliente
     */
    private ItemOrcamentoBebidaDAO dao = new ItemOrcamentoBebidaDAO();
    private BebidaDAO controle = new BebidaDAO();
    private List<Bebida> listabebidas = new ArrayList();
    Bebida b = new Bebida();
    private Orcamento orcamento;
    //private Integer numItem = 1;// faz parte da chave primaria junto com o orcamento

    /**
     * METODO RECEBE UM ORCAMENTO DA TELA DE ORCAMENTO
     *
     * @param orcamento
     */
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public PesquisaBebida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLista();
        txtLocalizar.requestFocus();
        atualizaTabela();
    }

    public void carregarLista() {
        listabebidas = controle.listarTodos();
    }

    public void atualizaTabela() {
        DefaultTableModel dtm = (DefaultTableModel) jTableBebidaPesquisa.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (Bebida b : listabebidas) {
            //String.format("%.2f",f.getPrecoUnitario()) -> mostra com duas casa apos a virgula
            dtm.addRow(new Object[]{b.getNome(), b.getPrecoUnitario(), b.getTipoBebida()}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }

    public void pesquisar() {
        String str = "from Bebida b ";
        String atributo = "b.nome";

        String filtro = txtLocalizar.getText();

        if (filtro.length() > 0 && filtro != null) {
            str += "where upper(" + atributo + ") like '%" + filtro.toUpperCase() + "%' ";
        }

        str += "order by " + atributo;
        pesquisarDinamico(str);//ATUALIZA A LISTA COM OS DADOS DESTA PESQUISA DINAMICA
        atualizaTabela();//PREENCHE COM OS NOVOS DADOS
    }

    public void pesquisarDinamico(String comando) {
        this.listabebidas = controle.pesquisarBebidas(comando);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLocalizar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBebidaPesquisa = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        lbValorUnitario = new javax.swing.JLabel();
        tfValorUnitario = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel1.setText("Pesquisar Bebidas:");

        txtLocalizar.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtLocalizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocalizarKeyReleased(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relação de Bebida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Unicode MS", 1, 18))); // NOI18N

        jTableBebidaPesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Valor Unitario", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBebidaPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBebidaPesquisaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBebidaPesquisa);
        if (jTableBebidaPesquisa.getColumnModel().getColumnCount() > 0) {
            jTableBebidaPesquisa.getColumnModel().getColumn(0).setResizable(false);
            jTableBebidaPesquisa.getColumnModel().getColumn(1).setResizable(false);
            jTableBebidaPesquisa.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel2.setText("Quantidade:");

        txtQuantidade.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N

        lbValorUnitario.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        lbValorUnitario.setText("Valor Unitario R$:");

        tfValorUnitario.setEditable(false);
        tfValorUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValorUnitarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbValorUnitario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfValorUnitario)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfValorUnitario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbValorUnitario)))
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btCadastrar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLocalizar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btCadastrar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCadastrar)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtLocalizarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocalizarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtLocalizarKeyReleased

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        // TODO add your handling code here:

        if (!txtQuantidade.getText().equals("")) {
            Bebida bebida = listabebidas.get(jTableBebidaPesquisa.getSelectedRow());
            ItemOrcamentoBebida item = new ItemOrcamentoBebida();
            item.setBebida(bebida);
            item.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            item.setOrcamento(orcamento);
            tfValorUnitario.setText(String.format("%.2f", bebida.getPrecoUnitario()));//exibo no campo de valor unitario
            item.setValorUnitario(Double.parseDouble(tfValorUnitario.getText().replace(",", ".")));//pegando o valor unitario do item
            dao.salvar(item);
            txtQuantidade.setText("");
            tfValorUnitario.setText("");

        } else {
            //JOptionPane.showMessageDialog(null, "Informe a Quantidade!", "Alerta", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(null, "Informe a Quantidade!");
            txtQuantidade.requestFocus();
        }


    }//GEN-LAST:event_btCadastrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValorUnitarioActionPerformed

    private void jTableBebidaPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBebidaPesquisaMouseClicked
        // TODO add your handling code here:
        Bebida b = listabebidas.get(jTableBebidaPesquisa.getSelectedRow());
        tfValorUnitario.setText(String.format("%.2f", b.getPrecoUnitario()));
    }//GEN-LAST:event_jTableBebidaPesquisaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PesquisaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaBebida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisaBebida dialog = new PesquisaBebida(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBebidaPesquisa;
    private javax.swing.JLabel lbValorUnitario;
    private javax.swing.JTextField tfValorUnitario;
    private javax.swing.JTextField txtLocalizar;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
