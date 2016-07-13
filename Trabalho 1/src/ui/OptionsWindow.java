package ui;

import java.util.ArrayList;
import javax.swing.JFrame;
import trabalhoia.Negocio;
import trabalhoia.Estrutura;
import trabalhoia.Produto;
import trabalhoia.Settings;

/**
 *
 * @author Pedro
 */
public class OptionsWindow extends javax.swing.JFrame {
    
    int tipoGame;
    
    public OptionsWindow(int tipoGame) {
        initComponents();
        this.tipoGame = tipoGame;
        myInits();
    }

    private void myInits(){
        setLocationRelativeTo(null);
        setResizable(false);
        if (MenuWindow.debugMode)
            System.out.println("Janela de Options Aberta, Tipo do Game: " + tipoGame);
        
        switch(tipoGame){
            case 1:
                lblJogador1.setText("Jogador 1");
                lblJogador2.setText("Jogador 2");
                break;
            case 2:
                lblJogador1.setText("Jogador 1");
                lblJogador2.setText("Computador 1");
                break;
            case 3:
                lblJogador1.setText("Computador 1");
                lblJogador2.setText("Computador 2");
                break;
            default:
                break;
        }
        
        ArrayList<Negocio> negocios = MenuWindow.getNegocios();
        for (Negocio n: negocios)
            ramo_negocio.addItem(n.ramo);
        
        selectInvestimentoInicial.addItem("50000.00");
        selectInvestimentoInicial.addItem("100000.00");
        selectInvestimentoInicial.addItem("500000.00");
        
    }
    
    private String getNomeEstruturaSelecionada(int jogador){
        String nomeEstrutura = "";
        if (jogador == 1){
            String valorSelect = String.valueOf(selectPredio.getItemAt(selectPredio.getSelectedIndex()));
            nomeEstrutura = valorSelect.split(" \\|", 2)[0];
        } else {            
            String valorSelect = String.valueOf(selectPredio2.getItemAt(selectPredio2.getSelectedIndex()));
            nomeEstrutura = valorSelect.split(" \\|", 2)[0];
        }
        return nomeEstrutura;
    }
    
    private Estrutura getEstrutura(ArrayList<Estrutura> estruturas, String nome){
        for (Estrutura e: estruturas)
            if (e.nome.equals(nome)) return e;
        return estruturas.get(0);
    }
    
    private Produto getProduto(ArrayList<Produto> produtos, String nome){
        for (Produto p: produtos)
            if (p.nome.equals(nome)) return p;
        return produtos.get(0);
    }
    
    private void loadProdutos(Negocio negocio){
        checkboxProd1.setText(negocio.produtos.get(0).nome);
        checkboxProd2.setText(negocio.produtos.get(1).nome);
        checkboxProd3.setText(negocio.produtos.get(2).nome);
        
        preco_producao1.setText(negocio.produtos.get(0).precoProduzir + "");
        preco_producao2.setText(negocio.produtos.get(1).precoProduzir + "");
        preco_producao3.setText(negocio.produtos.get(2).precoProduzir + "");
    }
    
