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
    private double[] possibleSellPrices = new double[3];
    private int businessType;
    private int quantityInStock;
    
    public Product(String name, int level, double craftingCost, double sellingPrice, int businessType){
        this.name = name;
        this.level = level;
        this.craftingCost = craftingCost;
        this.sellPrice = sellingPrice;
        this.businessType = businessType;
        
        this.possibleSellPrices[0] = craftingCost * 1.25;
        this.possibleSellPrices[1] = craftingCost * 1.50;
        this.possibleSellPrices[2] = craftingCost * 2.0;
    }
    
    public Product(Product clone){
        this.name = clone.name;
        this.level = clone.level;
        this.craftingCost = clone.craftingCost;
        this.businessType = clone.businessType;
        this.sellPrice = 0;
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
    
    public void setCostBenefit(double marketingInvestment, double researchInvestment) {
        double costRelation = 2 / (this.sellPrice/this.craftingCost);
        double costBenefit = costRelation * 0.5 + marketingInvestment * 0.35 + researchInvestment * 0.15;
        this.costBenefit = costBenefit;
    }

    public double[] getPossibleSellPrices() {
        return possibleSellPrices;
    }   
    
    public int getBusinessType() {
        return businessType;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    
    public void updateQuantityInStock(int quantityToAdd) {
        this.quantityInStock += quantityToAdd;
    }
     
}
