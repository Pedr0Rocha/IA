package trabalhoia;

import java.util.ArrayList;
import utils.TuplaDouble;

/**
 *
 * @author pedronote
 */
public class Estoque {
    
    private ArrayList<Produto> estoque;
    
    public Estoque(){
        estoque = new ArrayList<Produto>();
    }
    
    public void adicionaNoEstoque(Produto produto){
        int indexNoEstoque = getIndexNoEstoque(produto.nome);
        if (indexNoEstoque >= 0)
            atualizaNoEstoque(produto, indexNoEstoque);
        else
            estoque.add(produto);
    }
    
    private void atualizaNoEstoque(Produto produto, int indexNoEstoque){
        estoque.get(indexNoEstoque).precoVenda = produto.precoVenda;
        estoque.get(indexNoEstoque).quantidadeNoEstoque += produto.quantidadeNoEstoque;
        estoque.get(indexNoEstoque).adicionadoNaRodada = true;
    }
    
    public ArrayList<Produto> clonaEstoque(ArrayList<Produto> antigo, ArrayList<Produto> novo){
        for (Produto e: antigo){
            e.adicionadoNaRodada = false;
            novo.add(e);
        }
        return novo;
    }   
    
    public void imprimeEstoque(){
        System.out.println("");
        System.out.println("ESTOQUE");
        if (estoque.isEmpty()){
            System.out.println("Estoque Vazio.");
            return;
        } 
        for (Produto p : estoque){
            System.out.println("Produto: " + p.nome);
            System.out.println("    Preco Produzir: " + p.precoProduzir);
            System.out.println("    Preco Venda: " + p.precoVenda);
            System.out.println("    Quantidade: " + p.quantidadeNoEstoque);
            System.out.println("    Gasto: " + (p.precoProduzir * p.quantidadeNoEstoque));
        }
    }
    
    private boolean temNoEstoque(String nome, ArrayList<Produto> estoque){
        for (Produto p: estoque)
            if (p.nome.equals(nome)) return true;
        return false;
    }
    
    private int getIndexNoEstoque(String nomeProduto){
        for (int i = 0; i < estoque.size(); i++)
            if (estoque.get(i).nome.equals(nomeProduto)) return i;
        return -1;
    }
    
    public double getValorProducaoEstoque(){
        double valorTotal = 0;
        for (Produto p: estoque)
            valorTotal += (p.precoProduzir * p.quantidadeNoEstoque);
        return valorTotal;
    }
    
    public double getValorVendaEstoque(){
        double valorTotal = 0;
        for (Produto p: estoque)
            valorTotal += (p.precoVenda * p.quantidadeNoEstoque);
        return valorTotal;
    }  
    
    public Produto getProdutoPorNome(String nome){
        for (Produto p: estoque)
            if (p.nome.equals(nome)) return p;
        return null;
    }
    
    public TuplaDouble[] getPossiveisCustosPropaganda(){
        TuplaDouble[] custosPropaganda = new TuplaDouble[3];
        
        custosPropaganda[0].chave = 0.05;
        custosPropaganda[0].valor = getValorVendaEstoque() * custosPropaganda[0].chave;
        
        custosPropaganda[0].chave = 0.15;
        custosPropaganda[1].valor = getValorVendaEstoque() * custosPropaganda[1].chave;
        
        custosPropaganda[0].chave = 0.25;
        custosPropaganda[2].valor = getValorVendaEstoque() * custosPropaganda[2].chave;
        
        return custosPropaganda;
    }
    
    public TuplaDouble[] getPossiveisCustosPesquisa(){
        TuplaDouble[] custosPesquisa = new TuplaDouble[3];
        
        custosPesquisa[0].chave = 0.05;
        custosPesquisa[0].valor = getValorProducaoEstoque() * custosPesquisa[0].chave;
        
        custosPesquisa[0].chave = 0.15;
        custosPesquisa[1].valor = getValorProducaoEstoque() * custosPesquisa[1].chave;
        
        custosPesquisa[0].chave = 0.25;
        custosPesquisa[2].valor = getValorProducaoEstoque() * custosPesquisa[2].chave;
        
        return custosPesquisa;
    }
}
