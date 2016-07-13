package trabalhoia;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Estrutura {
    
    public String nome;
    public double preco;
    public int[] producaoMensalPorNivel = new int[3];
    public int nivel;
    public double gastoFixo;
    
    public Estrutura(String nome, double preco, int producaoMensal[], int nivel, double gastoFixo){
        this.nome = nome;
        this.preco = preco;
        this.producaoMensalPorNivel = producaoMensal;
        this.nivel = nivel;
        this.gastoFixo = gastoFixo;
    }
}
