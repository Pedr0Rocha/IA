package structures;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class GameDatabase {
    
    private ArrayList<Building> buildings = new ArrayList<Building>();
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Business> businesses = new ArrayList<Business>();
    
    private GameDatabase(){};
    
    private static GameDatabase INSTANCE;
    
    public static GameDatabase getInstance() {
        if (INSTANCE == null) 
            INSTANCE = new GameDatabase();
        return INSTANCE;
    }
    
    public void addItemToDatabase(Object item) {
        if (item instanceof Building)
            buildings.add((Building) item);
        if (item instanceof Product)
            products.add((Product) item);
        if (item instanceof Business)
            businesses.add((Business) item);
        else
            System.out.println("Cant add item to any table at database.");
    }
    
    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }
    public ArrayList<Product> getProducts() {
        return this.products;
    }
    public ArrayList<Business> getBusinesses() {
        return this.businesses;
    }
    
}