    private void loadProdutos2(Negocio negocio){
        checkboxProd21.setText(negocio.produtos.get(0).nome);
        checkboxProd22.setText(negocio.produtos.get(1).nome);
        checkboxProd23.setText(negocio.produtos.get(2).nome);
        
        preco_producao21.setText(negocio.produtos.get(0).precoProduzir + "");
        preco_producao22.setText(negocio.produtos.get(1).precoProduzir + "");
        preco_producao23.setText(negocio.produtos.get(2).precoProduzir + "");
    }
    private void disableProdutos(int nivel){
        if (nivel == 0){
            checkboxProd1.setEnabled(true);
            checkboxProd2.setEnabled(false);
            checkboxProd3.setEnabled(false);
            
            lblPrecoProd1.setEnabled(true);
            lblPrecoProd2.setEnabled(false);
            lblPrecoProd3.setEnabled(false);
            
            lblPrecoVenda1.setEnabled(true);
            lblPrecoVenda2.setEnabled(false);
            lblPrecoVenda3.setEnabled(false);
            
            lblQta1.setEnabled(true);
            lblQta2.setEnabled(false);
            lblQta3.setEnabled(false);
            
            preco_producao1.setEnabled(true);
            preco_producao2.setEnabled(false);
            preco_producao3.setEnabled(false);
            
            preco_venda1.setEnabled(true);
            preco_venda2.setEnabled(false);
            preco_venda3.setEnabled(false);
        } else if (nivel == 1){
            checkboxProd1.setEnabled(true);
            checkboxProd2.setEnabled(true);
            checkboxProd3.setEnabled(false);
            
            lblPrecoProd1.setEnabled(true);
            lblPrecoProd2.setEnabled(true);
            lblPrecoProd3.setEnabled(false);
            
            lblPrecoVenda1.setEnabled(true);
            lblPrecoVenda2.setEnabled(true);
            lblPrecoVenda3.setEnabled(false);
            
            lblQta1.setEnabled(true);
            lblQta2.setEnabled(true);
            lblQta3.setEnabled(false);
            
            preco_producao1.setEnabled(true);
            preco_producao2.setEnabled(true);
            preco_producao3.setEnabled(false);
            
            preco_venda1.setEnabled(true);
            preco_venda2.setEnabled(true);
            preco_venda3.setEnabled(false);
        } else if (nivel == 2){
            checkboxProd1.setEnabled(true);
            checkboxProd2.setEnabled(true);
            checkboxProd3.setEnabled(true);
            
            lblPrecoProd1.setEnabled(true);
            lblPrecoProd2.setEnabled(true);
            lblPrecoProd3.setEnabled(true);
            
            lblPrecoVenda1.setEnabled(true);
            lblPrecoVenda2.setEnabled(true);
            lblPrecoVenda3.setEnabled(true);
            
            lblQta1.setEnabled(true);
            lblQta2.setEnabled(true);
            lblQta3.setEnabled(true);
            
            preco_producao1.setEnabled(true);
            preco_producao2.setEnabled(true);
            preco_producao3.setEnabled(true);
            
            preco_venda1.setEnabled(true);
            preco_venda2.setEnabled(true);
            preco_venda3.setEnabled(true);
        }
    }
    
    private void disableProdutos2(int nivel){
        if (nivel == 0){
            checkboxProd21.setEnabled(true);
            checkboxProd22.setEnabled(false);
            checkboxProd23.setEnabled(false);
            
            lblPrecoProd21.setEnabled(true);
            lblPrecoProd22.setEnabled(false);
            lblPrecoProd23.setEnabled(false);
            
            lblPrecoVenda21.setEnabled(true);
            lblPrecoVenda22.setEnabled(false);
            lblPrecoVenda23.setEnabled(false);
            
            lblQta21.setEnabled(true);
            lblQta22.setEnabled(false);
            lblQta23.setEnabled(false);
            
            preco_producao21.setEnabled(true);
            preco_producao22.setEnabled(false);
            preco_producao23.setEnabled(false);
            
            preco_venda21.setEnabled(true);
            preco_venda22.setEnabled(false);
            preco_venda23.setEnabled(false);
        } else if (nivel == 1){
            checkboxProd21.setEnabled(true);
            checkboxProd22.setEnabled(true);
            checkboxProd23.setEnabled(false);
            
            lblPrecoProd21.setEnabled(true);
            lblPrecoProd22.setEnabled(true);
            lblPrecoProd23.setEnabled(false);
            
            lblPrecoVenda21.setEnabled(true);
            lblPrecoVenda22.setEnabled(true);
            lblPrecoVenda23.setEnabled(false);
            
            lblQta21.setEnabled(true);
            lblQta22.setEnabled(true);
            lblQta23.setEnabled(false);
            
            preco_producao21.setEnabled(true);
            preco_producao22.setEnabled(true);
            preco_producao23.setEnabled(false);
            
            preco_venda21.setEnabled(true);
            preco_venda22.setEnabled(true);
            preco_venda23.setEnabled(false);
        } else if (nivel == 2){
            checkboxProd21.setEnabled(true);
            checkboxProd22.setEnabled(true);
            checkboxProd23.setEnabled(true);
            
            lblPrecoProd21.setEnabled(true);
            lblPrecoProd22.setEnabled(true);
            lblPrecoProd23.setEnabled(true);
            
            lblPrecoVenda21.setEnabled(true);
            lblPrecoVenda22.setEnabled(true);
            lblPrecoVenda23.setEnabled(true);
            
            lblQta21.setEnabled(true);
            lblQta22.setEnabled(true);
            lblQta23.setEnabled(true);
            
            preco_producao21.setEnabled(true);
            preco_producao22.setEnabled(true);
            preco_producao23.setEnabled(true);
            
            preco_venda21.setEnabled(true);
            preco_venda22.setEnabled(true);
            preco_venda23.setEnabled(true);
        }
    }
    
