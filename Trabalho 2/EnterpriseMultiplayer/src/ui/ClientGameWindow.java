package ui;

import java.util.ArrayList;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import structures.Building;
import structures.Business;
import structures.GameDatabase;
import structures.GameSettings;
import structures.Player;
import structures.Product;

/**
 *
 * @author pedro
 */
public class ClientGameWindow extends javax.swing.JFrame {

    GameDatabase db = GameDatabase.getInstance();
    Player player;
    ArrayList<Product> availableProducts;
    
    public ClientGameWindow(Player createdPlayer) {
        initComponents();
        myInits(createdPlayer);
    }

    private void myInits(Player createdPlayer) {
        setTitle("Enterprise Multiplayer Client");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        this.player = createdPlayer;
        availableProducts = db.getProductsByBusinessType(player.getBusinessType());
        
        // receive info from server to update all variables
        GameDatabase db = GameDatabase.getInstance(); // not necessary after client/server sync
        Business business = db.getBusinessByName("Technology"); // not necessary after client/server sync
        GameSettings gm = new GameSettings(12, 20000.00, business);
        gm.setCurrentMonth(3);
        updateMainConfigsValue(gm);
        updateBuildingValue();
        updateProductsValue();
    }
    
    private void updateMainConfigsValue(GameSettings gm) {
        lblBusinessName.setText(gm.getBusiness().getName());
        lblTotalMoney.setText(String.valueOf(gm.getInitialMoney()));
        lblMonths.setMaximum(gm.getMaxMonths());
        lblMonths.setValue(gm.getCurrentMonth());
        lblMonths.setString(gm.getCurrentMonth() + "/" + lblMonths.getMaximum());
        lblMonths.setIndeterminate(false);
    }
    
    private void updateBuildingValue() {
        Building b = player.getBuilding();
        lblBuildingName.setText(b.getName());
        lblBuildingLevel.setText(String.valueOf(b.getLevel()));
    }
    
