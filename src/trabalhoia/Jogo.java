package trabalhoia;

import java.util.ArrayList;
import java.util.Scanner;
import ui.MenuWindow;

/**
 *
 * @author Pedro
 */
public class Jogo {
    
    private final int MAX_MESES = 12;
    private final int NUMERO_CONSUMIDORES = 150;
    
    private int tipoJogo;
    
    private Scanner scan;
    private ArrayList<Negocio> negocios;
    private ArrayList<Double> dinheiroInicial = new ArrayList<Double>();
    public Settings confInicial = new Settings();
    
    private ArrayList<Settings> gameLog = new ArrayList<Settings>();
    
    static double[] gastoPropaganda = new double[3];
    static double[] gastoPesquisa = new double[3];
    double[] precoProd = new double[3];
    
    Sistema sistema;
    
    public Jogo(int tipoJogo){
        this.tipoJogo = tipoJogo;
        negocios = MenuWindow.getNegocios();
        scan = new Scanner(System.in);
        sistema = new Sistema(NUMERO_CONSUMIDORES);
        configuraDinheiroInicial();
        configuraGastoPropaganda();
        configuraGastoPesquisa();
        configuraPrecoProdutos();
        
        if (MenuWindow.debugMode)
            System.out.println("Jogo criado - NoInterface , modo: " + tipoJogo );
        
        switch(tipoJogo){
            case 1:
                pedeConfiguracao();
                break;
            case 2:
                pedeConfiguracao2();
                break;
            default:
                System.out.println("Erro, recomece o jogo.");
                System.exit(1);
        }
    }
    
    private void configuraDinheiroInicial(){
        dinheiroInicial.add(50000.00);
        dinheiroInicial.add(100000.00);
        dinheiroInicial.add(500000.00);
    }
    
    private void configuraPrecoProdutos(){
        precoProd[0] = 1.25;
        precoProd[1] = 1.50;
        precoProd[2] = 2.0;
    }
    
    private void configuraGastoPropaganda(){
        gastoPropaganda[0] = 0.05;
        gastoPropaganda[1] = 0.15;
        gastoPropaganda[2] = 0.25;
    }
    
    private void configuraGastoPesquisa(){
        gastoPesquisa[0] = 0.05;
        gastoPesquisa[1] = 0.15;
        gastoPesquisa[2] = 0.25;
    }
    
    private void pedeConfiguracao(){
        confInicial.tipoGame = this.tipoJogo;
        confInicial.rodada = 0;
        
        int op = -1;
        while (op <= 0 || op > negocios.size()){
            System.out.println("");
            System.out.println("Escolha o ramo do negócio");
            for (int i = 0; i < negocios.size(); i++)
                System.out.println("[" + (i+1) + "] " + negocios.get(i).ramo);

            System.out.print("> ");
            op = scan.nextInt();
        }
        confInicial.negocio = negocios.get(op-1);
        if (MenuWindow.debugMode)
            System.out.println("Ramo do Negócio escolhido: " + confInicial.negocio.ramo);
        
        op = -1;
        while(op < MAX_MESES){
            System.out.println("");
            System.out.println("Escolha a duração do jogo em meses (min:12)");
            System.out.print("> ");
            op = scan.nextInt();
        }
        confInicial.meses = op;
        if (MenuWindow.debugMode)
            System.out.println("Duração do jogo: " + confInicial.meses + " meses");
        
        op = -1;
        while (op <= 0 || op > dinheiroInicial.size()){
            System.out.println("");
            System.out.println("Escolha o investimento inicial das empresas");
            for (int i = 0; i < dinheiroInicial.size(); i++)
                System.out.println("[" + (i+1) + "] " + dinheiroInicial.get(i));
            
            System.out.print("> ");
            op = scan.nextInt();
        }
        confInicial.investimentoInicial = dinheiroInicial.get(op-1);
        if (MenuWindow.debugMode)
            System.out.println("Investimento Inicial dos jogadores: " + confInicial.investimentoInicial);    
        
        confInicialPlayer1(confInicial);
        confInicialPlayer2(confInicial);
        
        confInicial.dinheiroTotal = calculaDinheiroTotal(true, 1, confInicial);
        confInicial.dinheiroTotal2 = calculaDinheiroTotal(true, 2, confInicial);
        
        mostraRodada(confInicial);
        gameLog.add(confInicial);
        System.out.println("");
        System.out.println("Digite qualquer tecla e aperte enter para continuar");
        scan.next();   
        comecaJogo();
    }
    
