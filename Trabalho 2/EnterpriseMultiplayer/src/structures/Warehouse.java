package structures;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class Warehouse {
    
    private ArrayList<Product> stock;
    private double sellingCost;
    private double craftingCost;
    
    public Warehouse() {
        stock = new ArrayList<Product>();
    }
    
    public void addToStock(Product product) {
        int indexOnStock = getIndexOnStock(product.getName());
        if (indexOnStock >= 0)
            updateInStock(product, indexOnStock);
        else
            stock.add(product);
        
        craftingCost = getTotalCraftCost();
        sellingCost = getTotalSellCost();
    }
    
    private void updateInStock(Product product, int index) {
        Product productInStock = stock.get(index);
        
        productInStock.setSellPrice(product.getSellPrice());
        productInStock.updateQuantityInStock(product.getQuantityInStock());
    }
    
    private int getIndexOnStock(String productName) {
        for (int i = 0; i < stock.size(); i++)
            if (stock.get(i).getName().equals(productName)) return i;
        return -1;
    }
    
    public boolean has(Product product) {
        for (Product p : stock)
            if (p.getName().equals(product.getName())) return true;
        return false;
    }
    
    public double getTotalCraftCost(){
        double totalValue = 0;
        for (Product p: stock)
            totalValue += (p.getCraftingCost() * p.getQuantityInStock());
        return totalValue;
    }
    
    public double getTotalSellCost(){
        double totalValue = 0;
        for (Product p: stock)
            totalValue += (p.getSellPrice() * p.getQuantityInStock());
        return totalValue;
    }  
    
    public ArrayList<Product> getStock() {
        return this.stock;
    }
    
    public Product getProductOnStock(Product product) {
        int indexOnStock = getIndexOnStock(product.getName());
        if (indexOnStock >= 0)
            return stock.get(indexOnStock);
        return null;
    }
}
