package trabalhoia;

/**
 *
 * @author Pedro
 */
public class Estrutura {
    
    public String nome;
    public double preco;
    public int[] producao = new int[3];
    public int nivel;
    public double gastoFixo;
    
    public Estrutura(String nome, double preco, int producao0, int producao1, int producao2, int nivel, double gastoFixo){
        this.nome = nome;
        this.preco = preco;
        this.producao[0] = producao0;
        this.producao[1] = producao1;
        this.producao[2] = producao2;
        this.nivel = nivel;
        this.gastoFixo = gastoFixo;
    }
}