    private void comecaJogo(){
        int mesesPassados = 1;
        sistema.turnoSistema(gameLog.get(mesesPassados-1));
        while (mesesPassados != gameLog.get(0).meses){
            Settings rodada = new Settings();
            rodada.rodada = mesesPassados;
            gameLog.add(rodada);
            
            System.out.println("RODADA " + (mesesPassados+1));
            turnoJogador1(rodada);
            turnoJogador2(rodada);
            //sistema.turnoSistema(rodada);
            mesesPassados++;
        }
    }
    
    private void turnoJogador1(Settings rodada){
        /*
            0 - atualiza valores (predio, dinheiro atual, estoque)
            1 - mostra dinheiro atual e lucro rodada passada
            2 - pergunta upgrade de predio
            3 - mostra estoque
            4 - pergunta mudança de valor do produto
            5 - pergunta produzir mais
            6 - pergunta gasto propaganda
            7 - pergunta gasto pesquisa
            8 - mostra resumo da jogada
        */
        atualizaRodada(gameLog.get(rodada.rodada-1), rodada);
        System.out.println("JOGADOR 1");
        System.out.println("Dinheiro Atual: " + gameLog.get(rodada.rodada-1).dinheiroTotal);
        System.out.println("Lucro da Rodada Passada: " + gameLog.get(rodada.rodada-1).lucro);
        
        int op = -1;
        if (podeUpgradePredio(1, rodada)){
            System.out.println("");
            System.out.println("Dinheiro suficiente para upgrade de estrutura");
            while (op <= 0 || op > 2){
                System.out.println("Deseja fazer upgrade?");
                System.out.println("[1] - SIM");
                System.out.println("[2] - NAO");
                System.out.print("> ");
                op = scan.nextInt();

                if (op == 1){
                    upgradePredio(1, rodada);
                }
            }
        } else {
            System.out.println("");
            if (rodada.predio.nivel == 2)
                System.out.println("Sua estrutura esta no nivel maximo");
            else
                System.out.println("Dinheiro insuficiente para upgrade de estrutura");
        }
        
        imprimeEstoque(rodada.estoque);
        op = -1;
        while (op <= 0 || op > 2){
            System.out.println("");
            System.out.println("Deseja produzir mais produtos?");
                System.out.println("[1] - SIM");
                System.out.println("[2] - NAO");
                System.out.print("> ");
                op = scan.nextInt();

                if (op == 1){
                    escolheProdutos(1, rodada);
                }
            
        }
        System.out.println("");
    }
    
    private void turnoJogador2(Settings rodada){
        System.out.println("JOGADOR 2");
        System.out.println("Dinheiro Atual: " + gameLog.get(rodada.rodada-1).dinheiroTotal2);
        System.out.println("Lucro da Rodada Passada: " + gameLog.get(rodada.rodada-1).lucro2);
    }
    
    private void pedeConfiguracao2(){
        
    }
    
