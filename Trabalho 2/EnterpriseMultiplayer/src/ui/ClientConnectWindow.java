package ui;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import structures.Player;
import utils.CONSTANTS;
import utils.DatabaseLoader;

/**
 *
 * @author pedro
 */
public class ClientConnectWindow extends javax.swing.JFrame {

    DatabaseLoader dbLoader;
    
    public ClientConnectWindow() {
        initComponents();
        myInits();
    }

    private void myInits() {
        setTitle("Enterprise Multiplayer Client");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        dbLoader = new DatabaseLoader();
        
        inputPlayerType.removeAllItems();
        inputPlayerType.addItem("Player");
        inputPlayerType.addItem("AI1 - Greedy");
        inputPlayerType.addItem("AI2 - ???");
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputServer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputPort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputPlayerName = new javax.swing.JTextField();
        inputPlayerType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Enterprise Multiplayer Client");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Server:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel2, gridBagConstraints);

        inputServer.setText("localhost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(inputServer, gridBagConstraints);

        jLabel3.setText("Port:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel3, gridBagConstraints);

        inputPort.setText("199999");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(inputPort, gridBagConstraints);

        btnConnect.setText("Connect to Server");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnConnect, gridBagConstraints);

        jLabel4.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(jLabel4, gridBagConstraints);

        jLabel5.setText("Type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        getContentPane().add(jLabel5, gridBagConstraints);

        inputPlayerName.setText("Pedro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(inputPlayerName, gridBagConstraints);

        inputPlayerType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(inputPlayerType, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        String serverHost = "";
        int port = 0;
        String playerName = "";
        String playerType = "";
        try {
            serverHost = inputServer.getText();
            port = Integer.valueOf(inputPort.getText());
            playerName = inputPlayerName.getText();
            playerType = inputPlayerType.getItemAt(inputPlayerType.getSelectedIndex());
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        
        if (!serverHost.isEmpty() && port != 0 && !playerName.isEmpty()) {
            System.out.println("Connecting to host: " + serverHost + " at port:" + port);
            System.out.println("Connecting as " + playerName + " and type " + playerType);
            // TCP - 3-wayhandshake
            try {
                Socket socket = new Socket(serverHost, port);
            } catch (IOException ex) {
                Logger.getLogger(ClientConnectWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(false);
            Player player = new Player("Pedro", CONSTANTS.TYPE_PLAYER);
            player.setCurrentMoney(700000);
            player.setBusinessType(CONSTANTS.TECHBUSSINESS);
            new ClientGameWindow(player).setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnConnectActionPerformed

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
                new ClientConnectWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnConnect;
    private javax.swing.JTextField inputPlayerName;
    private javax.swing.JComboBox<String> inputPlayerType;
    private javax.swing.JTextField inputPort;
    private javax.swing.JTextField inputServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
