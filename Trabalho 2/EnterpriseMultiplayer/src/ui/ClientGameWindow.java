package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import structures.Building;
import structures.Business;
import structures.GameDatabase;
import structures.GameSettings;
import structures.Player;
import structures.Product;
import tcp.GameClient;
import utils.CONSTANTS;
import utils.Popup;

/**
 *
 * @author pedro
 */
public class ClientGameWindow extends javax.swing.JFrame {

    GameDatabase db = GameDatabase.getInstance();
    Player player;
    ArrayList<Product> availableProducts;
    
    private GameClient client;
    public int playState; // 0 - mainConfigs change, 1 - saved warehouse, 2 - confirm play
    
    public ClientGameWindow(Player createdPlayer, GameClient client) {
        initComponents();
        this.client = client;
        myInits(createdPlayer, 0);
    }

    private void myInits(Player createdPlayer, int currentTurn) {
        setTitle("Enterprise Multiplayer Client");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        this.player = createdPlayer;
        availableProducts = db.getProductsByBusinessType(player.getBusinessType());
        
        GameDatabase db = GameDatabase.getInstance();
        Business business = db.getBusinessByType(createdPlayer.getBusinessType());
        GameSettings gs = new GameSettings(12, createdPlayer.getCurrentMoney(), business);
        gs.setCurrentMonth(currentTurn);
        updateMainConfigsValue(gs);
        updateBuildingValue();
        updateProductsValue();
    }
    
    private void updateMainConfigsValue(GameSettings gm) {
        updateMoneyValue();
        lblBusinessName.setText(gm.getBusiness().getName());
        lblMonths.setMaximum(gm.getMaxMonths());
        lblMonths.setValue(gm.getCurrentMonth());
        lblMonths.setString(gm.getCurrentMonth() + "/" + lblMonths.getMaximum());
        lblMonths.setIndeterminate(false);
        
        comboMarketingValues.setEnabled(false);
        comboResearchValues.setEnabled(false);
        btnSaveWarehouse.setEnabled(true);
        
        playState = CONSTANTS.PLAYSTATUS_START;
    }
    
    private void updateMoneyValue() {
        lblTotalMoney.setText(String.valueOf(player.getCurrentMoney()));
    }
    
    private void updateBuildingValue() {
        Building currentBuilding = player.getBuilding();
        lblBuildingName.setText(currentBuilding.getName());
        lblBuildingLevel.setText(String.valueOf(currentBuilding.getLevel()));
        
        if (currentBuilding.canUpdate(player))
            btnUpgradeBuilding.setEnabled(true);
        else
            btnUpgradeBuilding.setEnabled(false);
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
    
    private void setInvestments() {
        String marketingInvestment = comboMarketingValues.getItemAt(comboMarketingValues.getSelectedIndex());
        String researchInvestment = comboResearchValues.getItemAt(comboResearchValues.getSelectedIndex());

        if (marketingInvestment.equals("Low"))
            player.setMarketingInvestment(CONSTANTS.LOW_INVESTMENT);
        else if (marketingInvestment.equals("Medium")) 
            player.setMarketingInvestment(CONSTANTS.MED_INVESTMENT);
        else                
            player.setMarketingInvestment(CONSTANTS.HIGH_INVESTMENT);

        if (researchInvestment.equals("Low"))
            player.setResearchInvestment(CONSTANTS.LOW_INVESTMENT);
        else if (researchInvestment.equals("Medium")) 
            player.setResearchInvestment(CONSTANTS.MED_INVESTMENT);
        else                
            player.setResearchInvestment(CONSTANTS.HIGH_INVESTMENT);
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
        btnUpgradeBuilding = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comboSellingPrice0 = new javax.swing.JComboBox<>();
        lblProductName0 = new javax.swing.JLabel();
        comboQuantity0 = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnOpenWarehouse = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        btnConfirmPlay = new javax.swing.JButton();
        comboMarketingValues = new javax.swing.JComboBox<>();
        comboResearchValues = new javax.swing.JComboBox<>();
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
        btnSaveWarehouse = new javax.swing.JButton();

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

        btnUpgradeBuilding.setText("Upgrade");
        btnUpgradeBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpgradeBuildingActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnUpgradeBuilding, gridBagConstraints);

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

        btnOpenWarehouse.setText("Open Warehouse");
        btnOpenWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenWarehouseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnOpenWarehouse, gridBagConstraints);

        jLabel17.setText("Research:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 34;
        getContentPane().add(jLabel17, gridBagConstraints);

        btnConfirmPlay.setText("Confirm Play");
        btnConfirmPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmPlayActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 32;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(btnConfirmPlay, gridBagConstraints);

        comboMarketingValues.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "High" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 32;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboMarketingValues, gridBagConstraints);

