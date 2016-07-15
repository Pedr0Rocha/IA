package structures;

/**
 *
 * @author pedronote
 */
public class Player {
    
    private String name;
    private boolean IA;
    
    public Player(String name, boolean IA) {
        this.name = name;
        this.IA = IA;
    }

    public String getName() {
        return name;
    }
    
    public boolean isIA() {
        return IA;
    }    
}
