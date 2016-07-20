package structures;

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
    
    
    
}