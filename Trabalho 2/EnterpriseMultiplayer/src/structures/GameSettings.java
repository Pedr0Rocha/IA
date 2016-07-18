package structures;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class GameSettings {
    
    private int maxMonths;
    private int currentMonth;
    private double initialMoney;
    
    private Business business;
    
    public GameSettings(int maxMonths, double initialMoney, Business business) {
        this.maxMonths = maxMonths;
        this.initialMoney = initialMoney;
        this.business = business;
    }

    public int getMaxMonths() {
        return maxMonths;
    }
    
    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public double getInitialMoney() {
        return initialMoney;
    }

    public Business getBusiness() {
        return business;
    }
}