    private void updateProductsValue() {
        Product prodLevel0 = availableProducts.get(0);
        Product prodLevel1 = availableProducts.get(1);
        Product prodLevel2 = availableProducts.get(2);
       
        lblProductName0.setText(prodLevel0.getName());
        lblProductName1.setText(prodLevel1.getName());
        lblProductName2.setText(prodLevel2.getName());
        
        lblSpend0.setText("0");
        lblSpend1.setText("0");
        lblSpend2.setText("0");
        
        comboSellingPrice0.removeAllItems();
        comboSellingPrice1.removeAllItems();
        comboSellingPrice2.removeAllItems();
        for (int i = 0; i < prodLevel0.getPossibleSellPrices().length; i++)
            comboSellingPrice0.addItem(String.valueOf(prodLevel0.getPossibleSellPrices()[i]));
        for (int i = 0; i < prodLevel1.getPossibleSellPrices().length; i++)
            comboSellingPrice1.addItem(String.valueOf(prodLevel1.getPossibleSellPrices()[i]));
        for (int i = 0; i < prodLevel2.getPossibleSellPrices().length; i++)
            comboSellingPrice2.addItem(String.valueOf(prodLevel2.getPossibleSellPrices()[i]));
        
        SpinnerModel spModel0 = new SpinnerNumberModel(0, 0, player.getBuilding().getMonthlyProductionByLevel(0), 1);
        comboQuantity0.setModel(spModel0);
        SpinnerModel spModel1 = new SpinnerNumberModel(0, 0, player.getBuilding().getMonthlyProductionByLevel(1), 1);
        comboQuantity1.setModel(spModel1);
        SpinnerModel spModel2 = new SpinnerNumberModel(0, 0, player.getBuilding().getMonthlyProductionByLevel(2), 1);
        comboQuantity2.setModel(spModel2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBusinessName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTotalMoney = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblMonths = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        lblBuildingName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comboSellingPrice0 = new javax.swing.JComboBox<>();
        lblProductName0 = new javax.swing.JLabel();
        comboQuantity0 = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        lblProductName1 = new javax.swing.JLabel();
        lblProductName2 = new javax.swing.JLabel();
        comboSellingPrice1 = new javax.swing.JComboBox<>();
        comboSellingPrice2 = new javax.swing.JComboBox<>();
        comboQuantity1 = new javax.swing.JSpinner();
        comboQuantity2 = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        lblSpend0 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        lblBuildingLevel = new javax.swing.JLabel();
        lblSpend1 = new javax.swing.JLabel();
        lblSpend2 = new javax.swing.JLabel();
        btnConfirmWarehouse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Player Setup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Business:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel2, gridBagConstraints);

        lblBusinessName.setText("jLabel3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(lblBusinessName, gridBagConstraints);

        jLabel4.setText("Money:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel4, gridBagConstraints);

        lblTotalMoney.setText("jLabel5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        getContentPane().add(lblTotalMoney, gridBagConstraints);

        jLabel6.setText("Months:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(jLabel6, gridBagConstraints);

        lblMonths.setString("1/12");
        lblMonths.setStringPainted(true);
        lblMonths.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        getContentPane().add(lblMonths, gridBagConstraints);

        jLabel7.setText("Building:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        getContentPane().add(jLabel7, gridBagConstraints);

        lblBuildingName.setText("jLabel8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        getContentPane().add(lblBuildingName, gridBagConstraints);

        jButton1.setText("Upgrade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jButton1, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Products");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(jLabel9, gridBagConstraints);

        jLabel10.setText("Product:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        getContentPane().add(jLabel10, gridBagConstraints);

        jLabel11.setText("Selling Price:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        getContentPane().add(jLabel11, gridBagConstraints);

        jLabel12.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        getContentPane().add(jLabel12, gridBagConstraints);

        comboSellingPrice0.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboSellingPrice0, gridBagConstraints);

        lblProductName0.setText("jLabel13");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(lblProductName0, gridBagConstraints);

        comboQuantity0.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));
        comboQuantity0.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                comboQuantity0StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.ipadx = 20;
        getContentPane().add(comboQuantity0, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel14.setText("Investments");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(jLabel14, gridBagConstraints);

        jLabel15.setText("Marketing:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 32;
        getContentPane().add(jLabel15, gridBagConstraints);

        jButton2.setText("Check Warehouse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jButton2, gridBagConstraints);

        jLabel17.setText("Research:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 34;
        getContentPane().add(jLabel17, gridBagConstraints);

        jButton5.setText("Confirm Play");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 32;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jButton5, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jComboBox1, gridBagConstraints);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jComboBox2, gridBagConstraints);

        lblProductName1.setText("jLabel3");
        lblProductName1.setMaximumSize(new java.awt.Dimension(57, 17));
        lblProductName1.setMinimumSize(new java.awt.Dimension(57, 17));
        lblProductName1.setPreferredSize(new java.awt.Dimension(57, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(lblProductName1, gridBagConstraints);

        lblProductName2.setText("jLabel5");
        lblProductName2.setMaximumSize(new java.awt.Dimension(57, 17));
        lblProductName2.setMinimumSize(new java.awt.Dimension(57, 17));
        lblProductName2.setPreferredSize(new java.awt.Dimension(57, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(lblProductName2, gridBagConstraints);

        comboSellingPrice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboSellingPrice1, gridBagConstraints);

        comboSellingPrice2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboSellingPrice2, gridBagConstraints);

        comboQuantity1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));
        comboQuantity1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                comboQuantity1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.ipadx = 20;
        getContentPane().add(comboQuantity1, gridBagConstraints);

        comboQuantity2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));
        comboQuantity2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                comboQuantity2StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.ipadx = 20;
        getContentPane().add(comboQuantity2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jSeparator1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jSeparator2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jSeparator3, gridBagConstraints);

        jLabel16.setText("Total Spent:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        getContentPane().add(jLabel16, gridBagConstraints);

        lblSpend0.setText("jLabel18");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(lblSpend0, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 150;
        getContentPane().add(jSeparator4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 150;
        getContentPane().add(jSeparator5, gridBagConstraints);

        jLabel18.setText("Level:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        getContentPane().add(jLabel18, gridBagConstraints);

        lblBuildingLevel.setText("jLabel19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        getContentPane().add(lblBuildingLevel, gridBagConstraints);

        lblSpend1.setText("jLabel19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(lblSpend1, gridBagConstraints);

        lblSpend2.setText("jLabel20");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(lblSpend2, gridBagConstraints);

        btnConfirmWarehouse.setText("Confirm Warehouse Changes");
        btnConfirmWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmWarehouseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnConfirmWarehouse, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboQuantity0StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboQuantity0StateChanged
        double selectedSellingValue = Double.valueOf(comboSellingPrice0.getItemAt(comboSellingPrice0.getSelectedIndex()));
        lblSpend0.setText((selectedSellingValue * (Integer)comboQuantity0.getValue()) + "");
    }//GEN-LAST:event_comboQuantity0StateChanged

    private void comboQuantity1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboQuantity1StateChanged
        double selectedSellingValue = Double.valueOf(comboSellingPrice1.getItemAt(comboSellingPrice1.getSelectedIndex()));
        lblSpend1.setText((selectedSellingValue * (Integer)comboQuantity1.getValue()) + "");
    }//GEN-LAST:event_comboQuantity1StateChanged

    private void comboQuantity2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboQuantity2StateChanged
        double selectedSellingValue = Double.valueOf(comboSellingPrice2.getItemAt(comboSellingPrice2.getSelectedIndex()));
        lblSpend2.setText((selectedSellingValue * (Integer)comboQuantity2.getValue()) + "");
    }//GEN-LAST:event_comboQuantity2StateChanged

    private void btnConfirmWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmWarehouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmWarehouseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmWarehouse;
    private javax.swing.JSpinner comboQuantity0;
    private javax.swing.JSpinner comboQuantity1;
    private javax.swing.JSpinner comboQuantity2;
    private javax.swing.JComboBox<String> comboSellingPrice0;
    private javax.swing.JComboBox<String> comboSellingPrice1;
    private javax.swing.JComboBox<String> comboSellingPrice2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblBuildingLevel;
    private javax.swing.JLabel lblBuildingName;
    private javax.swing.JLabel lblBusinessName;
    private javax.swing.JProgressBar lblMonths;
    private javax.swing.JLabel lblProductName0;
    private javax.swing.JLabel lblProductName1;
    private javax.swing.JLabel lblProductName2;
    private javax.swing.JLabel lblSpend0;
    private javax.swing.JLabel lblSpend1;
    private javax.swing.JLabel lblSpend2;
    private javax.swing.JLabel lblTotalMoney;
    // End of variables declaration//GEN-END:variables
}
