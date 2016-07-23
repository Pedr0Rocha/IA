package utils;

import java.util.Random;

/**
 *
 * @author pedro
 */
public class PopulationManager {
    
    Random rand = new Random();
    
    private int interestedCustomers;
    private int[] customersByProductLevel = new int[3];
    
    public PopulationManager(double marketingInvestments) {
        this.interestedCustomers = (int)Math.round(CONSTANTS.POPULATION * marketingInvestments/100);
        arrangeCustomers();
    }
    
    private void arrangeCustomers() {
        customersByProductLevel[0] = (int)Math.round(interestedCustomers * CONSTANTS.LEVEL0_INTEREST);
        customersByProductLevel[1] = (int)Math.round(interestedCustomers * CONSTANTS.LEVEL1_INTEREST);
        customersByProductLevel[2] = (int)Math.round(interestedCustomers * CONSTANTS.LEVEL2_INTEREST);
    }

    public int getInterestedCustomers() {
        return interestedCustomers;
    }

    public int[] getAllCustomersByProductLevel() {
        return customersByProductLevel;
    }
    
    public int getCustomerByProductLevel(int prodLevel) {
        return customersByProductLevel[prodLevel];
    }
    
    public boolean productLevelHasDemand(int level) {
        return customersByProductLevel[level] > 0 ? true : false;
    }
    
    
}
