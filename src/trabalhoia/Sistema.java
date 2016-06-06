package trabalhoia;

import java.util.Random;

/**
 *
 * @author Pedro
 */
public class Sistema {
    
    Random random = new Random();
    
    public Sistema(){
        
    }
    
    
    public void calculaVenda(Settings rodada){
        int gastoPropaganda = getPorcentagemPropaganda(rodada)[0];
        int gastoPropaganda2 = getPorcentagemPropaganda(rodada)[1];
        
        int porcentagem;
        porcentagem = random.nextInt(100);
        if (porcentagem < gastoPropaganda){
            System.out.println("");
            System.out.println("Jogador 1 conquistou clientes com sua propaganda");
            for (Produto p : rodada.estoque){
                // TODO - CHECAR GASTO COM PESQUISA PARA CALCULAR QUAIS FORAM VENDIDOS E QUAL PRECO
                rodada.estoque.remove(p);
                rodada.lucro += (p.precoVenda * p.quantidadePorMes);
            }
            
        } else {
            
        }
        
        porcentagem = random.nextInt(100);
        if (porcentagem < gastoPropaganda2){
            System.out.println("");
            System.out.println("Jogador 2 conquistou clientes com sua propaganda");
            for (Produto p : rodada.estoque2){
                // TODO - CHECAR GASTO COM PESQUISA PARA CALCULAR QUAIS FORAM VENDIDOS E QUAL PRECO
                rodada.estoque2.remove(p);
                rodada.lucro2 += (p.precoVenda * p.quantidadePorMes);
            }
        } else {
        }
    }
    
    public double calculaLucro(){
        return 0;
    }
    
    private int[] getPorcentagemPropaganda(Settings rodada){
        int[] gastoProp = new int[2];
        if (rodada.gastoPropaganda == Jogo.getGastoPropaganda()[0]){
            gastoProp[0] = 20;
        } else if (rodada.gastoPropaganda == Jogo.getGastoPropaganda()[1]){
            gastoProp[0] = 55;
        } else if (rodada.gastoPropaganda == Jogo.getGastoPropaganda()[2]){
            gastoProp[0] = 90;
        }
        
        if (rodada.gastoPropaganda2 == Jogo.getGastoPropaganda()[0]){
            gastoProp[1] = 20;
        } else if (rodada.gastoPropaganda2 == Jogo.getGastoPropaganda()[1]){
            gastoProp[1] = 55;
        } else if (rodada.gastoPropaganda2 == Jogo.getGastoPropaganda()[2]){
            gastoProp[1] = 90;
        }
        
        return gastoProp;
    }
    
}
