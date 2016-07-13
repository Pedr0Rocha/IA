package trabalhoia;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Settings {
    public Jogador jogador1;
    public Jogador jogador2;
    
    public int maximoMeses;
    public int rodada;
    
    public double investimentoInicial;
    
    public int tipoJogo;
    public Negocio negocio;
    
    public Settings(int tipoJogo){
        this.tipoJogo = tipoJogo;
    }
}
