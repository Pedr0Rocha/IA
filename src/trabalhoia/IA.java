package trabalhoia;

import java.util.ArrayList;

/**
 *
 * @author pedrodesktop
 */
public class IA {

    Settings confInicial;
    
    public IA(Settings config, Settings confInicial){
        this.confInicial = confInicial;
    }
    
    public Settings montaPossibilidade(Settings p){
            double dinheiro = p.dinheiroTotal;
            if (p.rodada == 0) dinheiro = p.investimentoInicial;
            double upgradeEstrutura = upgradeEstrutura(1, p);
            //Settings p = new Settings();
            //atualizaConfig(config);
            
            if (upgradeEstrutura > 0){
                dinheiro -= upgradeEstrutura;
                p.dinheiroTotal = dinheiro;
                p.predio = getEstruturaByPreco(upgradeEstrutura, confInicial.negocio.predios);

                escolhaGulosaProdutos(1, p, confInicial, dinheiro);
                
                double valorVendaEstoque = getValorVendaEstoque(p.estoque);
                double valorProducaoEstoque = getValorProducaoEstoque(p.estoque);
                escolhaGulosaPropaganda(1, p, dinheiro, valorVendaEstoque);
                escolhaGulosaPesquisa(1, p, dinheiro, valorProducaoEstoque);
            } else {
                p.dinheiroTotal = dinheiro;

                escolhaGulosaProdutos(1, p, confInicial, dinheiro);
                
                double valorVendaEstoque = getValorVendaEstoque(p.estoque);
                double valorProducaoEstoque = getValorProducaoEstoque(p.estoque);
                escolhaGulosaPropaganda(1, p, dinheiro, valorVendaEstoque);
                escolhaGulosaPesquisa(1, p, dinheiro, valorProducaoEstoque);
            }
            
            dinheiro = p.dinheiroTotal2;
            if (p.rodada == 0) dinheiro = p.investimentoInicial;
            upgradeEstrutura = upgradeEstrutura(2, p);
            
            if (upgradeEstrutura > 0){
                dinheiro -= upgradeEstrutura;
                p.dinheiroTotal2 = dinheiro;
                p.predio2 = getEstruturaByPreco(upgradeEstrutura, confInicial.negocio.predios);

                escolhaGulosaProdutos(2, p, confInicial, dinheiro);
                
                double valorVendaEstoque = getValorVendaEstoque(p.estoque2);
                double valorProducaoEstoque = getValorProducaoEstoque(p.estoque2);
                escolhaGulosaPropaganda(2, p, dinheiro, valorVendaEstoque);
                escolhaGulosaPesquisa(2, p, dinheiro, valorProducaoEstoque);
                return p;
            } else {
                p.dinheiroTotal2 = dinheiro;

                escolhaGulosaProdutos(2, p, confInicial, dinheiro);
                
                double valorVendaEstoque = getValorVendaEstoque(p.estoque2);
                double valorProducaoEstoque = getValorProducaoEstoque(p.estoque2);
                escolhaGulosaPropaganda(2, p, dinheiro, valorVendaEstoque);
                escolhaGulosaPesquisa(2, p, dinheiro, valorProducaoEstoque);
                return p;
            }
    }
    
    public Settings montaPossibilidadeJogador(Settings p){
            double dinheiro = p.dinheiroTotal2;
            if (p.rodada == 0) dinheiro = p.investimentoInicial;
            double upgradeEstrutura = upgradeEstrutura(2, p);
            
            if (upgradeEstrutura > 0){
                dinheiro -= upgradeEstrutura;
                p.dinheiroTotal2 = dinheiro;
                p.predio2 = getEstruturaByPreco(upgradeEstrutura, confInicial.negocio.predios);

                escolhaGulosaProdutos(2, p, confInicial, dinheiro);
                
                double valorVendaEstoque = getValorVendaEstoque(p.estoque2);
                double valorProducaoEstoque = getValorProducaoEstoque(p.estoque2);
                escolhaGulosaPropaganda(2, p, dinheiro, valorVendaEstoque);
                escolhaGulosaPesquisa(2, p, dinheiro, valorProducaoEstoque);
                return p;
            } else {
                p.dinheiroTotal2 = dinheiro;

                escolhaGulosaProdutos(2, p, confInicial, dinheiro);
                
                double valorVendaEstoque = getValorVendaEstoque(p.estoque2);
                double valorProducaoEstoque = getValorProducaoEstoque(p.estoque2);
                escolhaGulosaPropaganda(2, p, dinheiro, valorVendaEstoque);
                escolhaGulosaPesquisa(2, p, dinheiro, valorProducaoEstoque);
                return p;
            }
    }
    
    private void atualizaConfig(Settings config){
        config.negocio = confInicial.negocio;
    }
    
