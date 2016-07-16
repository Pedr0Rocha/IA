package ui;

import structures.GameDatabase;
import structures.Player;
import structures.Product;
import structures.Warehouse;

/**
 *
 * @author pedro
 */
public class WarehouseWindow extends javax.swing.JFrame {

    private Warehouse warehouse;
    private Player player;
    GameDatabase db = GameDatabase.getInstance();
    
    public WarehouseWindow(Player p) {
        initComponents();
        this.player = p;
        this.warehouse = p.getWarehouse();
        myInits();
    }
    
    private void myInits() {
        setTitle("Warehouse");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        
        initValues();
    }
    
    private void initValues() {        
        lblNameProd0.setText(db.getProductsByBusinessType(player.getBusinessType()).get(0).getName());
        lblNameProd1.setText(db.getProductsByBusinessType(player.getBusinessType()).get(1).getName());
        lblNameProd2.setText(db.getProductsByBusinessType(player.getBusinessType()).get(2).getName());
        
        lblCraftCostProd0.setText(db.getProductsByBusinessType(player.getBusinessType()).get(0).getCraftingCost()+"");
        lblCraftCostProd1.setText(db.getProductsByBusinessType(player.getBusinessType()).get(1).getCraftingCost()+"");
        lblCraftCostProd2.setText(db.getProductsByBusinessType(player.getBusinessType()).get(2).getCraftingCost()+"");
        
        lblQuantityProd0.setText("0");
        lblQuantityProd1.setText("0");
        lblQuantityProd2.setText("0");
        
        lblSellingPriceProd0.setText("Not Set");
        lblSellingPriceProd1.setText("Not Set");
        lblSellingPriceProd2.setText("Not Set");
        
        lblCraftCost.setText(warehouse.getTotalCraftCost()  +"");
        lblSellWorth.setText(warehouse.getTotalSellCost()  +"");
        
        Product prod0 = warehouse.getProductOnStock(db.getProductsByBusinessType(player.getBusinessType()).get(0));
        if (prod0 != null) {
            lblQuantityProd0.setText(prod0.getQuantityInStock() + "");
            lblSellingPriceProd0.setText(prod0.getSellPrice() + "");
        } else 
            System.out.println("No Product Level 0");
        
        Product prod1 = warehouse.getProductOnStock(db.getProductsByBusinessType(player.getBusinessType()).get(1));
        if (prod1 != null) {
            lblQuantityProd1.setText(prod1.getQuantityInStock() + "");
            lblSellingPriceProd1.setText(prod1.getSellPrice() + "");
        } else 
            System.out.println("No Product Level 1");
        
        Product prod2 = warehouse.getProductOnStock(db.getProductsByBusinessType(player.getBusinessType()).get(2));
        if (prod2 != null) {
            lblQuantityProd2.setText(prod2.getQuantityInStock() + "");
            lblSellingPriceProd2.setText(prod2.getSellPrice() + "");
        } else 
            System.out.println("No Product Level 2");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblQuantityProd0 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSellingPriceProd0 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblCraftCostProd0 = new javax.swing.JLabel();
        lblNameProd0 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        lblNameProd1 = new javax.swing.JLabel();
        lblQuantityProd1 = new javax.swing.JLabel();
        lblSellingPriceProd1 = new javax.swing.JLabel();
        lblCraftCostProd1 = new javax.swing.JLabel();
        lblNameProd2 = new javax.swing.JLabel();
        lblQuantityProd2 = new javax.swing.JLabel();
        lblSellingPriceProd2 = new javax.swing.JLabel();
        lblCraftCostProd2 = new javax.swing.JLabel();
        lblSellWorth = new javax.swing.JLabel();
        lblCraftCost = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Warehouse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Product:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel3, gridBagConstraints);

        lblQuantityProd0.setText("jLabel4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        getContentPane().add(lblQuantityProd0, gridBagConstraints);

        jLabel5.setText("Selling Value:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(jLabel5, gridBagConstraints);

        lblSellingPriceProd0.setText("jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        getContentPane().add(lblSellingPriceProd0, gridBagConstraints);

        jLabel7.setText("Craft Cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        getContentPane().add(jLabel7, gridBagConstraints);

        lblCraftCostProd0.setText("jLabel8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        getContentPane().add(lblCraftCostProd0, gridBagConstraints);

        lblNameProd0.setText("jLabel9");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(lblNameProd0, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jLabel9.setText("Product:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        getContentPane().add(jLabel9, gridBagConstraints);

        jLabel10.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        getContentPane().add(jLabel10, gridBagConstraints);

        jLabel11.setText("Selling Value:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        getContentPane().add(jLabel11, gridBagConstraints);

        jLabel12.setText("Craft Cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        getContentPane().add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jSeparator2, gridBagConstraints);

        jLabel13.setText("Product:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        getContentPane().add(jLabel13, gridBagConstraints);

        jLabel14.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        getContentPane().add(jLabel14, gridBagConstraints);

        jLabel15.setText("Selling Value:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        getContentPane().add(jLabel15, gridBagConstraints);

        jLabel16.setText("Craft Cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 28;
        getContentPane().add(jLabel16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jSeparator3, gridBagConstraints);

        jLabel17.setText("Total Sell Worth:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 32;
        getContentPane().add(jLabel17, gridBagConstraints);

        jLabel18.setText("Total Craft Cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 34;
        getContentPane().add(jLabel18, gridBagConstraints);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 36;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnClose, gridBagConstraints);

        lblNameProd1.setText("jLabel19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        getContentPane().add(lblNameProd1, gridBagConstraints);

        lblQuantityProd1.setText("jLabel20");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        getContentPane().add(lblQuantityProd1, gridBagConstraints);

        lblSellingPriceProd1.setText("jLabel21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        getContentPane().add(lblSellingPriceProd1, gridBagConstraints);

        lblCraftCostProd1.setText("jLabel22");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        getContentPane().add(lblCraftCostProd1, gridBagConstraints);

        lblNameProd2.setText("jLabel23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        getContentPane().add(lblNameProd2, gridBagConstraints);

        lblQuantityProd2.setText("jLabel24");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 24;
        getContentPane().add(lblQuantityProd2, gridBagConstraints);

        lblSellingPriceProd2.setText("jLabel25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 26;
        getContentPane().add(lblSellingPriceProd2, gridBagConstraints);

        lblCraftCostProd2.setText("jLabel26");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 28;
        getContentPane().add(lblCraftCostProd2, gridBagConstraints);

        lblSellWorth.setText("jLabel27");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 32;
        getContentPane().add(lblSellWorth, gridBagConstraints);

        lblCraftCost.setText("jLabel28");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 34;
        getContentPane().add(lblCraftCost, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCraftCost;
    private javax.swing.JLabel lblCraftCostProd0;
    private javax.swing.JLabel lblCraftCostProd1;
    private javax.swing.JLabel lblCraftCostProd2;
    private javax.swing.JLabel lblNameProd0;
    private javax.swing.JLabel lblNameProd1;
    private javax.swing.JLabel lblNameProd2;
    private javax.swing.JLabel lblQuantityProd0;
    private javax.swing.JLabel lblQuantityProd1;
    private javax.swing.JLabel lblQuantityProd2;
    private javax.swing.JLabel lblSellWorth;
    private javax.swing.JLabel lblSellingPriceProd0;
    private javax.swing.JLabel lblSellingPriceProd1;
    private javax.swing.JLabel lblSellingPriceProd2;
    // End of variables declaration//GEN-END:variables
}
