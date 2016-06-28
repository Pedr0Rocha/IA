package trabalhoia;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Settings {
    
    public double investimentoInicial;
    public Estrutura predio;
    public double gastoPropaganda;
    public double gastoPesquisa;
    public double gastosFixos;
    public double dinheiroTotal;
    public ArrayList<Produto> estoque = new ArrayList<Produto>();
    public double lucro;
    public int mesesEmPrejuizo;
    
    
    public double investimentoInicial2;
    public Estrutura predio2;
    public double gastoPropaganda2;
    public double gastoPesquisa2;
    public double gastosFixos2;
    public double dinheiroTotal2;
    public ArrayList<Produto> estoque2 = new ArrayList<Produto>();
    public double lucro2;
    public int mesesEmPrejuizo2;
    
    public Jogador jogador1;
    public Jogador jogador2;
    
    public int meses;
    public int tipoGame;
    public Negocio negocio;
    public int rodada;
    
    public Produto getProdutoByNome(String nome, ArrayList<Produto> estoque){
        for (Produto p: estoque)
            if (p.nome.equals(nome)) return p;
        return null;
    }
}
