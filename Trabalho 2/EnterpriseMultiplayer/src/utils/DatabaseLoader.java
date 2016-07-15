package utils;

import java.util.ArrayList;
import structures.Building;
import structures.Business;
import structures.GameDatabase;
import structures.Product;

/**
 *
 * @author pedro
 */
public class DatabaseLoader {
    
    GameDatabase db = GameDatabase.getInstance();
    
    public DatabaseLoader() {
        System.out.println("Loading Database...");
        loadDatabase();
        System.out.println("Database loaded successfully");
    }
    
    private void loadDatabase() {
        loadBuildings();
        loadProducts();
        loadBusinesses();
    }
    
    private void loadBuildings() {
        for (Building techBuilding : createTechBuildings())
            db.addItemToDatabase(techBuilding);
        
        // add whatever kind of buildings here
    }
    
    private void loadProducts() {
        db.addItemToDatabase(new Product("Website", 0, 2000.00, 00.00, CONSTANTS.TECHBUSSINESS));
        db.addItemToDatabase(new Product("Mobile App", 1, 4000.00, 00.00, CONSTANTS.TECHBUSSINESS));
        db.addItemToDatabase(new Product("Customized System", 2, 10000.00, 00.00, CONSTANTS.TECHBUSSINESS));
        
        // add more products here
    }
    
    private void loadBusinesses() {
        ArrayList<Product> productsByType = new ArrayList<Product>();
        ArrayList<Building> buildingsByType = new ArrayList<Building>();
        
        for (Product p : db.getProducts())
            if (p.getBusinessType() == CONSTANTS.TECHBUSSINESS)
                productsByType.add(p);  
        for (Building b : db.getBuildings())
            if (b.getBusinessType() == CONSTANTS.TECHBUSSINESS)
                buildingsByType.add(b);
        Business techBussiness = new Business("Technology", buildingsByType, productsByType, CONSTANTS.TECHBUSSINESS);
        
        db.addItemToDatabase(techBussiness);
        
    }
    
    private ArrayList<Building> createTechBuildings() {
        ArrayList<Building> buildingsList = new ArrayList<Building>();
        
        int[] monthlyProductionLevel0 = new int[3];
        monthlyProductionLevel0[0] = 10;
        monthlyProductionLevel0[1] = 0;
        monthlyProductionLevel0[2] = 0;
        buildingsList.add(new Building("Corner Office", 30000.00, monthlyProductionLevel0, 0, 80.00, CONSTANTS.TECHBUSSINESS));
                
        int[] monthlyProductionLevel1 = new int[3];
        monthlyProductionLevel1[0] = 20;
        monthlyProductionLevel1[1] = 7;
        monthlyProductionLevel1[2] = 0;
        buildingsList.add(new Building("Executive Suite", 80000.00, monthlyProductionLevel1, 1, 200.00, CONSTANTS.TECHBUSSINESS));
        
        int[] monthlyProductionLevel2 = new int[3];
        monthlyProductionLevel2[0] = 35;
        monthlyProductionLevel2[1] = 14;
        monthlyProductionLevel2[2] = 5;
        buildingsList.add(new Building("Tech Tower", 350000.00, monthlyProductionLevel2, 2, 400.00, CONSTANTS.TECHBUSSINESS));
        
        return buildingsList;
    }
}
