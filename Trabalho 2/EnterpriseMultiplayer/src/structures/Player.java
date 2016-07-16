package structures;

import java.util.ArrayList;

/**
 *
 * @author pedronote
 */
public class Player {
    
    GameDatabase db = GameDatabase.getInstance();
    
    private String name;
    private boolean IA;
    private double currentMoney;
    private Building building;
    private Warehouse warehouse;
    private int businessType;
    
    public Player(String name, boolean IA) {
        this.name = name;
        this.IA = IA;
        this.building = db.getBuildings().get(2);
        this.warehouse = new Warehouse();
    }

    public String getName() {
        return name;
    }
    
    public boolean isIA() {
        return IA;
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
    
    
    
}
