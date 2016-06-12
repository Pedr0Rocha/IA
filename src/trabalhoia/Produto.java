/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia;

/**
 *
 * @author Pedro
 */
public class Produto {
    
    public String nome;
    public int nivelEstrutura;
    public double precoVenda;
    public double precoProduzir;
    public int quantidadePorMes;
    public double custoBeneficio;
    public boolean adicionadoNaRodada;
    
    public Produto(String nome, int nivelEstrutura, double precoProduzir, double precoVenda){
        this.nome = nome;
        this.nivelEstrutura = nivelEstrutura;
        this.precoVenda = precoVenda;
        this.precoProduzir = precoProduzir;
    }
    
    public Produto(Produto clone){
        this.nome = clone.nome;
        this.nivelEstrutura = clone.nivelEstrutura;
        this.precoVenda = 0;
        this.precoProduzir = clone.precoProduzir;
    }
}
