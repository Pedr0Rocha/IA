package trabalhoia;

/**
 *
 * @author Pedro
 */
public class Produto {
    
    public String nome;
    public int nivelEstruturaNecessario;
    public double precoVenda;
    public double precoProduzir;
    public int quantidadeNoEstoque;
    public double custoBeneficio;
    public boolean adicionadoNaRodada;
    
    public Produto(String nome, int nivelEstrutura, double precoProduzir, double precoVenda){
        this.nome = nome;
        this.nivelEstruturaNecessario = nivelEstrutura;
        this.precoVenda = precoVenda;
        this.precoProduzir = precoProduzir;
    }
    
    public Produto(Produto clone){
        this.nome = clone.nome;
        this.nivelEstruturaNecessario = clone.nivelEstruturaNecessario;
        this.precoVenda = 0;
        this.precoProduzir = clone.precoProduzir;
    }
}