    private void confInicialPlayer1(Settings confInicial){
        System.out.println("");
        System.out.println("== Player 1 ==");

        int op = -1;
        while (op <= 0 || op > confInicial.negocio.predios.size()){
            System.out.println("");
            System.out.println("Escolha a estrutura inicial da empresa");
            for (int i = 0; i < confInicial.negocio.predios.size(); i++){
                if (confInicial.negocio.predios.get(i).preco <= confInicial.investimentoInicial){
                    System.out.println("[" + (i+1) + "] " + confInicial.negocio.predios.get(i).nome + " - Nível: " + confInicial.negocio.predios.get(i).nivel);
                    System.out.println("    Preço: " + confInicial.negocio.predios.get(i).preco);
                    System.out.println("    Gasto Fixo: " + confInicial.negocio.predios.get(i).gastoFixo);
                    System.out.println("    Producao:");
                    for (int j = 0; j < confInicial.negocio.produtos.size(); j++){
                        if (confInicial.negocio.produtos.get(j).nivelEstrutura <= i)
                            System.out.println("        " + confInicial.negocio.produtos.get(j).nome + "   |   qta.: " + confInicial.negocio.predios.get(i).producao[j] + "/mês");
                    }
                    System.out.println("");
                }
            }

            System.out.print("> ");
            op = scan.nextInt();
        }
        confInicial.predio = confInicial.negocio.predios.get(op-1);
        if (MenuWindow.debugMode)
            System.out.println("Estrutura Inicial: " + confInicial.predio.nome);
        
        System.out.println("");
        if (MenuWindow.debugMode)
            System.out.println("Equacao do dinheiro total (Investimento Inicial - (Preco Estrutura + Gasto Fixo da Estrutura)): " 
                    + confInicial.investimentoInicial + " - (" + confInicial.predio.preco + " + " + confInicial.predio.gastoFixo + ")");
        
        System.out.println("Dinheiro atual: " + calculaDinheiroTotal(true, 1, confInicial));
        
        escolheProdutos(1, confInicial);
        op = -1;
        while (op <= 0){
            System.out.println("");
            System.out.println("Deseja produzir outro produto?");
            System.out.println("[1] - SIM");
            System.out.println("[2] - NAO");
            System.out.print("> ");
            op = scan.nextInt();
            
            if (op == 1){
                escolheProdutos(1, confInicial);
                op = -1;
            }
        }
        
        imprimeEstoque(confInicial.estoque);
        System.out.println("");
        System.out.println("Valor Estoque: " + getValorProducaoEstoque(confInicial.estoque));
        System.out.println("");
        System.out.println("Dinheiro atual: " + calculaDinheiroTotal(true, 1, confInicial));
        
        escolheGastoPropaganda(1, confInicial);
        escolheGastoPesquisa(1, confInicial);
        
        System.out.println("");
        System.out.println("");
        System.out.println("CONFIGURACAO INICIAL DO JOGADOR 1");
        imprimeConfiguracaoJogador(1, confInicial);
        
        System.out.println("Digite qualquer tecla e aperte enter para continuar");
        scan.next();        
    }
    
