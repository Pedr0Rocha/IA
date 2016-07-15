package structures;

/**
 *
 * @author pedro
 */
public class Building {  
    
    private String name;
    private double price;
    private int[] monthlyProductionByLevel = new int[3];
    private int level;
    private double monthlyCost;
    private int businessType;
    
    public Building(String name, double price, int monthlyProductionByLevel[], int level, double monthlyCost, int businessType){
        this.name = name;
        this.price = price;
        this.monthlyProductionByLevel = monthlyProductionByLevel;
        this.level = level;
        this.monthlyCost = monthlyCost;
        this.businessType = businessType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int[] getAllMonthlyProduction() {
        return monthlyProductionByLevel;
    }
    
    public int getMonthlyProductionByLevel(int level) {
        return monthlyProductionByLevel[level];
    }

    public int getLevel() {
        return level;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }   

    public int getBusinessType() {
        return businessType;
    }
    
}