        comboResearchValues.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "High" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 34;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboResearchValues, gridBagConstraints);

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

        btnSaveWarehouse.setText("Save Warehouse Changes");
        btnSaveWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveWarehouseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnSaveWarehouse, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboQuantity0StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboQuantity0StateChanged
        double craftingCost = availableProducts.get(0).getCraftingCost();
        lblSpend0.setText((craftingCost * (Integer)comboQuantity0.getValue()) + "");
    }//GEN-LAST:event_comboQuantity0StateChanged

    private void comboQuantity1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboQuantity1StateChanged
        double craftingCost = availableProducts.get(1).getCraftingCost();
        lblSpend1.setText((craftingCost * (Integer)comboQuantity1.getValue()) + "");
    }//GEN-LAST:event_comboQuantity1StateChanged

    private void comboQuantity2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboQuantity2StateChanged
        double craftingCost = availableProducts.get(2).getCraftingCost();
        lblSpend2.setText((craftingCost * (Integer)comboQuantity2.getValue()) + "");
    }//GEN-LAST:event_comboQuantity2StateChanged

    private void btnSaveWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveWarehouseActionPerformed
        
        if ((Integer)comboQuantity0.getValue() != 0) {
            Product prod0 = new Product(availableProducts.get(0));
            prod0.setQuantityInStock((Integer)comboQuantity0.getValue());
            double sellingPrice = Double.valueOf(comboSellingPrice0.getItemAt(comboSellingPrice0.getSelectedIndex())); 
            prod0.setSellPrice(sellingPrice);
            player.getWarehouse().addToStock(prod0);
        }
        if ((Integer)comboQuantity1.getValue() != 0) {
            Product prod1 = new Product(availableProducts.get(1));
            prod1.setQuantityInStock((Integer)comboQuantity1.getValue());
            double sellingPrice = Double.valueOf(comboSellingPrice1.getItemAt(comboSellingPrice1.getSelectedIndex())); 
            prod1.setSellPrice(sellingPrice);
            player.getWarehouse().addToStock(prod1);
        }
        if ((Integer)comboQuantity2.getValue() != 0) {
            Product prod2 = new Product(availableProducts.get(2));
            prod2.setQuantityInStock((Integer)comboQuantity2.getValue());
            double sellingPrice = Double.valueOf(comboSellingPrice2.getItemAt(comboSellingPrice2.getSelectedIndex())); 
            prod2.setSellPrice(sellingPrice);
            player.getWarehouse().addToStock(prod2);
        }
        
        playState = CONSTANTS.PLAYSTATUS_WAREHOUSECHANGES;
        btnSaveWarehouse.setEnabled(false);        
        comboMarketingValues.setEnabled(true);
        comboResearchValues.setEnabled(true);
    }//GEN-LAST:event_btnSaveWarehouseActionPerformed

    private void btnConfirmPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmPlayActionPerformed
        if (playState == CONSTANTS.PLAYSTATUS_WAREHOUSECHANGES) {
            playState = CONSTANTS.PLAYSTATUS_CONFIRMPLAY;
            setInvestments();
            String serializedWh = player.getWarehouse().serialize(player.getWarehouse());
            System.out.println(serializedWh);
            player.getWarehouse().deserialize(player.getWarehouse(), serializedWh);
            // TCP - send all info to the servers and wait for other players
            System.out.println("Confirmed play from " + player.getName());
            try {
                client.send(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            new Popup("Waiting for server response").setVisible(true);
            Player updatedPlayer = null;
            int currentTurn = -1;
            try {
                updatedPlayer = (Player) client.receive();
                currentTurn = (Integer) client.receive();
            } catch (IOException ex) {
                Logger.getLogger(ClientGameWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            myInits(updatedPlayer, currentTurn);
            // TCP - receive turn statistics from server
        } else {
            System.out.println("Missing info, can't confirm play");
            new Popup("Save your warehouse changes before continuing").setVisible(true);
        }
    }//GEN-LAST:event_btnConfirmPlayActionPerformed

    private void btnOpenWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenWarehouseActionPerformed
        new WarehouseWindow(player).setVisible(true);
    }//GEN-LAST:event_btnOpenWarehouseActionPerformed

    private void btnUpgradeBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpgradeBuildingActionPerformed
        Building currentBuilding = player.getBuilding();
        Building nextBuilding = currentBuilding.getNextBuilding(player);
        
        player.setBuilding(nextBuilding);
        double newMoney = player.getCurrentMoney() - nextBuilding.getPrice();
        player.setCurrentMoney(newMoney);
        
        updateBuildingValue();
        updateMoneyValue();
        updateProductsValue();
    }//GEN-LAST:event_btnUpgradeBuildingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmPlay;
    private javax.swing.JButton btnOpenWarehouse;
    private javax.swing.JButton btnSaveWarehouse;
    private javax.swing.JButton btnUpgradeBuilding;
    private javax.swing.JComboBox<String> comboMarketingValues;
    private javax.swing.JSpinner comboQuantity0;
    private javax.swing.JSpinner comboQuantity1;
    private javax.swing.JSpinner comboQuantity2;
    private javax.swing.JComboBox<String> comboResearchValues;
    private javax.swing.JComboBox<String> comboSellingPrice0;
    private javax.swing.JComboBox<String> comboSellingPrice1;
    private javax.swing.JComboBox<String> comboSellingPrice2;
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
