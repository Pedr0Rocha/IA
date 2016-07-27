package structures;

import java.util.ArrayList;
import utils.PopulationManager;

/**
 *
 * @author pedro
 */
public class GameManager {
    
    private ArrayList<Player> players;
    private PopulationManager populationManager;
    GameDatabase db = GameDatabase.getInstance();
    
    public GameManager(ArrayList<Player> playersList) {
        this.players = playersList;
        int marketingInvestment = calcMarketingInvestments();
        populationManager = new PopulationManager(marketingInvestment);
    }
    
    private int calcMarketingInvestments() {
        int totalInvested = 0;
        for (Player p : players)
            totalInvested += p.getMarketingInvestment();
        return (int)totalInvested;
    }

    private void calcAllProductsCostBenefit() {
        for (Player p : players)
            for (Product prod : p.getWarehouse().getStock())
                prod.setCostBenefit(p.getMarketingInvestment(), p.getResearchInvestment());
    }
    
    private void sellingManager() {
        ArrayList<Product> products = db.getProductsByBusinessType(players.get(0).getBusinessType());
        /* if the product has a demand, sell to the player who has the best cost benefit of the product */        
        for (int i = 0; i < 4; i++) 
            if (populationManager.productLevelHasDemand(i)) {
                Player seller = getPlayerBestCostBenefit(products.get(i));
                Product prodToSell = seller.getWarehouse().getProductOnStock(products.get(i));
                int buyers = populationManager.getCustomerByProductLevel(i);
                if (buyers > prodToSell.getQuantityInStock()) {
                    /* still products to sell after this player */
                } else {
                    /* sold everything to this player */
                    sellProduct(seller, prodToSell, buyers);
                }
            }
        
    }
    
    private void sellProduct(Player player, Product product, int buyers) {
        Product productToSell = player.getWarehouse().getProductOnStock(product);
        double sellingPrice = productToSell.getSellPrice();
        int quantityInStock = productToSell.getQuantityInStock();
        int updatedQuantity = buyers - quantityInStock;
        if (updatedQuantity == 0) /* Sold all his stock */
            player.getWarehouse().getStock().remove(product);
        else /* Else just update the quantity in stock to the new value */
            productToSell.setQuantityInStock(updatedQuantity);
        
        int unitsSold = buyers - updatedQuantity;
        double moneyEarned = productToSell.getSellPrice() * unitsSold;
        player.updateCurrentMoney(moneyEarned);
    }
    
    /* Returns the player who has the best cost benefit of a product */
    public Player getPlayerBestCostBenefit(Product product) {
        double bestCostBenefit = 0.0;
        int bestPlayerIndex = -1;
        for (int i = 0; i < players.size(); i++) {
            Warehouse wh = players.get(i).getWarehouse();
            if (wh.has(product)) 
                if (wh.getProductOnStock(product).getCostBenefit() > bestCostBenefit) {
                    bestCostBenefit = wh.getProductOnStock(product).getCostBenefit();
                    bestPlayerIndex = i;
                }
        }
        return players.get(bestPlayerIndex);        
    }
    
    public PopulationManager getPopulationManager() {
        return populationManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    
    
}
