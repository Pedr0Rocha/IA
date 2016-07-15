package ui;

import java.util.ArrayList;
import javax.swing.InputVerifier;
import structures.GameDatabase;
import structures.Player;
import utils.DatabaseLoader;

/**
 *
 * @author pedronote
 */
public class ConfigurationWindow extends javax.swing.JFrame {

    ArrayList<Player> players;
    DatabaseLoader dbLoader;
    
    GameDatabase db = GameDatabase.getInstance();
    
    public ConfigurationWindow(ArrayList<Player> players) {
        initComponents();
        myInits(players);
    }
    
    private void myInits(ArrayList<Player> playersList) {
        setTitle("Enterprise Configurations");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        dbLoader =  new DatabaseLoader();
        initComboBoxes();
        
        this.players = playersList;        
    }
    
    private void initComboBoxes() {
        comboBusinessType.removeAllItems();
        for (int i = 0; i < db.getBusinesses().size(); i++)
            comboBusinessType.addItem(db.getBusinesses().get(i).getName());
        
        comboStartingMoney.removeAllItems();
        comboStartingMoney.addItem("50000");
        comboStartingMoney.addItem("100000");
        comboStartingMoney.addItem("500000");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        comboMonths = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        comboStartingMoney = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboBusinessType = new javax.swing.JComboBox<>();
        btnStartGame = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnOpenDatabase = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0};
        getContentPane().setLayout(layout);

        jLabel1.setText("Months");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel1, gridBagConstraints);

        comboMonths.setModel(new javax.swing.SpinnerNumberModel(12, 12, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(comboMonths, gridBagConstraints);

        jLabel2.setText("Starting Money");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel2, gridBagConstraints);

        comboStartingMoney.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboStartingMoney, gridBagConstraints);

        jLabel3.setText("Business");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(jLabel3, gridBagConstraints);

        comboBusinessType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboBusinessType, gridBagConstraints);

        btnStartGame.setText("Start Game");
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnStartGame, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("Main Configurations");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 11;
        getContentPane().add(jLabel4, gridBagConstraints);

        btnOpenDatabase.setText("Database");
        btnOpenDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenDatabaseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 10;
        getContentPane().add(btnOpenDatabase, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenDatabaseActionPerformed
        new DatabaseWindow().setVisible(true);
    }//GEN-LAST:event_btnOpenDatabaseActionPerformed

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        int months = Integer.valueOf(comboMonths.getValue().toString());
        String business = comboBusinessType.getItemAt(comboBusinessType.getSelectedIndex());
        double startingMoney = Double.valueOf(comboStartingMoney.getItemAt(comboStartingMoney.getSelectedIndex()));
        
        // info to send to every client - months, business, startingMoney
    }//GEN-LAST:event_btnStartGameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenDatabase;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JComboBox<String> comboBusinessType;
    private javax.swing.JSpinner comboMonths;
    private javax.swing.JComboBox<String> comboStartingMoney;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