    private double calculaDinheiroTotal(){
        double inicial = Double.valueOf(selectInvestimentoInicial.getItemAt(selectInvestimentoInicial.getSelectedIndex()) + "");
        
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(1));
        inicial -= estruturaSelecionada.preco;
        inicial -= estruturaSelecionada.gastoFixo;
        
        int qta = 0;
        double valor = 0.0;
        if (Integer.valueOf(qta_prod1.getText()) > 0){
            qta = Integer.valueOf(qta_prod1.getText());
            valor = Double.valueOf(preco_producao1.getText());
            inicial -= qta * valor;
        }
        if (Integer.valueOf(qta_prod2.getText()) > 0){
            qta = Integer.valueOf(qta_prod2.getText());
            valor = Double.valueOf(preco_producao2.getText());
            inicial -= qta * valor;
        }
        if (Integer.valueOf(qta_prod3.getText()) > 0){
            qta = Integer.valueOf(qta_prod3.getText());
            valor = Double.valueOf(preco_producao3.getText());
            inicial -= qta * valor;
        }
        
        if (Double.valueOf(gasto_propaganda.getText()) > 0 && !gasto_propaganda.getText().equals(""))
            inicial -= Double.valueOf(gasto_propaganda.getText());
        
        return inicial;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        qta_prod1 = new javax.swing.JTextField();
        lblQta1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        gasto_propaganda = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        meses = new javax.swing.JTextField();
        lblJogador1 = new javax.swing.JLabel();
        selectPredio = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ramo_negocio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        selectInvestimentoInicial = new javax.swing.JComboBox();
        btnDuvida = new javax.swing.JButton();
        lblPrecoProd1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        investimento_inicial = new javax.swing.JLabel();
        lblPrecoVenda1 = new javax.swing.JLabel();
        preco_producao1 = new javax.swing.JTextField();
        preco_venda1 = new javax.swing.JTextField();
        qta_prod2 = new javax.swing.JTextField();
        lblQta2 = new javax.swing.JLabel();
        lblPrecoProd2 = new javax.swing.JLabel();
        lblPrecoVenda2 = new javax.swing.JLabel();
        preco_producao2 = new javax.swing.JTextField();
        preco_venda2 = new javax.swing.JTextField();
        checkboxProd1 = new javax.swing.JCheckBox();
        checkboxProd2 = new javax.swing.JCheckBox();
        qta_prod3 = new javax.swing.JTextField();
        lblQta3 = new javax.swing.JLabel();
        lblPrecoProd3 = new javax.swing.JLabel();
        lblPrecoVenda3 = new javax.swing.JLabel();
        preco_producao3 = new javax.swing.JTextField();
        preco_venda3 = new javax.swing.JTextField();
        checkboxProd3 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        qta_prod21 = new javax.swing.JTextField();
        lblQta21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        gasto_propaganda2 = new javax.swing.JTextField();
        lblJogador2 = new javax.swing.JLabel();
        selectPredio2 = new javax.swing.JComboBox();
        btnDuvida2 = new javax.swing.JButton();
        lblPrecoProd21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblPrecoVenda21 = new javax.swing.JLabel();
        preco_producao21 = new javax.swing.JTextField();
        preco_venda21 = new javax.swing.JTextField();
        qta_prod22 = new javax.swing.JTextField();
        lblQta22 = new javax.swing.JLabel();
        lblPrecoProd22 = new javax.swing.JLabel();
        lblPrecoVenda22 = new javax.swing.JLabel();
        preco_producao22 = new javax.swing.JTextField();
        preco_venda22 = new javax.swing.JTextField();
        checkboxProd21 = new javax.swing.JCheckBox();
        checkboxProd22 = new javax.swing.JCheckBox();
        qta_prod23 = new javax.swing.JTextField();
        lblQta23 = new javax.swing.JLabel();
        lblPrecoProd23 = new javax.swing.JLabel();
        lblPrecoVenda23 = new javax.swing.JLabel();
        preco_producao23 = new javax.swing.JTextField();
        preco_venda23 = new javax.swing.JTextField();
        checkboxProd23 = new javax.swing.JCheckBox();
        btnAtualizaValor = new javax.swing.JButton();
        btnAtualizaValor2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings - Company Wars");
        setMinimumSize(new java.awt.Dimension(810, 656));
        setPreferredSize(new java.awt.Dimension(810, 656));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Configurações do Jogo");

