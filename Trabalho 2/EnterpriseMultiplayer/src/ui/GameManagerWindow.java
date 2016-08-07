package ui;

import java.util.ArrayList;
import javax.swing.JLabel;
import structures.GameManager;
import structures.Player;
import utils.CONSTANTS;

/**
 *
 * @author pedro
 */
public class GameManagerWindow extends javax.swing.JFrame {

    GameManager gm;
    private ArrayList<JLabel> playerLabels;
    private ArrayList<JLabel> profitLabels;
    
    public GameManagerWindow(ArrayList<Player> players) {
        gm = new GameManager(players);
        playerLabels = new ArrayList<JLabel>();
        profitLabels = new ArrayList<JLabel>();
        initComponents();
        myInits();
    }
    
    private void myInits() {
        setTitle("Enterprise Game Manager");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        
        playerLabels.add(lblP1);
        playerLabels.add(lblP2);
        playerLabels.add(lblP3);
        playerLabels.add(lblP4);
        
        profitLabels.add(lblProfit1);
        profitLabels.add(lblProfit2);
        profitLabels.add(lblProfit3);
        profitLabels.add(lblProfit4);
        
        lblPopulation.setText(CONSTANTS.POPULATION + "");
        lblCustomers.setText(gm.getPopulationManager().getInterestedCustomers() + "");
        
        for (int i = 0; i < gm.getPlayers().size(); i++) {
            playerLabels.get(i).setText(gm.getPlayers().get(i).getName());
            profitLabels.get(i).setText(gm.getPlayers().get(i).getProfitThisRound() + "");
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblP1 = new javax.swing.JLabel();
        lblPopulation = new javax.swing.JLabel();
        lblCustomers = new javax.swing.JLabel();
        lblP3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblP2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblP4 = new javax.swing.JLabel();
        lblProfit2 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblProfit3 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblProfit4 = new javax.swing.JLabel();
        lblProfit1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Turn Statistics");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 11;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Population:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Customers:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel3, gridBagConstraints);

        lblP1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblP1.setText("Player 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(lblP1, gridBagConstraints);

        lblPopulation.setText("jLabel5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(lblPopulation, gridBagConstraints);

        lblCustomers.setText("jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        getContentPane().add(lblCustomers, gridBagConstraints);

        lblP3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblP3.setText("Player 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(lblP3, gridBagConstraints);

        jLabel10.setText("Profit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        getContentPane().add(jLabel10, gridBagConstraints);

        lblP2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblP2.setText("Player 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(lblP2, gridBagConstraints);

        jLabel14.setText("Profit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        getContentPane().add(jLabel14, gridBagConstraints);

        lblP4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblP4.setText("Player 4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(lblP4, gridBagConstraints);

        lblProfit2.setText("jLabel18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        getContentPane().add(lblProfit2, gridBagConstraints);

        jLabel27.setText("Profit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        getContentPane().add(jLabel27, gridBagConstraints);

        lblProfit3.setText("jLabel28");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        getContentPane().add(lblProfit3, gridBagConstraints);

        jLabel29.setText("Profit:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        getContentPane().add(jLabel29, gridBagConstraints);

        lblProfit4.setText("jLabel30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 12;
        getContentPane().add(lblProfit4, gridBagConstraints);

        lblProfit1.setText("jLabel33");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        getContentPane().add(lblProfit1, gridBagConstraints);

        jButton1.setText("Close");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jButton1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCustomers;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    private javax.swing.JLabel lblP3;
    private javax.swing.JLabel lblP4;
    private javax.swing.JLabel lblPopulation;
    private javax.swing.JLabel lblProfit1;
    private javax.swing.JLabel lblProfit2;
    private javax.swing.JLabel lblProfit3;
    private javax.swing.JLabel lblProfit4;
    // End of variables declaration//GEN-END:variables
}
