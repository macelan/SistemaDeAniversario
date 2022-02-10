/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifsp.scfa.view;

import edu.ifsp.scfa.control.ItemOrcamentoAlimentoDAO;
import edu.ifsp.scfa.control.ItemOrcamentoBebidaDAO;
import edu.ifsp.scfa.control.ItemOrcamentoUtensilioDAO;
import edu.ifsp.scfa.control.OrcamentoDAO;
import edu.ifsp.scfa.model.Bebida;
import edu.ifsp.scfa.model.Cliente;
import edu.ifsp.scfa.model.ItemOrcamentoAlimento;
import edu.ifsp.scfa.model.ItemOrcamentoBebida;
import edu.ifsp.scfa.model.ItemOrcamentoUtensilio;
import edu.ifsp.scfa.model.Orcamento;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macel
 */
public class VisaoCadastroOrcamento extends javax.swing.JDialog {

    /**
     * Creates new form VisaoBebida
     */
    //private final BebidaDAO bebidaDAO;// pode ser final, não vou modificar mais ele
    private OrcamentoDAO controle = new OrcamentoDAO();
    private List<Orcamento> listaorcamentos = new ArrayList();
    private List<ItemOrcamentoAlimento> alimentos = new ArrayList();
    private List<ItemOrcamentoBebida> bebidas = new ArrayList();
    private List<ItemOrcamentoUtensilio> utensilios = new ArrayList();
    //DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // o mes tem que ser maiusculo
    Orcamento f = new Orcamento();
    private Cliente cliente;
    //controladores dos itens
    private ItemOrcamentoAlimentoDAO contAlimento = new ItemOrcamentoAlimentoDAO();
    private ItemOrcamentoBebidaDAO contBebida = new ItemOrcamentoBebidaDAO();
    private ItemOrcamentoUtensilioDAO contUtensilio = new ItemOrcamentoUtensilioDAO();
    private double total = 0;
    private double maoDeObra = 0;
    private int qtdAlimento = 0;
    private int qtdBebida = 0;
    private int qtdUtensilio = 0;

    /**
     * METODO RECEBE UM CLIENTE VINDO DA TELA DE PESQUISA E SETA NO NOME DO
     * CLIENTE
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        txtNomeCliente.setText(cliente.getNome());
        f = controle.novoOrcamento(f);//persistindo um vazio para obter um id para prosseguir com o orçamento
    }

    /**
     * METODO REALIZA AS PESQUISAS DOS ITENS CONTIDOS NO ORÇAMENTO
     */
    public void carregarListasDeItens() {
        this.alimentos = contAlimento.listarTodos(f.getIdOrcamento());
        this.bebidas = contBebida.listarTodos(f.getIdOrcamento());
        this.utensilios = contUtensilio.listarTodos(f.getIdOrcamento());

    }

    /**
     * METODO ATUALIZA TODAS AS TABELAS DE ITENS DO ORÇAMENTO
     */
    public void atualizarTodasTabelas() {
        atualizaTabelaAlimento();
        atualizaTabelaBebida();
        atualizaTabelaUtensilio();
    }

