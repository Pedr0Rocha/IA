package trabalhoia;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import ui.MenuWindow;

/**
 *
 * @author Pedro
 */
public class Sistema {
    
    Random random = new Random();
    Scanner scan = new Scanner(System.in);
    int consumidores;
    int consumidoresInteressados = 0;
    
    int[] consumidoresProduto = new int[3];
    
    public Sistema(int pessoas){
        this.consumidores = pessoas;
    }
    
    /*
        1 - dividir o numero de pessoas que querem ou não comprar algo (relacao de propaganda dos dois somados)
        2 - ver quais pessoas querem comprar quais produtos. Pesos maiores para produtos de nivel mais baixo
        3 - fazer um custo beneficio entre os produtos que serao comprados (relacao de preco de venda com propaganda e pesquisa)
        4 - pessoas compram produtos.
    */
    
    public void turnoSistema(Settings rodada){
        clearConsole();
        System.out.println("TURNO DO SISTEMA");
        calculaDemanda(rodada);
        calculaVenda(rodada);
        calculaResultados(rodada);
        
        System.out.println("Digite qualquer tecla e aperte enter para continuar");
        scan.next();   
        clearConsole();
    }
    
    private void calculaDemanda(Settings rodada){
        double gastoPropaganda = getPorcentagemPropaganda(rodada)[0];
        double gastoPropaganda2 = getPorcentagemPropaganda(rodada)[1];
        System.out.println("");
        System.out.println("Demanda:");
        consumidoresInteressados = (int)(consumidores * (gastoPropaganda/100) * (gastoPropaganda2/100));
        if (MenuWindow.debugMode)
            System.out.println("Consumidores Interessados = Consumidores * (Gasto em Propaganda/100) * (Gasto em Propaganda2/100)");
        System.out.println("De " + consumidores + " consumidores, " + consumidoresInteressados 
                            + " estao interessados em comprar produtos na area de " + rodada.negocio.ramo);
        
        consumidoresProduto[0] = (int)Math.round(consumidoresInteressados * 0.5);
        consumidoresProduto[1] = (int)Math.round(consumidoresInteressados * 0.40);
        consumidoresProduto[2] = (int)Math.round(consumidoresInteressados * 0.10);
        
        System.out.println("");
        for (int i = 0; i < rodada.negocio.produtos.size(); i++){
            System.out.println("Interessados em " + (rodada.negocio.produtos.get(i).nome).toUpperCase());
            System.out.println("    " + consumidoresProduto[i]);
        }
    }
    
