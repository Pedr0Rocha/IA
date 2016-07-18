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
        
    }
    
    public PopulationManager getPopulationManager() {
        return populationManager;
    }
    
}
