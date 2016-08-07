package ui;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.UIManager;
import structures.GameDatabase;
import structures.Player;
import tcp.GameServer;
import utils.CONSTANTS;
import utils.DatabaseLoader;
import utils.Popup;

/**
 *
 * @author pedronote
 */
public class ServerWindow extends javax.swing.JFrame {

    GameDatabase db = GameDatabase.getInstance();
    DatabaseLoader gl = new DatabaseLoader();
    private ArrayList<Player> players;
    
    public ServerWindow() {
        initComponents();
        myInits();
        new GameServer(7777, 50000.00, 0, 12);
    }
    
    private void myInits() {
        setTitle("Enterprise Multiplayer");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setInitialText();
        

        players = new ArrayList<>();
        
        //fakeStartGame();
    }
    
    private void setInitialText() {
        p1lbl.setText("Waiting for player 1 to connect...");
        p2lbl.setText("Waiting for player 2 to connect...");
        p3lbl.setText("Waiting for player 3 to connect...");
        p4lbl.setText("Waiting for player 4 to connect...");
    }

    private void setConnectedText(JLabel label, int playerIndex, String playerName) {
        label.setText("Player " + playerIndex + " connected as " + playerName + "!");
    }
    
    private void createPlayerFromClientInfo(String name) {
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        p1lbl = new javax.swing.JLabel();
        p2lbl = new javax.swing.JLabel();
        p3lbl = new javax.swing.JLabel();
        p4lbl = new javax.swing.JLabel();
        btnStartGame = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exitMenuBar = new javax.swing.JMenuItem();
        menuBarInstructions = new javax.swing.JMenu();
        menubarInstructions = new javax.swing.JMenuItem();
        menubarAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0, 15, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Enterprise Multiplayer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(jLabel1, gridBagConstraints);

        p1lbl.setText("jLabel2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(p1lbl, gridBagConstraints);

        p2lbl.setText("jLabel3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(p2lbl, gridBagConstraints);

        p3lbl.setText("jLabel4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(p3lbl, gridBagConstraints);

        p4lbl.setText("jLabel5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(p4lbl, gridBagConstraints);

        btnStartGame.setText("Start Game");
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        getContentPane().add(btnStartGame, gridBagConstraints);

        jMenu1.setText("File");

        exitMenuBar.setText("Exit");
        exitMenuBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuBarActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenuBar);

        jMenuBar1.add(jMenu1);

        menuBarInstructions.setText("Help");

        menubarInstructions.setText("Instructions");
        menubarInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubarInstructionsActionPerformed(evt);
            }
        });
        menuBarInstructions.add(menubarInstructions);

        menubarAbout.setText("About");
        menubarAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubarAboutActionPerformed(evt);
            }
        });
        menuBarInstructions.add(menubarAbout);

        jMenuBar1.add(menuBarInstructions);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fakeStartGame() {
        Player player = new Player("P1", CONSTANTS.TYPE_PLAYER);
        players.add(player);
        setConnectedText(p1lbl, 1, player.getName());
        
        Player player2 = new Player("P2", CONSTANTS.TYPE_PLAYER);
        players.add(player2);
        setConnectedText(p2lbl, 2, player.getName());
    }
    
    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        System.out.println("Current number of players " + players.size());
        if (players.size() > 1) {
            this.setVisible(false);
            new ConfigurationWindow(players).setVisible(true);
            dispose();
        } else {
            new Popup("You need at least 2 players to start the game!").setVisible(true);
        }
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void exitMenuBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuBarActionPerformed
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_exitMenuBarActionPerformed

    private void menubarInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubarInstructionsActionPerformed
        
    }//GEN-LAST:event_menubarInstructionsActionPerformed

    private void menubarAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubarAboutActionPerformed
        
    }//GEN-LAST:event_menubarAboutActionPerformed

    public static void main(String args[]) {
        /* Setting the GTK look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code ">
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
        } catch(Exception e){
            System.out.println("Erro alterando Look and Feel");
        } 
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartGame;
    private javax.swing.JMenuItem exitMenuBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuBarInstructions;
    private javax.swing.JMenuItem menubarAbout;
    private javax.swing.JMenuItem menubarInstructions;
    private javax.swing.JLabel p1lbl;
    private javax.swing.JLabel p2lbl;
    private javax.swing.JLabel p3lbl;
    private javax.swing.JLabel p4lbl;
    // End of variables declaration//GEN-END:variables
}
