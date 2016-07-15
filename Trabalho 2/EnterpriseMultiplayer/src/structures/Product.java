package structures;

/**
 *
 * @author pedro
 */
public class Product {
    
    private String name;
    private int level;
    private double sellPrice;
    private double craftingCost;
    private double costBenefit;
    private boolean addedThisRound;
    private double[] possibleSellPrices = new double[3];
    
    public Product(String name, int level, double craftingCost, double sellingPrice){
        this.name = name;
        this.level = level;
        this.craftingCost = craftingCost;
        this.sellPrice = sellingPrice;
        
        this.possibleSellPrices[0] = craftingCost * 1.25;
        this.possibleSellPrices[1] = craftingCost * 1.50;
        this.possibleSellPrices[2] = craftingCost * 2.0;
    }
    
    public Product(Product clone){
        this.name = clone.name;
        this.level = clone.level;
        this.sellPrice = 0;
        this.craftingCost = clone.craftingCost;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getCraftingCost() {
        return craftingCost;
    }

    public double getCostBenefit() {
        return costBenefit;
    }

    public void setCostBenefit(double costBenefit) {
        this.costBenefit = costBenefit;
    }

    public boolean isAddedThisRound() {
        return addedThisRound;
    }

    public void setAddedThisRound(boolean addedThisRound) {
        this.addedThisRound = addedThisRound;
    }

    public double[] getPossibleSellPrices() {
        return possibleSellPrices;
    }   
    
}
