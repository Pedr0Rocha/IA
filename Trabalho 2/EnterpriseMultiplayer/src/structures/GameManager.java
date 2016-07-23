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
    
    private void sellProducts() {
        ArrayList<Product> products = db.getProductsByBusinessType(players.get(0).getBusinessType());
        /* if the product has a demand, sell to the player who has the best cost benefit of the product */        
        for (int i = 0; i < 4; i++) 
            if (populationManager.productLevelHasDemand(i)) {
                Player playerSeller = getPlayerBestCostBenefit(products.get(i));
                /* sell to this player */
                /* check if theres still customers left to buy the product */
            }
        
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
