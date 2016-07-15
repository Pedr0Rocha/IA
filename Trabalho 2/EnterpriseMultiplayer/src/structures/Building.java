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
    
    public Building(String name, double price, int monthlyProductionByLevel[], int level, double monthlyCost){
        this.name = name;
        this.price = price;
        this.monthlyProductionByLevel = monthlyProductionByLevel;
        this.level = level;
        this.monthlyCost = monthlyCost;
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
}