        jLabel3.setText("Estruturas:");

        qta_prod1.setText("0");
        qta_prod1.setToolTipText("O formado do Input deve ser: 99.99");
        qta_prod1.setEnabled(false);
        qta_prod1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qta_prod1FocusLost(evt);
            }
        });

        lblQta1.setText("Qta.");

        jLabel5.setText("Produtos:");

        btnStart.setText("Começar o Jogo");
        btnStart.setPreferredSize(new java.awt.Dimension(150, 35));
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        jLabel8.setText("Gasto com Propaganda:");

        gasto_propaganda.setText("00.00");
        gasto_propaganda.setToolTipText("O formado do Input deve ser: 99.99");

        jLabel10.setText("Tempo Máximo de Jogo:");
        jLabel10.setToolTipText("Defina a quantidade de meses para o final do jogo.");

        meses.setToolTipText("Tempo em Meses");

        lblJogador1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblJogador1.setText("Jogador 1");

        selectPredio.setToolTipText("Escolha qual o tipo de estrutura deseja começar.");
        selectPredio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPredioActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText("*coloque o cursor sobre os campos para informações");

        jLabel21.setText("Ramo do Negócio:");

        ramo_negocio.setToolTipText("Tipo do Negócio deste jogo");
        ramo_negocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ramo_negocioActionPerformed(evt);
            }
        });

        jLabel2.setText("Meses");

        jLabel9.setText("Investimento Inicial:");

        selectInvestimentoInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInvestimentoInicialActionPerformed(evt);
            }
        });

        btnDuvida.setText("?");
        btnDuvida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuvidaActionPerformed(evt);
            }
        });

        lblPrecoProd1.setText("Preço de Produção:");

        jLabel14.setText("Dinheiro Total:");

        investimento_inicial.setText("50000.00");

        lblPrecoVenda1.setText("Preço de Venda:");

        preco_producao1.setEditable(false);

        qta_prod2.setText("0");
        qta_prod2.setToolTipText("O formado do Input deve ser: 99.99");
        qta_prod2.setEnabled(false);
        qta_prod2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qta_prod2FocusLost(evt);
            }
        });

        lblQta2.setText("Qta.");

        lblPrecoProd2.setText("Preço de Produção:");

        lblPrecoVenda2.setText("Preço de Venda:");

        preco_producao2.setEditable(false);

        checkboxProd1.setText("Produto 1");
        checkboxProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxProd1ActionPerformed(evt);
            }
        });

        checkboxProd2.setText("Produto 2");
        checkboxProd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxProd2ActionPerformed(evt);
            }
        });

        qta_prod3.setText("0");
        qta_prod3.setToolTipText("O formado do Input deve ser: 99.99");
        qta_prod3.setEnabled(false);
        qta_prod3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qta_prod3FocusLost(evt);
            }
        });

        lblQta3.setText("Qta.");

        lblPrecoProd3.setText("Preço de Produção:");

        lblPrecoVenda3.setText("Preço de Venda:");

        preco_producao3.setEditable(false);

        checkboxProd3.setText("Produto 3");
        checkboxProd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxProd3ActionPerformed(evt);
            }
        });

        jLabel12.setText("Estruturas:");

        qta_prod21.setText("0");
        qta_prod21.setToolTipText("O formado do Input deve ser: 99.99");
        qta_prod21.setEnabled(false);
        qta_prod21.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qta_prod21FocusLost(evt);
            }
        });

        lblQta21.setText("Qta.");

        jLabel23.setText("Produtos:");

        jLabel24.setText("Gasto com Propaganda:");

        gasto_propaganda2.setToolTipText("O formado do Input deve ser: 99.99");

        lblJogador2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblJogador2.setText("Jogador 2");

        selectPredio2.setToolTipText("Escolha qual o tipo de estrutura deseja começar.");
        selectPredio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPredio2ActionPerformed(evt);
            }
        });

        btnDuvida2.setText("?");
        btnDuvida2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuvida2ActionPerformed(evt);
            }
        });

        lblPrecoProd21.setText("Preço de Produção:");

        jLabel26.setText("Dinheiro Total:");

        jLabel30.setText("50000.00");

        lblPrecoVenda21.setText("Preço de Venda:");

        preco_producao21.setEditable(false);

        qta_prod22.setText("0");
        qta_prod22.setToolTipText("O formado do Input deve ser: 99.99");
        qta_prod22.setEnabled(false);
        qta_prod22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qta_prod22FocusLost(evt);
            }
        });

        lblQta22.setText("Qta.");

        lblPrecoProd22.setText("Preço de Produção:");

        lblPrecoVenda22.setText("Preço de Venda:");

        preco_producao22.setEditable(false);

        checkboxProd21.setText("Produto 1");
        checkboxProd21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxProd21ActionPerformed(evt);
            }
        });

        checkboxProd22.setText("Produto 2");
        checkboxProd22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxProd22ActionPerformed(evt);
            }
        });

        qta_prod23.setText("0");
        qta_prod23.setToolTipText("O formado do Input deve ser: 99.99");
        qta_prod23.setEnabled(false);
        qta_prod23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                qta_prod23FocusLost(evt);
            }
        });

        lblQta23.setText("Qta.");

        lblPrecoProd23.setText("Preço de Produção:");

        lblPrecoVenda23.setText("Preço de Venda:");

        preco_producao23.setEditable(false);

        checkboxProd23.setText("Produto 3");
        checkboxProd23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxProd23ActionPerformed(evt);
            }
        });

        btnAtualizaValor.setText("!");
        btnAtualizaValor.setToolTipText("Atualizar valor");
        btnAtualizaValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizaValorActionPerformed(evt);
            }
        });

        btnAtualizaValor2.setText("!");
        btnAtualizaValor2.setToolTipText("Atualizar valor");
        btnAtualizaValor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizaValor2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addGap(206, 206, 206)
                        .addComponent(jLabel9)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(checkboxProd1))
                        .addGap(57, 57, 57)
                        .addComponent(lblQta1)
                        .addGap(8, 8, 8)
                        .addComponent(qta_prod1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(meses, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)))
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(checkboxProd21))
                        .addGap(57, 57, 57)
                        .addComponent(lblQta21)
                        .addGap(8, 8, 8)
                        .addComponent(qta_prod21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblPrecoProd1)
                        .addGap(6, 6, 6)
                        .addComponent(preco_producao1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(lblPrecoProd21)
                        .addGap(6, 6, 6)
                        .addComponent(preco_producao21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblPrecoVenda1)
                        .addGap(21, 21, 21)
                        .addComponent(preco_venda1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(lblPrecoVenda21)
                        .addGap(21, 21, 21)
                        .addComponent(preco_venda21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(checkboxProd2)
                        .addGap(57, 57, 57)
                        .addComponent(lblQta2)
                        .addGap(8, 8, 8)
                        .addComponent(qta_prod2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(checkboxProd22)
                        .addGap(57, 57, 57)
                        .addComponent(lblQta22)
                        .addGap(8, 8, 8)
                        .addComponent(qta_prod22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblPrecoProd2)
                        .addGap(6, 6, 6)
                        .addComponent(preco_producao2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(lblPrecoProd22)
                        .addGap(6, 6, 6)
                        .addComponent(preco_producao22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblPrecoVenda2)
                        .addGap(21, 21, 21)
                        .addComponent(preco_venda2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(lblPrecoVenda22)
                        .addGap(21, 21, 21)
                        .addComponent(preco_venda22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(checkboxProd3)
                        .addGap(57, 57, 57)
                        .addComponent(lblQta3)
                        .addGap(8, 8, 8)
                        .addComponent(qta_prod3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(checkboxProd23)
                        .addGap(57, 57, 57)
                        .addComponent(lblQta23)
                        .addGap(8, 8, 8)
                        .addComponent(qta_prod23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblPrecoProd3)
                        .addGap(6, 6, 6)
                        .addComponent(preco_producao3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(lblPrecoProd23)
                        .addGap(6, 6, 6)
                        .addComponent(preco_producao23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lblPrecoVenda3)
                        .addGap(21, 21, 21)
                        .addComponent(preco_venda3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(lblPrecoVenda23)
                        .addGap(21, 21, 21)
                        .addComponent(preco_venda23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel8)
                        .addGap(5, 5, 5)
                        .addComponent(gasto_propaganda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(jLabel24)
                        .addGap(5, 5, 5)
                        .addComponent(gasto_propaganda2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addComponent(jLabel19))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblJogador1)
                                    .addComponent(jLabel14))
                                .addGap(1, 1, 1)
                                .addComponent(investimento_inicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(selectPredio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDuvida, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnAtualizaValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectInvestimentoInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(ramo_negocio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJogador2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(selectPredio2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnDuvida2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(btnAtualizaValor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblJogador1)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(6, 6, 6)
                        .addComponent(ramo_negocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblJogador2)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel30)
                            .addComponent(btnAtualizaValor2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(investimento_inicial)
                            .addComponent(btnAtualizaValor))))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectPredio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDuvida)
                    .addComponent(selectInvestimentoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectPredio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDuvida2))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(checkboxProd1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblQta1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(qta_prod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(6, 6, 6)
                        .addComponent(checkboxProd21))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblQta21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(qta_prod21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecoProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_producao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecoProd21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_producao21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecoVenda1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_venda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecoVenda21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_venda21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkboxProd2)
                            .addComponent(lblQta2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qta_prod2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxProd22)
                            .addComponent(lblQta22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qta_prod22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecoProd2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_producao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecoProd22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_producao22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecoVenda2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_venda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecoVenda22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_venda22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkboxProd3)
                    .addComponent(lblQta3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qta_prod3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxProd23)
                    .addComponent(lblQta23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qta_prod23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecoProd3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_producao3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecoProd23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_producao23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecoVenda3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_venda3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecoVenda23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preco_venda23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gasto_propaganda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gasto_propaganda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        Settings settings = new Settings(); // estrutura, produto, preco, propaganda, gasto fixo, tempo fab, meses

        
        //String texto = investimento_inicial.getText().split(" ")[1];
        //if (!texto.equals(""))
            //settings.investimentoInicial = Double.valueOf(texto);
        //texto = investimento_inicial2.getText().split(" ")[1];
        //if (!texto.equals(""))
          // settings.investimentoInicial2 = Double.valueOf(texto);
       
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        settings.negocio = negocio;
       
        settings.predio = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(1));
        settings.predio2 = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(2));
        
        //texto = selectProduto.getItemAt(selectProduto.getSelectedIndex()).toString();
        //settings.produto = getProduto(settings.negocio.produtos, texto);
        //texto = selectProduto2.getItemAt(selectProduto2.getSelectedIndex()).toString();
        //settings.produto2 = getProduto(settings.negocio.produtos, texto);
        
        if (!qta_prod1.getText().equals("")){
            //settings.precoProduto = Double.valueOf(qta_prod1.getText());
        } else {
            if (MenuWindow.debugMode)
                System.out.println("Produto 1 sem preço");
            // TODO: popup, não começar o jogo
        } 
        if (!qta_prod21.getText().equals("")){
            //settings.precoProduto2 = Double.valueOf(qta_prod21.getText());
        } else{
            if (MenuWindow.debugMode)
                System.out.println("Produto 2 sem preço");
            // TODO: popup, não começar o jogo
        } 
           
        if (!gasto_propaganda.getText().equals(""))
            settings.gastoPropaganda = Double.valueOf(gasto_propaganda.getText());
        //if (!gasto_propaganda2.getText().equals(""))
        //    settings.gastoPropaganda2 = Double.valueOf(gasto_propaganda2.getText());
        
        //if (!gasto_fixo.getText().equals(""))
        //    settings.gastosFixos = Double.valueOf(gasto_fixo.getText());
        //if (!gasto_fixo2.getText().equals(""))
         //   settings.gastosFixos2 = Double.valueOf(gasto_fixo2.getText());
        
        //if (!tempo_fabricacao.getText().equals(""))
        //    settings.tempoFabricacao = Integer.valueOf(tempo_fabricacao.getText());
        //if (!tempo_fabricacao2.getText().equals(""))
         //   settings.tempoFabricacao2 = Integer.valueOf(tempo_fabricacao2.getText());
        
        if (!meses.getText().equals("")){
            settings.maximoMeses = Integer.valueOf(meses.getText());
        } else {
            System.out.println("Tempo de Jogo não especificado");
            System.out.println("Padrão: 12 meses");
            settings.maximoMeses = 12;
        }
        
        settings.rodada = 0;
        
        dispose();
        new GameWindow(tipoGame, settings).setVisible(true);
    }//GEN-LAST:event_btnStartActionPerformed

    private void ramo_negocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ramo_negocioActionPerformed
        selectPredio.removeAllItems();        
        selectPredio2.removeAllItems();

        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        for (Estrutura e: negocio.predios){
            selectPredio.addItem(e.nome);
            selectPredio2.addItem(e.nome);
        }
    }//GEN-LAST:event_ramo_negocioActionPerformed

    private void selectPredioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPredioActionPerformed
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(1));
        
        loadProdutos(negocio);
        disableProdutos(estruturaSelecionada.nivel);
    }//GEN-LAST:event_selectPredioActionPerformed

    private void btnDuvidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuvidaActionPerformed
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(1));
        new InstruEstruturaWindow(estruturaSelecionada, negocio).setVisible(true);
    }//GEN-LAST:event_btnDuvidaActionPerformed

    private void selectPredio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPredio2ActionPerformed
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(2));
        
        loadProdutos2(negocio);
        disableProdutos2(estruturaSelecionada.nivel);
    }//GEN-LAST:event_selectPredio2ActionPerformed

    private void btnDuvida2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuvida2ActionPerformed
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(2));
        new InstruEstruturaWindow(estruturaSelecionada, negocio).setVisible(true);
    }//GEN-LAST:event_btnDuvida2ActionPerformed

    private void selectInvestimentoInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectInvestimentoInicialActionPerformed
        investimento_inicial.setText(selectInvestimentoInicial.getItemAt(selectInvestimentoInicial.getSelectedIndex()) + "");
    }//GEN-LAST:event_selectInvestimentoInicialActionPerformed

    private void qta_prod1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qta_prod1FocusLost
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(1));
        if (Integer.valueOf(qta_prod1.getText()) > estruturaSelecionada.producaoMensalPorNivel[0])
            qta_prod1.setText(estruturaSelecionada.producaoMensalPorNivel[0] + "");
    }//GEN-LAST:event_qta_prod1FocusLost

    private void checkboxProd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxProd2ActionPerformed
        qta_prod2.setEnabled(checkboxProd2.isSelected());
    }//GEN-LAST:event_checkboxProd2ActionPerformed

    private void checkboxProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxProd1ActionPerformed
        qta_prod1.setEnabled(checkboxProd1.isSelected());
    }//GEN-LAST:event_checkboxProd1ActionPerformed

    private void checkboxProd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxProd3ActionPerformed
        qta_prod3.setEnabled(checkboxProd3.isSelected());
    }//GEN-LAST:event_checkboxProd3ActionPerformed

    private void btnAtualizaValor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaValor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizaValor2ActionPerformed

    private void btnAtualizaValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaValorActionPerformed
        investimento_inicial.setText(calculaDinheiroTotal() + "");
    }//GEN-LAST:event_btnAtualizaValorActionPerformed

    private void qta_prod2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qta_prod2FocusLost
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(1));
        if (Integer.valueOf(qta_prod2.getText()) > estruturaSelecionada.producaoMensalPorNivel[1])
            qta_prod2.setText(estruturaSelecionada.producaoMensalPorNivel[1] + "");
    }//GEN-LAST:event_qta_prod2FocusLost

    private void qta_prod3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qta_prod3FocusLost
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(1));
        if (Integer.valueOf(qta_prod3.getText()) > estruturaSelecionada.producaoMensalPorNivel[2])
            qta_prod3.setText(estruturaSelecionada.producaoMensalPorNivel[2] + "");
    }//GEN-LAST:event_qta_prod3FocusLost

    private void checkboxProd21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxProd21ActionPerformed
        qta_prod21.setEnabled(checkboxProd21.isSelected());
    }//GEN-LAST:event_checkboxProd21ActionPerformed

    private void checkboxProd22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxProd22ActionPerformed
        qta_prod22.setEnabled(checkboxProd22.isSelected());
    }//GEN-LAST:event_checkboxProd22ActionPerformed

    private void checkboxProd23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxProd23ActionPerformed
        qta_prod23.setEnabled(checkboxProd23.isSelected());
    }//GEN-LAST:event_checkboxProd23ActionPerformed

    private void qta_prod21FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qta_prod21FocusLost
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(2));
        if (Integer.valueOf(qta_prod21.getText()) > estruturaSelecionada.producaoMensalPorNivel[0])
            qta_prod21.setText(estruturaSelecionada.producaoMensalPorNivel[0] + "");
    }//GEN-LAST:event_qta_prod21FocusLost

    private void qta_prod22FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qta_prod22FocusLost
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(2));
        if (Integer.valueOf(qta_prod22.getText()) > estruturaSelecionada.producaoMensalPorNivel[1])
            qta_prod22.setText(estruturaSelecionada.producaoMensalPorNivel[1] + "");
    }//GEN-LAST:event_qta_prod22FocusLost

    private void qta_prod23FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qta_prod23FocusLost
        Negocio negocio = MenuWindow.getNegocio(String.valueOf(ramo_negocio.getSelectedItem()));
        Estrutura estruturaSelecionada = getEstrutura(negocio.predios, getNomeEstruturaSelecionada(2));
        if (Integer.valueOf(qta_prod23.getText()) > estruturaSelecionada.producaoMensalPorNivel[2])
            qta_prod23.setText(estruturaSelecionada.producaoMensalPorNivel[2] + "");
    }//GEN-LAST:event_qta_prod23FocusLost

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizaValor;
    private javax.swing.JButton btnAtualizaValor2;
    private javax.swing.JButton btnDuvida;
    private javax.swing.JButton btnDuvida2;
    private javax.swing.JButton btnStart;
    private javax.swing.JCheckBox checkboxProd1;
    private javax.swing.JCheckBox checkboxProd2;
    private javax.swing.JCheckBox checkboxProd21;
    private javax.swing.JCheckBox checkboxProd22;
    private javax.swing.JCheckBox checkboxProd23;
    private javax.swing.JCheckBox checkboxProd3;
    private javax.swing.JTextField gasto_propaganda;
    private javax.swing.JTextField gasto_propaganda2;
    private javax.swing.JLabel investimento_inicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblJogador1;
    private javax.swing.JLabel lblJogador2;
    private javax.swing.JLabel lblPrecoProd1;
    private javax.swing.JLabel lblPrecoProd2;
    private javax.swing.JLabel lblPrecoProd21;
    private javax.swing.JLabel lblPrecoProd22;
    private javax.swing.JLabel lblPrecoProd23;
    private javax.swing.JLabel lblPrecoProd3;
    private javax.swing.JLabel lblPrecoVenda1;
    private javax.swing.JLabel lblPrecoVenda2;
    private javax.swing.JLabel lblPrecoVenda21;
    private javax.swing.JLabel lblPrecoVenda22;
    private javax.swing.JLabel lblPrecoVenda23;
    private javax.swing.JLabel lblPrecoVenda3;
    private javax.swing.JLabel lblQta1;
    private javax.swing.JLabel lblQta2;
    private javax.swing.JLabel lblQta21;
    private javax.swing.JLabel lblQta22;
    private javax.swing.JLabel lblQta23;
    private javax.swing.JLabel lblQta3;
    private javax.swing.JTextField meses;
    private javax.swing.JTextField preco_producao1;
    private javax.swing.JTextField preco_producao2;
    private javax.swing.JTextField preco_producao21;
    private javax.swing.JTextField preco_producao22;
    private javax.swing.JTextField preco_producao23;
    private javax.swing.JTextField preco_producao3;
    private javax.swing.JTextField preco_venda1;
    private javax.swing.JTextField preco_venda2;
    private javax.swing.JTextField preco_venda21;
    private javax.swing.JTextField preco_venda22;
    private javax.swing.JTextField preco_venda23;
    private javax.swing.JTextField preco_venda3;
    private javax.swing.JTextField qta_prod1;
    private javax.swing.JTextField qta_prod2;
    private javax.swing.JTextField qta_prod21;
    private javax.swing.JTextField qta_prod22;
    private javax.swing.JTextField qta_prod23;
    private javax.swing.JTextField qta_prod3;
    private javax.swing.JComboBox ramo_negocio;
    private javax.swing.JComboBox selectInvestimentoInicial;
    private javax.swing.JComboBox selectPredio;
    private javax.swing.JComboBox selectPredio2;
    // End of variables declaration//GEN-END:variables
}
