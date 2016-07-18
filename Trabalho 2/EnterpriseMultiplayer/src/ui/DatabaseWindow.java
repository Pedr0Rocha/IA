/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import structures.GameDatabase;

/**
 *
 * @author pedro
 */
public class DatabaseWindow extends javax.swing.JFrame {

    GameDatabase db = GameDatabase.getInstance();
    
    public DatabaseWindow() {
        initComponents();
        myInits();
    }

    private void myInits() {
        setTitle("Database");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loadText();
    }
    
    private void loadText() {
        StringBuilder fullMessage = new StringBuilder();
        fullMessage.append("---- BUSINESSES ----\n\n");
        for (int i = 0; i < db.getBusinesses().size(); i++)
            fullMessage.append(db.getBusinesses().get(i).getName() + "\n");
        
        fullMessage.append("\n---- BUILDINGS ----\n\n");
        for (int i = 0; i < db.getBuildings().size(); i++) 
            fullMessage.append(db.getBuildings().get(i).getName() + "\n");
               
        fullMessage.append("\n---- PRODUCTS ----\n\n");
        for (int i = 0; i < db.getProducts().size(); i++) 
            fullMessage.append(db.getProducts().get(i).getName() + "\n");
          
        txtDatabase.setText(fullMessage.toString());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDatabase = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0};
        layout.rowHeights = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0};
        getContentPane().setLayout(layout);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("Database");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jLabel2, gridBagConstraints);

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jButton2, gridBagConstraints);

        txtDatabase.setEditable(false);
        txtDatabase.setColumns(20);
        txtDatabase.setRows(12);
        jScrollPane1.setViewportView(txtDatabase);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDatabase;
    // End of variables declaration//GEN-END:variables
}