    private void calculaVenda(Settings rodada){
        Produto produto0 = rodada.negocio.produtos.get(0);
        Produto produto1 = rodada.negocio.produtos.get(1);
        Produto produto2 = rodada.negocio.produtos.get(2);
        calculaCustoBeneficio(rodada);
        
        if (consumidoresProduto[0] > 0 && (temNoEstoque(produto0.nome, rodada.estoque) ||(temNoEstoque(produto0.nome, rodada.estoque2)))){
            Produto prodJogador1 = rodada.getProdutoByNome(produto0.nome, rodada.estoque);
            Produto prodJogador2 = rodada.getProdutoByNome(produto0.nome, rodada.estoque2);
            if ((temNoEstoque(produto0.nome, rodada.estoque) && (temNoEstoque(produto0.nome, rodada.estoque2)))){
                if (prodJogador1.custoBeneficio > prodJogador2.custoBeneficio)
                    vendeProduto(1, prodJogador1, rodada, consumidoresProduto[0], true);
                else
                    vendeProduto(2, prodJogador2, rodada, consumidoresProduto[0], true);
                
            } else if (temNoEstoque(produto0.nome, rodada.estoque)){
                vendeProduto(1, prodJogador1, rodada, consumidoresProduto[0], false);
            } else if (temNoEstoque(produto0.nome, rodada.estoque2)){
                vendeProduto(2, prodJogador2, rodada, consumidoresProduto[0], false);
            }        
        }
        if (consumidoresProduto[1] > 0 && (temNoEstoque(produto1.nome, rodada.estoque) ||(temNoEstoque(produto1.nome, rodada.estoque2)))){
            Produto prodJogador1 = rodada.getProdutoByNome(produto1.nome, rodada.estoque);
            Produto prodJogador2 = rodada.getProdutoByNome(produto1.nome, rodada.estoque2);
            if ((temNoEstoque(produto1.nome, rodada.estoque) && (temNoEstoque(produto1.nome, rodada.estoque2)))){
                if (prodJogador1.custoBeneficio > prodJogador2.custoBeneficio)
                    vendeProduto(1, prodJogador1, rodada, consumidoresProduto[1], true);
                else
                    vendeProduto(2, prodJogador2, rodada, consumidoresProduto[1], true);
                
            } else if (temNoEstoque(produto1.nome, rodada.estoque)){
                vendeProduto(1, prodJogador1, rodada, consumidoresProduto[1], false);
            } else if (temNoEstoque(produto1.nome, rodada.estoque2)){
                vendeProduto(2, prodJogador2, rodada, consumidoresProduto[1], false);
            } 
        }
        if (consumidoresProduto[2] > 0 && (temNoEstoque(produto2.nome, rodada.estoque) ||(temNoEstoque(produto2.nome, rodada.estoque2)))){
            Produto prodJogador1 = rodada.getProdutoByNome(produto2.nome, rodada.estoque);
            Produto prodJogador2 = rodada.getProdutoByNome(produto2.nome, rodada.estoque2);
            if ((temNoEstoque(produto2.nome, rodada.estoque) && (temNoEstoque(produto2.nome, rodada.estoque2)))){
                if (prodJogador1.custoBeneficio > prodJogador2.custoBeneficio)
                    vendeProduto(1, prodJogador1, rodada, consumidoresProduto[2], true);
                else
                    vendeProduto(2, prodJogador2, rodada, consumidoresProduto[2], true);
                
            } else if (temNoEstoque(produto2.nome, rodada.estoque)){
                vendeProduto(1, prodJogador1, rodada, consumidoresProduto[2], false);
            } else if (temNoEstoque(produto2.nome, rodada.estoque2)){
                vendeProduto(2, prodJogador2, rodada, consumidoresProduto[2], false);
            }            
        }
    }
    
    private void calculaResultados(Settings rodada){
        rodada.dinheiroTotal += rodada.lucro;
        rodada.dinheiroTotal2 += rodada.lucro2;
        
        System.out.println("");
        System.out.println("DINHEIRO TOTAL APÓS RODADA");
        System.out.println("    Jogador 1: " + rodada.dinheiroTotal);
        System.out.println("    Jogador 2: " + rodada.dinheiroTotal2);
        
        if (rodada.dinheiroTotal < 0){
            rodada.mesesEmPrejuizo++;
            System.out.println("Jogador 1 está com saldo negativo. Meses seguidos em prejuízo: " + rodada.mesesEmPrejuizo);
        }
        if (rodada.dinheiroTotal2 < 0){
            rodada.mesesEmPrejuizo2++;
            System.out.println("Jogador 2 está com saldo negativo. Meses seguidos em prejuízo: " + rodada.mesesEmPrejuizo2);
        }
        
        System.out.println("FIM DA RODADA " + rodada.rodada);
    }
    