    private void confInicialPlayer2(Settings confInicial){
        System.out.println("");
        System.out.println("== Player 2 ==");

        int op = -1;
        while (op <= 0 || op > confInicial.negocio.predios.size()){
            System.out.println("");
            System.out.println("Escolha a estrutura inicial da empresa");
            for (int i = 0; i < confInicial.negocio.predios.size(); i++){
                if (confInicial.negocio.predios.get(i).preco <= confInicial.investimentoInicial){
                    System.out.println("[" + (i+1) + "] " + confInicial.negocio.predios.get(i).nome + " - Nível: " + confInicial.negocio.predios.get(i).nivel);
                    System.out.println("    Preço: " + confInicial.negocio.predios.get(i).preco);
                    System.out.println("    Gasto Fixo: " + confInicial.negocio.predios.get(i).gastoFixo);
                    System.out.println("    Producao:");
                    for (int j = 0; j < confInicial.negocio.produtos.size(); j++){
                        if (confInicial.negocio.produtos.get(j).nivelEstrutura <= i)
                            System.out.println("        " + confInicial.negocio.produtos.get(j).nome + "   |   qta.: " + confInicial.negocio.predios.get(i).producao[j] + "/mês");
                    }
                    System.out.println("");
                }
            }

            System.out.print("> ");
            op = scan.nextInt();
        }
        confInicial.predio2 = confInicial.negocio.predios.get(op-1);
        if (MenuWindow.debugMode)
            System.out.println("Estrutura Inicial: " + confInicial.predio2.nome);
        
        System.out.println("");
        if (MenuWindow.debugMode)
            System.out.println("Equacao do dinheiro total (Investimento Inicial - (Preco Estrutura + Gasto Fixo da Estrutura)): " 
                    + confInicial.investimentoInicial + " - (" + confInicial.predio2.preco + " + " + confInicial.predio2.gastoFixo + ")");
        
        System.out.println("Dinheiro atual: " + calculaDinheiroTotal(true, 2, confInicial));
        
        escolheProdutos(2, confInicial);
        op = -1;
        while (op <= 0){
            System.out.println("");
            System.out.println("Deseja produzir outro produto?");
            System.out.println("[1] - SIM");
            System.out.println("[2] - NAO");
            System.out.print("> ");
            op = scan.nextInt();
            
            if (op == 1){
                escolheProdutos(2, confInicial);
                op = -1;
            }
        }
        
        imprimeEstoque(confInicial.estoque2);
        System.out.println("");
        System.out.println("Valor Estoque: " + getValorProducaoEstoque(confInicial.estoque2));
        System.out.println("");
        System.out.println("Dinheiro atual: " + calculaDinheiroTotal(true, 2, confInicial));
        
        escolheGastoPropaganda(2, confInicial);
        escolheGastoPesquisa(2, confInicial);
        
        System.out.println("");
        System.out.println("");
        System.out.println("CONFIGURACAO INICIAL DO JOGADOR 2");
        imprimeConfiguracaoJogador(2, confInicial);
        
        System.out.println("Digite qualquer tecla e aperte enter para continuar");
        scan.next();        
    }
    
    private void imprimeConfiguracaoJogador(int jogador, Settings conf){
        if (jogador == 1){
            System.out.println("ESTRUTURA: " + conf.predio.nome);
            imprimeEstoque(conf.estoque);
            System.out.println("VALOR DE PRODUCAO DO ESTOQUE: " + getValorProducaoEstoque(conf.estoque));
            System.out.println("VALOR DE VENDA DO ESTOQUE: " + getValorVendaEstoque(conf.estoque));
            System.out.println("GASTO COM PROPAGANDA: " + (conf.gastoPropaganda * getValorProducaoEstoque(conf.estoque)));
            System.out.println("GASTO COM PESQUISA: " + (conf.gastoPesquisa * getValorProducaoEstoque(conf.estoque)));
            System.out.println("DINHEIRO ATUAL: " + conf.dinheiroTotal);
        } else {
            System.out.println("ESTRUTURA: " + conf.predio2.nome);
            imprimeEstoque(conf.estoque2);
            System.out.println("VALOR DO ESTOQUE: " + getValorProducaoEstoque(conf.estoque2));
            System.out.println("VALOR DE VENDA DO ESTOQUE: " + getValorVendaEstoque(conf.estoque2));
            System.out.println("GASTO COM PROPAGANDA: " + (conf.gastoPropaganda2 * getValorProducaoEstoque(conf.estoque2)));
            System.out.println("GASTO COM PESQUISA: " + (conf.gastoPesquisa2 * getValorProducaoEstoque(conf.estoque2)));
            System.out.println("DINHEIRO ATUAL: " + conf.dinheiroTotal2);
        }
    }
    
