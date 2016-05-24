package trabalhoia;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Negocio {
      
    public String ramo;
    public ArrayList<Estrutura> predios;
    public ArrayList<Produto> produtos;
    
    public Negocio(String ramo, ArrayList<Estrutura> predios, ArrayList<Produto> produtos){
        this.ramo = ramo;
        this.predios = predios;
        this.produtos = produtos;
    }
    
}
