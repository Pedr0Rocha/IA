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
        resetLastRoundProfits();
        int marketingInvestment = calcMarketingInvestments();
        populationManager = new PopulationManager(marketingInvestment);
        sellingManager();
    }
    
    private void resetLastRoundProfits() {
        for (Player p : players)
            p.setProfitThisRound(0);
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
        ArrayList<Player> temporarySellers = players;
        ArrayList<Product> products = db.getProductsByBusinessType(players.get(0).getBusinessType());
        /* if the product has a demand, sell to the player who has the best cost benefit of the product */        
        for (int i = 0; i < 4; i++) {
            if (populationManager.productLevelHasDemand(i)) {
                int buyers = populationManager.getCustomerByProductLevel(i);
                while(buyers >= 1) {
                    Player seller = getPlayerBestCostBenefit(products.get(i));
                    Product prodToSell = seller.getWarehouse().getProductOnStock(products.get(i));
                    if (buyers > prodToSell.getQuantityInStock()) {
                        /* multiple sales */
                        int quantityBeforeSale = prodToSell.getQuantityInStock();
                        sellProduct(seller, prodToSell, buyers);
                        buyers -= quantityBeforeSale;
                    } else {
                        /* sold everything to this player */
                        sellProduct(seller, prodToSell, buyers);
                        buyers = 0;
                    }
                    seller.setAbleToSell(false);
                }
            }
            for (Player p : players)
                p.setAbleToSell(true);
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
        player.updateProfitThisRound(moneyEarned);
    }
    
    /* Returns the player who has the best cost benefit of a product */
    public Player getPlayerBestCostBenefit(Product product) {
        double bestCostBenefit = 0.0;
        int bestPlayerIndex = -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isAbleToSellThisRound()) {
                Warehouse wh = players.get(i).getWarehouse();
                if (wh.has(product)) 
                    if (wh.getProductOnStock(product).getCostBenefit() > bestCostBenefit) {
                        bestCostBenefit = wh.getProductOnStock(product).getCostBenefit();
                        bestPlayerIndex = i;
                    }
            }
        }
        return players.get(bestPlayerIndex);        
    }
    
    public void showProfits() {
        ArrayList<Player> temporaryPlayers = players;
        Player[] orderedPlayers = new Player[players.size()];
        int max = Integer.MIN_VALUE;
        int index = 0;
        System.out.println("Profits this round:");
        for (Player p : temporaryPlayers) {
            if (p.getProfitThisRound() > max) {
                orderedPlayers[0] = p;
                temporaryPlayers.remove(p);
            }
        }
    }
    
    public PopulationManager getPopulationManager() {
        return populationManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    
    
}
