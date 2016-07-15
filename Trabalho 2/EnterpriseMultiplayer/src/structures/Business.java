package structures;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class Business {
          
    private String name;
    private ArrayList<Building> buildings;
    private ArrayList<Product> products;
    private int bussinessType;
    
    public Business(String name, ArrayList<Building> buildings, ArrayList<Product> products, int bussinessType){
        this.name = name;
        this.buildings = buildings;
        this.products = products;
        this.bussinessType = bussinessType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
    public int getBussinessType() {
        return this.bussinessType;
    }
}