    // TABELAS DOS ITENS DO ORÇAMENTO
    public void atualizaTabelaAlimento() {
        DefaultTableModel dtm = (DefaultTableModel) jTableOrcamentoAlimento.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (ItemOrcamentoAlimento f : alimentos) {
            //String.format("%.2f",f.getPrecoUnitario()) -> mostra com duas casa apos a virgula
            dtm.addRow(new Object[]{f.getAlimento().getNome(), String.format("%.2f", f.getValorUnitario()), f.getQuantidade(), String.format("%.2f", f.getQuantidade() * f.getValorUnitario())}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }

    public void atualizaTabelaBebida() {
        DefaultTableModel dtm = (DefaultTableModel) jTableOrcamentoBebida.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (ItemOrcamentoBebida f : bebidas) {
            //String.format("%.2f",f.getPrecoUnitario()) -> mostra com duas casa apos a virgula
            dtm.addRow(new Object[]{f.getBebida().getNome(), String.format("%.2f", f.getValorUnitario()), f.getQuantidade(), String.format("%.2f", f.getQuantidade() * f.getValorUnitario())}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }

    public void atualizaTabelaUtensilio() {
        DefaultTableModel dtm = (DefaultTableModel) jTableOrcamentoUtensilio.getModel(); // manipulador de tabela DefaultTableModel
        dtm.setNumRows(0);//apagando todas as linhas / o zero serva para apagar as linhas "é muito rapido"
        for (ItemOrcamentoUtensilio f : utensilios) {
            //String.format("%.2f",f.getPrecoUnitario()) -> mostra com duas casa apos a virgula
            dtm.addRow(new Object[]{f.getUtensilio().getNome(), String.format("%.2f", f.getValorUnitario()), f.getQuantidade(), String.format("%.2f", f.getQuantidade() * f.getValorUnitario())}); // Object é um vetor ou coleção com ele não pecisa converter
        }

    }
    //FIM DAS TABELAS DOS ITENS DO ORÇAMENTO

    /**
     * METODO REALIZA O CALCULO DO TOTAL DO ORÇAMENTO E QUANTIDADE DE ITENS
     */
    public void calcularTotal() {
        this.total = 0;
        this.maoDeObra = 0;
        this.qtdAlimento = 0;//limpando variavel para ter valor atualizado
        this.qtdBebida = 0;
        this.qtdUtensilio = 0;

        for (ItemOrcamentoAlimento i : alimentos) {

            this.total += i.getQuantidade() * i.getValorUnitario();
            this.qtdAlimento += i.getQuantidade();

        }
        for (ItemOrcamentoBebida it : bebidas) {

            this.total += it.getQuantidade() * it.getValorUnitario();
            this.qtdBebida += it.getQuantidade();

        }

        for (ItemOrcamentoUtensilio ut : utensilios) {

            this.total += ut.getQuantidade() * ut.getValorUnitario();
            this.maoDeObra += ut.getQuantidade() * ut.getValorUnitario();
            this.qtdUtensilio += ut.getQuantidade();

        }

        txtPrecoTotal.setText(String.format("%.2f", this.total));
        txtPrecoMaoDeObra.setText(String.format("%.2f", this.maoDeObra));

    }

    /**
     * METODO RECEBE UM CLIENTE VINDO DA TELA DE PESQUISA E SETA NO NOME DO
     * CLIENTE
     *
     * @param bebida
     */
    public void setBebida(Bebida bebida) {
        //   listabebida = this.bebida;
        txtNomeCliente.setText(cliente.getNome());
    }

    private void trataEdicao(boolean editando) {
        btnAlterar.setVisible(false);
        btnExcluir.setVisible(false);
        btnNovo.setVisible(false);
        btPesquisaCliente.setEnabled(editando);
        btAdicBebidas.setEnabled(!editando);
        txtNomeAniversariante.setEnabled(!editando);
        txtPrecoTotal.setEnabled(!editando);
        txtPrecoMaoDeObra.setEnabled(!editando);
        txtNomeCliente.setEnabled(!editando);
        jFormHora.setEnabled(!editando);
        //jDateChooserEvento.setEditable(editando);
        btPesquisaCliente.setVisible(false);
        jTableOrcamentoBebida.setEnabled(!editando);
    }

    public void limparCampos() {
        txtNomeAniversariante.setText("");
        //txtLocalizar.setText("");
        txtPrecoTotal.setText("");
        txtPrecoMaoDeObra.setText("");
        jComboBoxStatus.setSelectedIndex(0);
    }



    public void pesquisarDinamico(String comando) {
        this.listaorcamentos = controle.pesquisarOrcamentos(comando);
    }

    public VisaoCadastroOrcamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        trataEdicao(false);
    }

    public void vincularCampos() {
        Orcamento b = listaorcamentos.get(jTableOrcamentoBebida.getSelectedRow());// pega a linha da tabela
//        txtNome.setText(b.getNome());
//        txtPrecoUnitario.setText((String.format("%.2f", b.getPrecoUnitario()))); //Pega converte em String
//        jComboBoxOrcamento.setSelectedItem(b.getTipoOrcamento()); // pega a bebida co comboBox

    }

    private void pesquisar(java.awt.event.ActionEvent evt) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCabecalho = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        painelDados = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtNomeAniversariante = new javax.swing.JTextField();
        dataLabel1 = new javax.swing.JLabel();
        txtPrecoTotal = new javax.swing.JTextField();
        dataLabel2 = new javax.swing.JLabel();
        dataLabel3 = new javax.swing.JLabel();
        dataLabel4 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        btPesquisaCliente = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        jDtEvento = new com.toedter.calendar.JDateChooser();
        jFormHora = new javax.swing.JFormattedTextField();
        dataLabel5 = new javax.swing.JLabel();
        txtPrecoMaoDeObra = new javax.swing.JTextField();
        painelAcoes = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btSalvarOrcamento = new javax.swing.JButton();
        painelTabela = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrcamentoBebida = new javax.swing.JTable();
        btExcluirBebida = new javax.swing.JButton();
        btAdicBebidas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOrcamentoAlimento = new javax.swing.JTable();
        btExcluirAlimento = new javax.swing.JButton();
        btAdicAlimentacao = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableOrcamentoUtensilio = new javax.swing.JTable();
        btExcluirUtensilio = new javax.swing.JButton();
        btAdicUtensilios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelCabecalho.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro de Orçamento");

        javax.swing.GroupLayout panelCabecalhoLayout = new javax.swing.GroupLayout(panelCabecalho);
        panelCabecalho.setLayout(panelCabecalhoLayout);
        panelCabecalhoLayout.setHorizontalGroup(
            panelCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCabecalhoLayout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCabecalhoLayout.setVerticalGroup(
            panelCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCabecalhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        painelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Orçamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Unicode MS", 1, 18))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel10.setText("Nome Do Aniversáriante:");

        txtNomeAniversariante.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtNomeAniversariante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeAniversarianteActionPerformed(evt);
            }
        });

        dataLabel1.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        dataLabel1.setText("Total:");

        txtPrecoTotal.setEditable(false);
        txtPrecoTotal.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        txtPrecoTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoTotalActionPerformed(evt);
            }
        });

        dataLabel2.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        dataLabel2.setText("Data do Evento:");

        dataLabel3.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        dataLabel3.setText("Status:");

        dataLabel4.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        dataLabel4.setText("Hora do Evento:");

        jComboBoxStatus.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Status", "Cancelado", "Pendente", "Confirmado" }));
        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });

        btPesquisaCliente.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btPesquisaCliente.setText("Pesquisa");
        btPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisaClienteActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel11.setText("Nome Do Cliente:");

        txtNomeCliente.setEditable(false);
        txtNomeCliente.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });

        try {
            jFormHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        dataLabel5.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        dataLabel5.setText("Mão de Obra:");

        txtPrecoMaoDeObra.setEditable(false);
        txtPrecoMaoDeObra.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        txtPrecoMaoDeObra.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout painelDadosLayout = new javax.swing.GroupLayout(painelDados);
        painelDados.setLayout(painelDadosLayout);
        painelDadosLayout.setHorizontalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeAniversariante, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelDadosLayout.createSequentialGroup()
                        .addComponent(dataLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dataLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecoMaoDeObra, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dataLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDtEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(dataLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormHora, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dataLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );
        painelDadosLayout.setVerticalGroup(
            painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNomeAniversariante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisaCliente))
                .addGap(18, 18, 18)
                .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDtEvento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxStatus)
                        .addComponent(dataLabel3)
                        .addComponent(jFormHora)
                        .addComponent(dataLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosLayout.createSequentialGroup()
                        .addGroup(painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dataLabel1)
                            .addComponent(txtPrecoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecoMaoDeObra)
                        .addComponent(dataLabel5)
                        .addComponent(dataLabel2)))
                .addGap(21, 21, 21))
        );

        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acões", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Unicode MS", 1, 18))); // NOI18N

        btnNovo.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btSalvarOrcamento.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btSalvarOrcamento.setText("Salvar");
        btSalvarOrcamento.setToolTipText("clique para salvar o orçamento");
        btSalvarOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarOrcamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelAcoesLayout = new javax.swing.GroupLayout(painelAcoes);
        painelAcoes.setLayout(painelAcoesLayout);
        painelAcoesLayout.setHorizontalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35)
                .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(69, 69, 69)
                .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47)
                .addComponent(btSalvarOrcamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(299, 299, 299))
        );
        painelAcoesLayout.setVerticalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvarOrcamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );

        painelTabela.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionar Itens", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Unicode MS", 1, 14))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bebidas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Unicode MS", 1, 14))); // NOI18N

        jTableOrcamentoBebida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Preço", "Qtd", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableOrcamentoBebida);
        if (jTableOrcamentoBebida.getColumnModel().getColumnCount() > 0) {
            jTableOrcamentoBebida.getColumnModel().getColumn(1).setResizable(false);
            jTableOrcamentoBebida.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTableOrcamentoBebida.getColumnModel().getColumn(2).setResizable(false);
            jTableOrcamentoBebida.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTableOrcamentoBebida.getColumnModel().getColumn(3).setResizable(false);
            jTableOrcamentoBebida.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        btExcluirBebida.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btExcluirBebida.setText("Exclui");
        btExcluirBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirBebidaActionPerformed(evt);
            }
        });

        btAdicBebidas.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btAdicBebidas.setText("Adiciona");
        btAdicBebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicBebidasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdicBebidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btExcluirBebida, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addGap(105, 105, 105))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdicBebidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluirBebida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alimentações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Unicode MS", 1, 14))); // NOI18N

        jTableOrcamentoAlimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Preço", "Qtd", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableOrcamentoAlimento);
        if (jTableOrcamentoAlimento.getColumnModel().getColumnCount() > 0) {
            jTableOrcamentoAlimento.getColumnModel().getColumn(1).setResizable(false);
            jTableOrcamentoAlimento.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTableOrcamentoAlimento.getColumnModel().getColumn(2).setResizable(false);
            jTableOrcamentoAlimento.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTableOrcamentoAlimento.getColumnModel().getColumn(3).setResizable(false);
            jTableOrcamentoAlimento.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        btExcluirAlimento.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btExcluirAlimento.setText("Exclui");
        btExcluirAlimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirAlimentoActionPerformed(evt);
            }
        });

        btAdicAlimentacao.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btAdicAlimentacao.setText("Adiciona");
        btAdicAlimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicAlimentacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdicAlimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(btExcluirAlimento, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(106, 106, 106))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdicAlimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluirAlimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aluguel de Material", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Unicode MS", 1, 14))); // NOI18N

        jTableOrcamentoUtensilio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Mão de Obra", "Qtd", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableOrcamentoUtensilio);
        if (jTableOrcamentoUtensilio.getColumnModel().getColumnCount() > 0) {
            jTableOrcamentoUtensilio.getColumnModel().getColumn(1).setResizable(false);
            jTableOrcamentoUtensilio.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableOrcamentoUtensilio.getColumnModel().getColumn(2).setResizable(false);
            jTableOrcamentoUtensilio.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTableOrcamentoUtensilio.getColumnModel().getColumn(3).setResizable(false);
            jTableOrcamentoUtensilio.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        btExcluirUtensilio.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btExcluirUtensilio.setText("Exclui");
        btExcluirUtensilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirUtensilioActionPerformed(evt);
            }
        });

        btAdicUtensilios.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btAdicUtensilios.setText("Adiciona");
        btAdicUtensilios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicUtensiliosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdicUtensilios, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btExcluirUtensilio, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addGap(117, 117, 117))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdicUtensilios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluirUtensilio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout painelTabelaLayout = new javax.swing.GroupLayout(painelTabela);
        painelTabela.setLayout(painelTabelaLayout);
        painelTabelaLayout.setHorizontalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelTabelaLayout.setVerticalGroup(
            painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 26, Short.MAX_VALUE))
                            .addComponent(painelAcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(painelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCabecalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(painelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1132, 826));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeAniversarianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeAniversarianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeAniversarianteActionPerformed

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        trataEdicao(true);
        limparCampos();
        txtNomeAniversariante.requestFocus();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (jTableOrcamentoBebida.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para alterar", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            f = listaorcamentos.get(jTableOrcamentoBebida.getSelectedRow());
            trataEdicao(true);
            txtNomeAniversariante.requestFocus();
        }

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (jTableOrcamentoBebida.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela para excluir", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            Orcamento b = listaorcamentos.get(jTableOrcamentoBebida.getSelectedRow());
            controle.remover(b);
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jComboBoxOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBebidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBebidaActionPerformed

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteActionPerformed

    private void btPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisaClienteActionPerformed
        // TODO add your handling code here:
        PesquisaCliente tela = new PesquisaCliente(null, true);
        this.dispose();
        tela.setVisible(true);
    }//GEN-LAST:event_btPesquisaClienteActionPerformed

    private void btAdicBebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicBebidasActionPerformed
        // TODO add your handling code here:
        PesquisaBebida tela = new PesquisaBebida(null, true);
        tela.setOrcamento(f);// passando o orçamento para a tela de pesquisa de bebida para ser persistido a bebida escolhida
        tela.setVisible(true);
        carregarListasDeItens();//indo ao banco de dados em busca de novos itens
        atualizarTodasTabelas();
        calcularTotal();

    }//GEN-LAST:event_btAdicBebidasActionPerformed

    private void btAdicAlimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicAlimentacaoActionPerformed
        // TODO add your handling code here:
        PesquisaAlimento tela = new PesquisaAlimento(null, true);
        tela.setOrcamento(f);// passando o orçamento para a tela de pesquisa de bebida para ser persistido a bebida escolhida
        tela.setVisible(true);
        carregarListasDeItens();//indo ao banco de dados em busca de novos itens
        atualizarTodasTabelas();
        calcularTotal();
    }//GEN-LAST:event_btAdicAlimentacaoActionPerformed

    private void btAdicUtensiliosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicUtensiliosActionPerformed
        // TODO add your handling code here:
        PesquisaUtensilio tela = new PesquisaUtensilio(null, true);
        tela.setOrcamento(f);// passando o orçamento para a tela de pesquisa de bebida para ser persistido a bebida escolhida
        tela.setVisible(true);
        carregarListasDeItens();//indo ao banco de dados em busca de novos itens
        atualizarTodasTabelas();
        calcularTotal();
    }//GEN-LAST:event_btAdicUtensiliosActionPerformed

    private void btSalvarOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarOrcamentoActionPerformed
        // TODO add your handling code here:
        // NOVO CODIGO
        
        if (!txtNomeAniversariante.getText().equals("")) {
            if (jDtEvento.getDate()!= null) {
                if (!jFormHora.getText().equals("  :  ")) {
                    if (jComboBoxStatus.getSelectedIndex() != 0) {
                        DateFormat df = new SimpleDateFormat("HH:mm");
                        f.setCliente(cliente);
                        f.setDataEvento(jDtEvento.getDate());
                        f.setDataOrcamento(new Date());
                        try {
                            f.setHoraEvento(df.parse(jFormHora.getText()));
                        } catch (ParseException ex) {
                            System.out.println("ERRO AO CONVERTER PARA HORA " + ex);
                        }
                        f.setNomeDoAniversariante(txtNomeAniversariante.getText());
                        f.setStatus(String.valueOf(jComboBoxStatus.getSelectedItem()));
                        f.setValorTotal(this.total);
                        f.setValorTotalMaoDeObra(this.maoDeObra);
                        f.setQuantidadeAlimento(qtdAlimento);
                        f.setQuantidadeBebida(qtdBebida);
                        f.setQuantidadeUtensilio(qtdUtensilio);
                        controle.salvar(f);//salvar o orçamento

                    } else {
                        JOptionPane.showMessageDialog(null, "Informe a Confirmação do Orçamento!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        jDtEvento.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Informe o Horario do Evento!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    jFormHora.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha a data do Evento!", "Alerta", JOptionPane.WARNING_MESSAGE);
                jDtEvento.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha o nome do aniversariante!", "Alerta", JOptionPane.WARNING_MESSAGE);
            txtNomeAniversariante.requestFocus();
        }

    }//GEN-LAST:event_btSalvarOrcamentoActionPerformed

    private void btExcluirBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirBebidaActionPerformed
        // TODO add your handling code here:

        ItemOrcamentoBebida item = bebidas.get(jTableOrcamentoBebida.getSelectedRow());
        contBebida.excluir(item);//removendo o item
        carregarListasDeItens();
        atualizarTodasTabelas();
        calcularTotal();


    }//GEN-LAST:event_btExcluirBebidaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btExcluirAlimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirAlimentoActionPerformed
        // TODO add your handling code here:
        ItemOrcamentoAlimento item = alimentos.get(jTableOrcamentoAlimento.getSelectedRow());
        contAlimento.excluir(item);//removendo o item
        carregarListasDeItens();
        atualizarTodasTabelas();
        calcularTotal();
    }//GEN-LAST:event_btExcluirAlimentoActionPerformed

    private void btExcluirUtensilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirUtensilioActionPerformed
        // TODO add your handling code here:
        ItemOrcamentoUtensilio item = utensilios.get(jTableOrcamentoUtensilio.getSelectedRow());
        contUtensilio.excluir(item);//removendo o item
        carregarListasDeItens();
        atualizarTodasTabelas();
        calcularTotal();
    }//GEN-LAST:event_btExcluirUtensilioActionPerformed

    private void txtPrecoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoTotalActionPerformed

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
            java.util.logging.Logger.getLogger(VisaoCadastroOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisaoCadastroOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisaoCadastroOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisaoCadastroOrcamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VisaoCadastroOrcamento dialog = new VisaoCadastroOrcamento(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btAdicAlimentacao;
    private javax.swing.JButton btAdicBebidas;
    private javax.swing.JButton btAdicUtensilios;
    private javax.swing.JButton btExcluirAlimento;
    private javax.swing.JButton btExcluirBebida;
    private javax.swing.JButton btExcluirUtensilio;
    private javax.swing.JButton btPesquisaCliente;
    private javax.swing.JButton btSalvarOrcamento;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel dataLabel1;
    private javax.swing.JLabel dataLabel2;
    private javax.swing.JLabel dataLabel3;
    private javax.swing.JLabel dataLabel4;
    private javax.swing.JLabel dataLabel5;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private com.toedter.calendar.JDateChooser jDtEvento;
    private javax.swing.JFormattedTextField jFormHora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableOrcamentoAlimento;
    private javax.swing.JTable jTableOrcamentoBebida;
    private javax.swing.JTable jTableOrcamentoUtensilio;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelDados;
    private javax.swing.JPanel painelTabela;
    private javax.swing.JPanel panelCabecalho;
    private javax.swing.JTextField txtNomeAniversariante;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtPrecoMaoDeObra;
    private javax.swing.JTextField txtPrecoTotal;
    // End of variables declaration//GEN-END:variables

    private void PesquisaCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