    private void escolheGastoPesquisa(int player, Settings confInicial){
        int op = -1;        
        if (player == 1){
            double valorEstoque = getValorProducaoEstoque(confInicial.estoque);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em pesquisa de mercado?");
                System.out.println("[1] BAIXO");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Preco: 25% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[2] * valorEstoque));
                op = scan.nextInt();
            }
            confInicial.gastoPesquisa = gastoPesquisa[op-1];
            if (MenuWindow.debugMode)
                System.out.println("Gasto com Pesquisa: " + (confInicial.gastoPesquisa * valorEstoque));
        } else {
            double valorEstoque = getValorProducaoEstoque(confInicial.estoque2);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em pesquisa de mercado?");
                System.out.println("[1] BAIXO");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Preco: 25% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[2] * valorEstoque));
                op = scan.nextInt();
            }
            confInicial.gastoPesquisa2 = gastoPesquisa[op-1];
            if (MenuWindow.debugMode)
                System.out.println("Gasto com Pesquisa: " + (confInicial.gastoPesquisa2 * valorEstoque));            
        }
    }
    
    private void escolheGastoPropaganda(int player, Settings confInicial){
        int op = -1;        
        if (player == 1){
            double valorEstoque = getValorProducaoEstoque(confInicial.estoque);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em propaganda?");
                System.out.println("[1] BAIXO");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Preco: 25% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[2] * valorEstoque));
                op = scan.nextInt();
            }
            confInicial.gastoPropaganda = gastoPropaganda[op-1];
            if (MenuWindow.debugMode)
                System.out.println("Gasto com Propaganda: " + (confInicial.gastoPropaganda * valorEstoque));
        } else {
            double valorEstoque = getValorProducaoEstoque(confInicial.estoque2);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em propaganda?");
                System.out.println("[1] BAIXO");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Preco: 25% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[2] * valorEstoque));
                op = scan.nextInt();
            }
            confInicial.gastoPropaganda2 = gastoPropaganda[op-1];
            if (MenuWindow.debugMode)
                System.out.println("Gasto com Propaganda: " + (confInicial.gastoPropaganda2 * valorEstoque));            
        }
    }
    
    private void escolheProdutos(int jogador, Settings rodada){
        int op = -1;
        if (jogador == 1){
            while (op <= 0 || op > rodada.negocio.produtos.size()){
                System.out.println("");
                System.out.println("Escolha qual produto ira produzir:");
                for (int i = 0; i < rodada.negocio.produtos.size(); i ++){
                    System.out.println("[" + (i+1) + "] " + rodada.negocio.produtos.get(i).nome + " - Custo: " + rodada.negocio.produtos.get(i).precoProduzir);
                }
                System.out.print("> ");
                op = scan.nextInt();
                if (rodada.negocio.produtos.get(op-1).nivelEstrutura > rodada.predio.nivel){
                    System.out.println("");
                    System.out.println("Este produto nao pode ser produzido pela estrutura atual.");
                    op = -1;                
                } else {
                    if (temNoEstoque(rodada.negocio.produtos.get(op-1).nome, rodada.estoque)){
                        System.out.println("Voce ja produzira esse produto.");
                    } else {
                        int op2 = -1;
                        while (op2 <= 0 || op2 > rodada.predio.producao[op-1]){
                            System.out.println("Quantas unidades deseja produzir deste produto? Maximo por mes = " + rodada.predio.producao[op-1]);
                            System.out.print("> ");
                            op2 = scan.nextInt();
                        }
                        Produto prod = new Produto(rodada.negocio.produtos.get(op-1));
                        prod.quantidadePorMes = op2;

                        op2 = -1;                        
                        while (op2 <= 0 || op2 >= 4){
                            System.out.println("Qual o preco de venda deste produto? Preco de producao: " + prod.precoProduzir);
                            System.out.println("[1] - " + (prod.precoProduzir * precoProd[0]));
                            System.out.println("[2] - " + (prod.precoProduzir * precoProd[1]));
                            System.out.println("[3] - " + (prod.precoProduzir * precoProd[2]));

                            op2 = scan.nextInt();
                        }
                        prod.precoVenda = (prod.precoProduzir * precoProd[op2-1]);
                        rodada.estoque.add(prod);
                        if (MenuWindow.debugMode)
                            System.out.println("Produto escolhido: " + rodada.estoque.get(rodada.estoque.size() - 1).nome);
                    }
                }
            }
        } else {
            while (op <= 0 || op > rodada.negocio.produtos.size()){
                System.out.println("");
                System.out.println("Escolha qual produto ira produzir:");
                for (int i = 0; i < rodada.negocio.produtos.size(); i ++){
                    System.out.println("[" + (i+1) + "] " + rodada.negocio.produtos.get(i).nome + " - Custo: " + rodada.negocio.produtos.get(i).precoProduzir);
                }
                System.out.print("> ");
                op = scan.nextInt();
                if (rodada.negocio.produtos.get(op-1).nivelEstrutura > rodada.predio2.nivel){
                    System.out.println("");
                    System.out.println("Este produto nao pode ser produzido pela estrutura atual.");
                    op = -1;                
                } else {
                    if (temNoEstoque(rodada.negocio.produtos.get(op-1).nome, rodada.estoque2)){
                        System.out.println("Voce ja produzira esse produto.");
                    } else {
                        int op2 = -1;
                        while (op2 <= 0 || op2 > rodada.predio2.producao[op-1]){
                            System.out.println("Quantas unidades deseja produzir deste produto? Maximo por mes = " + rodada.predio2.producao[op-1]);
                            System.out.print("> ");
                            op2 = scan.nextInt();
                        }
                        Produto prod = new Produto(rodada.negocio.produtos.get(op-1));
                        System.out.println("AQUI" + prod.quantidadePorMes);
                        prod.quantidadePorMes = op2;

                        op2 = -1;                        
                        while (op2 <= 0 || op2 >= 4){
                            System.out.println("Qual o preco de venda deste produto? Preco de producao: " + prod.precoProduzir);
                            System.out.println("[1] - " + (prod.precoProduzir * precoProd[0]));
                            System.out.println("[2] - " + (prod.precoProduzir * precoProd[1]));
                            System.out.println("[3] - " + (prod.precoProduzir * precoProd[2]));

                            op2 = scan.nextInt();
                        }
                        prod.precoVenda = (prod.precoProduzir * precoProd[op2-1]);
                        rodada.estoque2.add(prod);
                        if (MenuWindow.debugMode)
                            System.out.println("Produto escolhido: " + rodada.estoque2.get(rodada.estoque2.size() - 1).nome);
                    }
                }
            }
        }
    }
    
    private boolean podeUpgradePredio(int jogador, Settings rodada){
        if (jogador == 1){
            if (rodada.predio.nivel == 0){
                if (rodada.dinheiroTotal > confInicial.negocio.predios.get(1).preco){
                    return true;
                }
            } else if (rodada.predio.nivel == 1){
                if (rodada.dinheiroTotal > confInicial.negocio.predios.get(2).preco){
                    return true;
                }
            } else {
                return false;
            }
        } else {
            if (rodada.predio2.nivel == 0){
                if (rodada.dinheiroTotal2 > confInicial.negocio.predios.get(1).preco){
                    return true;
                }
            } else if (rodada.predio2.nivel == 1){
                if (rodada.dinheiroTotal2 > confInicial.negocio.predios.get(2).preco){
                    return true;
                }
            } else {
                return false;
            }            
        }
        return false;
    }
    
    private void upgradePredio(int jogador, Settings rodada){
        if (jogador == 1){
            int op = -1;
            ArrayList<Estrutura> upgrades = new ArrayList<Estrutura>();
            for (int i = 0; i < confInicial.negocio.predios.size(); i++){
                if (confInicial.negocio.predios.get(i).preco <= rodada.dinheiroTotal && !confInicial.negocio.predios.get(i).nome.equals(rodada.predio.nome)
                && rodada.predio.nivel < confInicial.negocio.predios.get(i).nivel){
                    upgrades.add(confInicial.negocio.predios.get(i));
                    if (MenuWindow.debugMode)
                        System.out.println("Adicionado na lista de upgrades: " + confInicial.negocio.predios.get(i).nome);
                }
            }
            while (op <= 0 || op > upgrades.size()){
                System.out.println("");
                System.out.println("Qual a nova estrutura desejada?");
                for (int i = 0; i < upgrades.size(); i++){                       
                        System.out.println("[" + (i+1) + "] " + upgrades.get(i).nome + " - Nível: " + upgrades.get(i).nivel);
                        System.out.println("    Preço: " + upgrades.get(i).preco);
                        System.out.println("    Gasto Fixo: " + upgrades.get(i).gastoFixo);
                        System.out.println("    Producao:");
                        for (int j = 0; j < confInicial.negocio.produtos.size(); j++){
                            if (confInicial.negocio.produtos.get(j).nivelEstrutura <= upgrades.get(i).nivel)
                                System.out.println("        " + confInicial.negocio.produtos.get(j).nome + "   |   qta.: " + upgrades.get(i).producao[j] + "/mês");
                        }
                        System.out.println("");
                }

                System.out.print("> ");
                op = scan.nextInt();
            }
            rodada.predio = upgrades.get(op-1);
            if (MenuWindow.debugMode)
                System.out.println("Estrutura Nova: " + rodada.predio.nome);
        }
    }
    
    private void atualizaRodada(Settings rodadaPassada, Settings rodadaAtual){
        rodadaAtual.dinheiroTotal = rodadaPassada.dinheiroTotal;
        clonaEstoque(rodadaPassada.estoque, rodadaAtual.estoque);
        rodadaAtual.predio = rodadaPassada.predio;
    }
    
    private ArrayList<Produto> clonaEstoque(ArrayList<Produto> antigo, ArrayList<Produto> atual){
        for (Produto e: antigo)
            atual.add(e);
        return atual;
    }
    
    private double calculaDinheiroTotal(boolean inicial, int player, Settings conf){
        double gastoTotal = 0;
        if (inicial){
            if (player == 1)
                gastoTotal = (conf.investimentoInicial - (conf.predio.preco + conf.predio.gastoFixo) - 
                    (getValorProducaoEstoque(conf.estoque) * conf.gastoPropaganda) - 
                    (getValorProducaoEstoque(conf.estoque) * conf.gastoPesquisa));
            else
                gastoTotal = (conf.investimentoInicial - (conf.predio2.preco + conf.predio2.gastoFixo) - 
                    (getValorProducaoEstoque(conf.estoque2) * conf.gastoPropaganda2) -
                    (getValorProducaoEstoque(conf.estoque2) * conf.gastoPesquisa));
        } else {
            if (player == 1)
                gastoTotal = (conf.predio.gastoFixo) - (getValorProducaoEstoque(conf.estoque) * conf.gastoPropaganda) -
                        (getValorProducaoEstoque(conf.estoque) * conf.gastoPesquisa);
            else
                gastoTotal = (conf.predio2.gastoFixo) - (getValorProducaoEstoque(conf.estoque2) * conf.gastoPropaganda2) -
                        (getValorProducaoEstoque(conf.estoque2) * conf.gastoPesquisa);
        }
        return gastoTotal;
    }
    
    private void imprimeEstoque(ArrayList<Produto> estoque){
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
            System.out.println("    Quantidade: " + p.quantidadePorMes);
            System.out.println("    Gasto: " + (p.precoProduzir * p.quantidadePorMes));
        }
    }
    
    private boolean temNoEstoque(String nome, ArrayList<Produto> estoque){
        for (Produto p: estoque)
            if (p.nome.equals(nome)) return true;
        return false;
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
    
    private void mostraRodada(Settings rodada){
        clearConsole();
        System.out.println("RESUMO DA RODADA");
        System.out.println("");
        System.out.println("PLAYER 1");
        imprimeConfiguracaoJogador(1, rodada);
        System.out.println("");
        System.out.println("PLAYER 2");
        imprimeConfiguracaoJogador(2, rodada);
    }
    
    public static double[] getGastoPropaganda(){
        return gastoPropaganda;
    }
    
    private void clearConsole(){
        for (int i = 0; i < 50; i++)
            System.out.println("");
    }
}