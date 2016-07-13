package utils;

import java.util.Random;

/**
 *
 * @author pedronote
 */
public class Utils {
    
    Random random;
    
    public Utils(){
        random = new Random();
    }    
    
    public void clearConsole(){
        for (int i = 0; i < 50; i++)
            System.out.println("");
    }
    
    public boolean flipCoin(){
        return (random.nextInt(100) > 50);
    } 
}
