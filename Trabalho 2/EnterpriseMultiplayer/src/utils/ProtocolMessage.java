package utils;

/**
 *
 * @author pedro
 */
public class ProtocolMessage {
    
    private String serializedWarehouse;
    private double marketingInvestment;
    private double researchInvestment;
    private double profit;
    
    public ProtocolMessage(String wh, double mi, double ri) {
        this.serializedWarehouse = wh;
        this.marketingInvestment = mi;
        this.researchInvestment = ri;
    }

    public String serializeMessage() {
        String seri = "";
        seri += serializedWarehouse;
        seri += "|" + marketingInvestment;
        seri += "|" + researchInvestment;
        seri += "|" + profit;
        
        return seri;
    }

    public String getSerializedWarehouse() {
        return serializedWarehouse;
    }

    public void setSerializedWarehouse(String serializedWarehouse) {
        this.serializedWarehouse = serializedWarehouse;
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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
    
}
