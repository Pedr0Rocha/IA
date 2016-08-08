package structures;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author pedronote
 */
public class Player {
    
    GameDatabase db = GameDatabase.getInstance();
    
    private String name;
    private int IA;
    private double currentMoney;
    private Building building;
    private Warehouse warehouse;
    private int businessType;
    private double marketingInvestment;
    private double researchInvestment;
    private double profitThisRound = 0;
    private boolean ableToSellThisRound = false;
    
    public Player(String name, int IA) {
        this.name = name;
        this.IA = IA;
        this.building = db.getBuildings().get(0);
        this.warehouse = new Warehouse();
    }

    public String getName() {
        return name;
    }

    public int getIA() {
        return IA;
    }

    public void setIA(int IA) {
        this.IA = IA;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }
    
    public void updateCurrentMoney(double moneyToAdd) {
        this.currentMoney += moneyToAdd;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public double getMarketingInvestment() {
        return marketingInvestment;
    }

    public void setMarketingInvestment(double marketingInvestment) {
        this.marketingInvestment = marketingInvestment;
    }

    public double getResearchInvestment() {
        return researchInvestment;
    }

    public void setResearchInvestment(double researchInvestment) {
        this.researchInvestment = researchInvestment;
    }

    public double getProfitThisRound() {
        return profitThisRound;
    }

    public void setProfitThisRound(double profitThisRound) {
        this.profitThisRound = profitThisRound;
    }
    
    public void updateProfitThisRound(double profitToAdd) {
        this.profitThisRound += profitToAdd;
    }

    public boolean isAbleToSellThisRound() {
        return ableToSellThisRound;
    }

    public void setAbleToSell(boolean ableToSellThisRound) {
        this.ableToSellThisRound = ableToSellThisRound;
    }
    
    
}