    private void vendeProduto(int jogador, Produto produto, Settings rodada, int quantidadeVendida, boolean ambosTemProduto){
        if (jogador == 1){
            Produto produtoVender = rodada.getProdutoByNome(produto.nome, rodada.estoque);
            int vendidos = quantidadeVendida;
            if (quantidadeVendida > produtoVender.quantidadePorMes)
                vendidos = produtoVender.quantidadePorMes;
            
            if (quantidadeVendida > produtoVender.quantidadePorMes && ambosTemProduto)
                vendeProduto(2, produto, rodada, (quantidadeVendida - produto.quantidadePorMes), false);
            
            produtoVender.quantidadePorMes -= vendidos;
            if (produtoVender.quantidadePorMes <= 0)
                rodada.estoque.remove(produtoVender);
            
            double ganho = produtoVender.precoVenda * vendidos;
            System.out.println("");
            System.out.println("Jogador 1: " + vendidos + " " + produtoVender.nome + "(s)" +" vendidos.");
            System.out.println("Ganho com as vendas: " + ganho);
            
            rodada.lucro += ganho;
            
        } else {
            Produto produtoVender = rodada.getProdutoByNome(produto.nome, rodada.estoque2);
            int vendidos = quantidadeVendida;
            if (quantidadeVendida > produtoVender.quantidadePorMes)
                vendidos = produtoVender.quantidadePorMes;
            
            if (quantidadeVendida > produtoVender.quantidadePorMes && ambosTemProduto)
                vendeProduto(1, produto, rodada, (quantidadeVendida - produto.quantidadePorMes), false);
            
            produtoVender.quantidadePorMes -= vendidos;
            if (produtoVender.quantidadePorMes <= 0)
                rodada.estoque2.remove(produtoVender);
            
            double ganho = produtoVender.precoVenda * vendidos;
            System.out.println("");
            System.out.println("Jogador 2: " + vendidos + " " + produtoVender.nome + "(s)" +" vendido(s).");
            System.out.println("Ganho com as vendas: " + ganho);
            
            rodada.lucro2 += ganho;
        }
    }
    
    private void calculaCustoBeneficio(Settings rodada){
        System.out.println("");
        System.out.println("Calculando Custo Beneficio");
        if (MenuWindow.debugMode)
            System.out.println("Formula: (2/(PrecoVenda/PrecoProducao)) * 0.5 + GastoPropaganda * 0.2 + GastoPesquisa * 0.2");
        for (Produto p: rodada.estoque){
            double custo = 2/(p.precoVenda/p.precoProduzir);
            double equacao = (custo) * 0.5 + rodada.gastoPropaganda * 0.2 + rodada.gastoPesquisa * 0.2;
            p.custoBeneficio = equacao;
        }
        for (Produto p: rodada.estoque2){
            double custo = 2/(p.precoVenda/p.precoProduzir);
            double equacao = (custo) * 0.5 + rodada.gastoPropaganda2 * 0.2 + rodada.gastoPesquisa2 * 0.2;
            p.custoBeneficio = equacao;
        }
    }
    
    private double[] getPorcentagemPropaganda(Settings rodada){
        double[] gastoProp = new double[2];
        if (rodada.gastoPropaganda == Jogo.getGastoPropaganda()[0]){
            gastoProp[0] = 20.0;
        } else if (rodada.gastoPropaganda == Jogo.getGastoPropaganda()[1]){
            gastoProp[0] = 55.0;
        } else if (rodada.gastoPropaganda == Jogo.getGastoPropaganda()[2]){
            gastoProp[0] = 90.0;
        }
        
        if (rodada.gastoPropaganda2 == Jogo.getGastoPropaganda()[0]){
            gastoProp[1] = 20.0;
        } else if (rodada.gastoPropaganda2 == Jogo.getGastoPropaganda()[1]){
            gastoProp[1] = 55.0;
        } else if (rodada.gastoPropaganda2 == Jogo.getGastoPropaganda()[2]){
            gastoProp[1] = 90.0;
        }
        
        return gastoProp;
    }
    
    private boolean temNoEstoque(String produto, ArrayList<Produto> estoque){
        for (Produto p : estoque)
            if (p.nome.equals(produto))
                return true;
        return false;
    }
    
    private void clearConsole(){
        for (int i = 0; i < 50; i++)
            System.out.println("");
    }
    
}
