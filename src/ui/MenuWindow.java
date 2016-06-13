/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.UIManager;
import trabalhoia.Negocio;
import trabalhoia.Estrutura;
import trabalhoia.Jogo;
import trabalhoia.Produto;

/**
 *
 * @author Pedro
 */
public class MenuWindow extends javax.swing.JFrame {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    
    public static boolean debugMode = true;
    
    private static ArrayList<Negocio> negocios;
    
    public MenuWindow() {    
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch(Exception e){
            System.out.println("Erro alterando Look and Feel");
        }
        initComponents();
        myInits();
    }

    private void myInits(){
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setResizable(false); 
        setLocationRelativeTo(null);
        createNegocios();
        
        if (debugMode){
            System.out.println("Debug Log Iniciado");
            System.out.println("Menu Principal Aberto");
        }
        
        btnPvP.setText("Player vs Player");
        btnCvP.setText("Player vs Comp.");
        btnCvC.setText("Comp. vs Comp.");
        btnExit.setText("Sair");   
    }
    
    private void createNegocios(){
        negocios = new ArrayList<Negocio>();
        
        ArrayList<Estrutura> predios = new ArrayList<Estrutura>();
        predios.add(new Estrutura("Garagem", 30000.00, 10, 0, 0, 0, 80.00));
        predios.add(new Estrutura("Sala Comercial", 80000.00, 20, 7, 0, 1, 200.00));
        predios.add(new Estrutura("Prédio", 350000.00, 35, 14, 5, 2, 400.00));
        
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        produtos.add(new Produto("Website", 0, 2000.00, 00.00));
        produtos.add(new Produto("Aplicativo", 1, 4000.00, 00.00));
        produtos.add(new Produto("Sistema", 2, 10000.00, 00.00));
        
        Negocio tecnologia = new Negocio("Tecnologia", predios, produtos);
        negocios.add(tecnologia);
    }
    
    public static ArrayList getNegocios(){
        return negocios;
    }
    
    public static Negocio getNegocio(String ramo){
        for (Negocio n: negocios)
            if (n.ramo.equals(ramo)) return n;
        return negocios.get(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnPvP = new javax.swing.JButton();
        btnCvP = new javax.swing.JButton();
        btnCvC = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnInstrucoes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu - Company Wars");
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lblTitulo.setText("Simulador de Empresas");

        btnPvP.setText("Player vs Player");
        btnPvP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPvPActionPerformed(evt);
            }
        });

        btnCvP.setText("Player vs Comp.");
        btnCvP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCvPActionPerformed(evt);
            }
        });

        btnCvC.setText("Comp. vs Comp.");
        btnCvC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCvCActionPerformed(evt);
            }
        });

        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnInstrucoes.setText("Instruções");
        btnInstrucoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstrucoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPvP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCvP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCvC, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInstrucoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(325, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lblTitulo)
                .addGap(90, 90, 90)
                .addComponent(btnPvP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCvP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCvC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInstrucoes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.out.println("Saindo do Jogo");
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnPvPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPvPActionPerformed
        dispose();
        //new OptionsWindow(1).setVisible(true);
        Jogo jogo = new Jogo(1);
    }//GEN-LAST:event_btnPvPActionPerformed

    private void btnCvPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCvPActionPerformed
        dispose();
        //new OptionsWindow(3).setVisible(true);
        Jogo jogo = new Jogo(3);
    }//GEN-LAST:event_btnCvPActionPerformed

    private void btnCvCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCvCActionPerformed
        dispose();
        //new OptionsWindow(3).setVisible(true);
        Jogo jogo = new Jogo(2);
    }//GEN-LAST:event_btnCvCActionPerformed

    private void btnInstrucoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstrucoesActionPerformed
        dispose();
        new InstruWindow().setVisible(true);
    }//GEN-LAST:event_btnInstrucoesActionPerformed

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
            java.util.logging.Logger.getLogger(MenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCvC;
    private javax.swing.JButton btnCvP;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInstrucoes;
    private javax.swing.JButton btnPvP;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
