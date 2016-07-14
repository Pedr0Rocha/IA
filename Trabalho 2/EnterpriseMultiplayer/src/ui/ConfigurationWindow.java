package ui;

import java.util.ArrayList;
import structures.Player;

/**
 *
 * @author pedronote
 */
public class ConfigurationWindow extends javax.swing.JFrame {

    ArrayList<Player> players;
    
    public ConfigurationWindow(ArrayList<Player> players) {
        initComponents();
        myInits(players);
    }
    
    private void myInits(ArrayList<Player> playersList) {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        
        this.players = playersList;        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
