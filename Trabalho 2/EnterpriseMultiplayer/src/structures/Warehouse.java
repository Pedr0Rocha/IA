package structures;

import java.util.ArrayList;
import utils.CONSTANTS;
import utils.DatabaseLoader;

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
    
    public static String serialize(Warehouse wh) {
        String serializedWarehouse = "";
        ArrayList<Product> stock = wh.getStock();
        for (int i = 0; i < stock.size(); i++) {
            serializedWarehouse += stock.get(i).getName();
            serializedWarehouse += "|";
            serializedWarehouse += stock.get(i).getQuantityInStock();
            serializedWarehouse += "|";
            serializedWarehouse += stock.get(i).getSellPrice();
            serializedWarehouse += "|";
        }
        return serializedWarehouse;
    }
    
    public static Warehouse deserialize(String serialized) {
        Warehouse wh = new Warehouse();
        ArrayList<Product> prods = GameDatabase.getInstance().getProductsByBusinessType(CONSTANTS.TECHBUSSINESS);
        for (Product p : prods) {
            Product newP = new Product(p);
            newP.setQuantityInStock(0);
            wh.addToStock(newP);
        }
        
        String[] items = serialized.split("\\|");
        for (int i = 0; i < items.length; i+=3) {
            for (Product p : wh.getStock()) {
                if (p.getName().equals(items[i])) {
                    p.setQuantityInStock(Integer.valueOf(items[i+1]));
                    p.setSellPrice(Double.valueOf(items[i+2]));
                }
            }
        }
        for (int i = 0; i < wh.getStock().size(); i++)
            if (wh.getStock().get(i).getQuantityInStock() == 0)
                wh.getStock().remove(wh.getStock().get(i));
        
        return wh;
    }
}