    private void escolhaGulosaProdutos(int jogador, Settings p, Settings confInicial, double dinheiro){
        if (jogador == 1){
            for (int i = 0; i < confInicial.negocio.produtos.size(); i++){
                if (!temNoEstoque(confInicial.negocio.produtos.get(i).nome, p.estoque)){
                    int quantidadePossivel = p.predio.producao[i];
                    int quantidade = 0;
                    while (quantidadePossivel != 0 || quantidade != 0){
                        if (dinheiro > confInicial.negocio.produtos.get(i).precoProduzir * quantidadePossivel){
                            quantidade = p.predio.producao[i];
                            break;
                        } else {
                            quantidadePossivel--;
                        }
                    }
                    if (quantidadePossivel != 0){
                        Produto prod = new Produto(confInicial.negocio.produtos.get(i));
                        prod.precoVenda = 2 * prod.precoProduzir;
                        prod.quantidadePorMes = quantidade;
                        p.estoque.add(prod);    
                    }       
                }
            }
            for (Produto produto: p.estoque)
                p.dinheiroTotal -= produto.precoProduzir * produto.quantidadePorMes;
        } else {
            for (int i = 0; i < confInicial.negocio.produtos.size(); i++){
                if (!temNoEstoque(confInicial.negocio.produtos.get(i).nome, p.estoque2)){
                    int quantidadePossivel = p.predio2.producao[i];
                    int quantidade = 0;
                    while (quantidadePossivel != 0){
                        if (dinheiro > confInicial.negocio.produtos.get(i).precoProduzir * quantidadePossivel){
                            quantidade = p.predio2.producao[i];
                            break;
                        } else {
                            quantidadePossivel--;
                        }
                    }
                    if (quantidadePossivel != 0){
                        Produto prod = new Produto(confInicial.negocio.produtos.get(i));
                        prod.precoVenda = 2 * prod.precoProduzir;
                        prod.quantidadePorMes = quantidade;
                        p.estoque2.add(prod);                    
                    }
                }
            }
            for (Produto produto: p.estoque2)
                p.dinheiroTotal2 -= produto.precoProduzir * produto.quantidadePorMes;
            
        }
    }
    
    private void escolhaGulosaPropaganda(int jogador, Settings p, double dinheiro, double valorEstoque){
        if (jogador == 1){
            if (dinheiro > valorEstoque * 0.25){
                p.gastoPropaganda = 0.25; 
            } else if (dinheiro > valorEstoque * 0.15){
                p.gastoPropaganda = 0.15;
            } else {
                p.gastoPropaganda = 0.05;
            }
        } else {
            if (dinheiro > valorEstoque * 0.25){
                p.gastoPropaganda2 = 0.25; 
            } else if (dinheiro > valorEstoque * 0.15){
                p.gastoPropaganda2 = 0.15;
            } else {
                p.gastoPropaganda2 = 0.05;
            }
        }
    }
    
    private void escolhaGulosaPesquisa(int jogador, Settings p, double dinheiro, double valorEstoque){
        if (jogador == 1){
            if (dinheiro > valorEstoque * 0.25){
                p.gastoPesquisa = 0.25; 
            } else if (dinheiro > valorEstoque * 0.15){
                p.gastoPesquisa = 0.15;
            } else {
                p.gastoPesquisa = 0.05;
            }
        } else {
            if (dinheiro > valorEstoque * 0.25){
                p.gastoPesquisa2 = 0.25; 
            } else if (dinheiro > valorEstoque * 0.15){
                p.gastoPesquisa2 = 0.15;
            } else {
                p.gastoPesquisa2 = 0.05;
            }
        }
    }
    
    private double upgradeEstrutura(int jogador, Settings config){
        double sobra = 15000.0;
        if (config.predio != null)
            if (config.predio.nivel == 1) 
                sobra = 50000.0;
        
        if (jogador == 1){
            if (config.predio == null){
                return confInicial.negocio.predios.get(0).preco;
            }
            else if (config.predio.nivel != 2){
                if (config.dinheiroTotal-sobra > (confInicial.negocio.predios.get(config.predio.nivel+1).preco)){
                    return (confInicial.negocio.predios.get(config.predio.nivel+1).preco);
                } else {
                    return 0.0;
                }
            } else {
              return 0.0;
          }
        } else {
            if (config.predio2 == null){
                return confInicial.negocio.predios.get(0).preco;
            }
            if (config.predio2.nivel != 2){
                if (config.dinheiroTotal2-sobra > (confInicial.negocio.predios.get(config.predio2.nivel+1).preco)){
                    return (confInicial.negocio.predios.get(config.predio2.nivel+1).preco);
                } else {
                    return 0.0;
                }
            } else {
                return 0.0;
            }
        }
    }
    
    public Estrutura getEstruturaByPreco(double preco, ArrayList<Estrutura> predios){
        for (Estrutura p: predios)
            if (preco == p.preco) return p;
        return null;
    }
    
    private double getValorProducaoEstoque(ArrayList<Produto> estoque){
        double valorTotal = 0;
        for (Produto p: estoque){
            valorTotal += (p.precoProduzir * p.quantidadePorMes);
        }
        return valorTotal;
    }
    
    private double getValorVendaEstoque(ArrayList<Produto> estoque){
        double valorTotal = 0;
        for (Produto p: estoque){
            valorTotal += (p.precoVenda * p.quantidadePorMes);
        }
        return valorTotal;
    }
    
    private boolean temNoEstoque(String nome, ArrayList<Produto> estoque){
        for (Produto p: estoque)
            if (p.nome.equals(nome)) return true;
        return false;
    }
    
}
