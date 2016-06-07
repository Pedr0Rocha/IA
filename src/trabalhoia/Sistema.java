package trabalhoia;

import java.util.ArrayList;
import java.util.Random;
import ui.MenuWindow;

/**
 *
 * @author Pedro
 */
public class Sistema {
    
    Random random = new Random();
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
        calculaDemanda(rodada);
        calculaVenda(rodada);
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
        if (consumidoresProduto[0] > 0 && (temNoEstoque(rodada.negocio.produtos.get(0).nome, rodada.estoque) 
                                        ||(temNoEstoque(rodada.negocio.produtos.get(0).nome, rodada.estoque2)))){
            
        }
        if (consumidoresProduto[1] > 0 && (temNoEstoque(rodada.negocio.produtos.get(1).nome, rodada.estoque) 
                                        ||(temNoEstoque(rodada.negocio.produtos.get(1).nome, rodada.estoque2)))){
            
        }
        if (consumidoresProduto[2] > 0 && (temNoEstoque(rodada.negocio.produtos.get(2).nome, rodada.estoque) 
                                        ||(temNoEstoque(rodada.negocio.produtos.get(2).nome, rodada.estoque2)))){
            
        }
    }
    
    private void calculaCustoBeneficio(Settings rodada){
        for (Produto p: rodada.estoque){
            double custo = p.precoProduzir/p.precoVenda;
            if (custo == 2){
                
            } else if (custo == 1.5){
                
            } else if (custo == 1.25){
                
            }
        }
    }
    
    private double calculaLucro(){
        return 0;
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
    
}
