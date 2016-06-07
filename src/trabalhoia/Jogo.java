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
            System.out.println("Jogo criado - NI , modo: " + tipoJogo );
        
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
        if (confInicial.dinheiroTotal < 0) confInicial.mesesEmPrejuizo++;
        if (confInicial.dinheiroTotal2 < 0) confInicial.mesesEmPrejuizo2++;
        
        mostraRodada(confInicial);
        gameLog.add(confInicial);
        comecaJogo();
    }
    
    private void comecaJogo(){
        int mesesPassados = 1;
        sistema.turnoSistema(gameLog.get(mesesPassados-1));
        while (mesesPassados != gameLog.get(0).meses){
            System.out.println("Rodada " + mesesPassados);
            mesesPassados++;
        }
    }
    
    private void pedeConfiguracao2(){
        
    }
    
    private void confInicialPlayer1(Settings confInicial){
        System.out.println("");
        System.out.println("== Player 1 ==");

        int op = -1;
        while (op <= 0 || op > confInicial.negocio.predios.size()){
            System.out.println("");
            System.out.println("Escolha o prédio inicial da empresa");
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
            System.out.println("Prédio Inicial: " + confInicial.predio.nome);
        
        System.out.println("");
        if (MenuWindow.debugMode)
            System.out.println("Equacao do dinheiro total (Investimento Inicial - (Preco Predio + Gasto Fixo do Predio)): " 
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
        System.out.println("Valor Estoque: " + getValorEstoque(confInicial.estoque));
        System.out.println("");
        System.out.println("Dinheiro atual: " + calculaDinheiroTotal(true, 1, confInicial));
        
        escolheGastoPropaganda(1, confInicial);
        escolheGastoPesquisa(1, confInicial);
        
        System.out.println("");
        System.out.println("");
        System.out.println("CONFIGURACAO INICIAL DO JOGADOR 1");
        imprimeConfiguracaoJogador(1, confInicial);
        
        System.out.println("Digite qualquer tecla para continuar");
        scan.next();        
    }
    
    private void confInicialPlayer2(Settings confInicial){
        System.out.println("");
        System.out.println("== Player 2 ==");

        int op = -1;
        while (op <= 0 || op > confInicial.negocio.predios.size()){
            System.out.println("");
            System.out.println("Escolha o prédio inicial da empresa");
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
            System.out.println("Prédio Inicial: " + confInicial.predio2.nome);
        
        System.out.println("");
        if (MenuWindow.debugMode)
            System.out.println("Equacao do dinheiro total (Investimento Inicial - (Preco Predio + Gasto Fixo do Predio)): " 
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
        System.out.println("Valor Estoque: " + getValorEstoque(confInicial.estoque2));
        System.out.println("");
        System.out.println("Dinheiro atual: " + calculaDinheiroTotal(true, 2, confInicial));
        
        escolheGastoPropaganda(2, confInicial);
        escolheGastoPesquisa(2, confInicial);
        
        System.out.println("");
        System.out.println("");
        System.out.println("CONFIGURACAO INICIAL DO JOGADOR 2");
        imprimeConfiguracaoJogador(2, confInicial);
        
        System.out.println("Digite qualquer tecla para continuar");
        scan.next();        
    }
    
    private void imprimeConfiguracaoJogador(int jogador, Settings conf){
        if (jogador == 1){
            System.out.println("PREDIO: " + conf.predio.nome);
            imprimeEstoque(conf.estoque);
            System.out.println("VALOR DO ESTOQUE: " + getValorEstoque(conf.estoque));
            System.out.println("GASTO COM PROPAGANDA: " + (conf.gastoPropaganda * getValorEstoque(conf.estoque)));
            System.out.println("GASTO COM PESQUISA: " + (conf.gastoPesquisa * getValorEstoque(conf.estoque)));
            System.out.println("DINHEIRO ATUAL: " + conf.dinheiroTotal);
        } else {
            System.out.println("PREDIO: " + conf.predio2.nome);
            imprimeEstoque(conf.estoque2);
            System.out.println("VALOR DO ESTOQUE: " + getValorEstoque(conf.estoque2));
            System.out.println("GASTO COM PROPAGANDA: " + (conf.gastoPropaganda2 * getValorEstoque(conf.estoque2)));
            System.out.println("GASTO COM PESQUISA: " + (conf.gastoPesquisa2 * getValorEstoque(conf.estoque2)));
            System.out.println("DINHEIRO ATUAL: " + conf.dinheiroTotal2);
        }
    }
    
    private void escolheGastoPesquisa(int player, Settings confInicial){
        int op = -1;        
        if (player == 1){
            double valorEstoque = getValorEstoque(confInicial.estoque);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em pesquisa de mercado?");
                System.out.println("[1] BAIXO");
                System.out.println("    Chance de vender com preco alto: 20%");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Chance de vender com preco alto: 55%");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Chance de vender com preco alto: 90%");
                System.out.println("    Preco: 25% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[2] * valorEstoque));
                op = scan.nextInt();
            }
            confInicial.gastoPesquisa = gastoPesquisa[op-1];
            if (MenuWindow.debugMode)
                System.out.println("Gasto com Pesquisa: " + (confInicial.gastoPesquisa * valorEstoque));
        } else {
            double valorEstoque = getValorEstoque(confInicial.estoque2);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em pesquisa de mercado?");
                System.out.println("[1] BAIXO");
                System.out.println("    Chance de vender com preco alto: 20%");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Chance de vender com preco alto: 55%");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPesquisa[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Chance de vender com preco alto: 90%");
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
            double valorEstoque = getValorEstoque(confInicial.estoque);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em propaganda?");
                System.out.println("[1] BAIXO");
                System.out.println("    Chance de vender produtos do Estoque: 20%");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Chance de vender produtos do Estoque: 55%");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Chance de vender produtos do Estoque: 90%");
                System.out.println("    Preco: 25% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[2] * valorEstoque));
                op = scan.nextInt();
            }
            confInicial.gastoPropaganda = gastoPropaganda[op-1];
            if (MenuWindow.debugMode)
                System.out.println("Gasto com Propaganda: " + (confInicial.gastoPropaganda * valorEstoque));
        } else {
            double valorEstoque = getValorEstoque(confInicial.estoque2);
            while (op <= -1 || op > 3){
                System.out.println("");
                System.out.println("Qual sera o investimento em propaganda?");
                System.out.println("[1] BAIXO");
                System.out.println("    Chance de vender produtos do Estoque: 20%");
                System.out.println("    Preco: 5% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[0] * valorEstoque));
                System.out.println("[2] MEDIO");
                System.out.println("    Chance de vender produtos do Estoque: 55%");
                System.out.println("    Preco: 15% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[1] * valorEstoque));
                System.out.println("[3] ALTO");
                System.out.println("    Chance de vender produtos do Estoque: 90%");
                System.out.println("    Preco: 25% do Valor Total do Estoque");
                System.out.println("    Preco Atual: " + (gastoPropaganda[2] * valorEstoque));
                op = scan.nextInt();
            }
            confInicial.gastoPropaganda2 = gastoPropaganda[op-1];
            if (MenuWindow.debugMode)
                System.out.println("Gasto com Propaganda: " + (confInicial.gastoPropaganda2 * valorEstoque));            
        }
    }
    
    private void escolheProdutos(int jogador, Settings confInicial){
        int op = -1;
        if (jogador == 1){
            while (op <= 0 || op > confInicial.negocio.produtos.size()){
                System.out.println("");
                System.out.println("Escolha qual produto ira produzir:");
                for (int i = 0; i < confInicial.negocio.produtos.size(); i ++){
                    System.out.println("[" + (i+1) + "] " + confInicial.negocio.produtos.get(i).nome + " - Custo: " + confInicial.negocio.produtos.get(i).precoProduzir);
                }
                System.out.print("> ");
                op = scan.nextInt();
                if (confInicial.negocio.produtos.get(op-1).nivelEstrutura > confInicial.predio.nivel){
                    System.out.println("");
                    System.out.println("Este produto nao pode ser produzido pela estrutura atual.");
                    op = -1;                
                } else {
                    if (confInicial.estoque.contains(confInicial.negocio.produtos.get(op-1))){
                        System.out.println("Voce ja produzira esse produto.");
                    } else {
                        int op2 = -1;
                        while (op2 <= 0 || op2 > confInicial.predio.producao[op-1]){
                            System.out.println("Quantas unidades deseja produzir deste produto? Maximo por mes = " + confInicial.predio.producao[op-1]);
                            System.out.print("> ");
                            op2 = scan.nextInt();
                        }
                        Produto prod = confInicial.negocio.produtos.get(op-1);
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
                        confInicial.estoque.add(confInicial.negocio.produtos.get(op-1));
                        if (MenuWindow.debugMode)
                            System.out.println("Produto escolhido: " + confInicial.estoque.get(confInicial.estoque.size() - 1).nome);
                    }
                }
            }
        } else {
            while (op <= 0 || op > confInicial.negocio.produtos.size()){
                System.out.println("");
                System.out.println("Escolha qual produto ira produzir:");
                for (int i = 0; i < confInicial.negocio.produtos.size(); i ++){
                    System.out.println("[" + (i+1) + "] " + confInicial.negocio.produtos.get(i).nome + " - Custo: " + confInicial.negocio.produtos.get(i).precoProduzir);
                }
                System.out.print("> ");
                op = scan.nextInt();
                if (confInicial.negocio.produtos.get(op-1).nivelEstrutura > confInicial.predio2.nivel){
                    System.out.println("");
                    System.out.println("Este produto nao pode ser produzido pela estrutura atual.");
                    op = -1;                
                } else {
                    if (confInicial.estoque2.contains(confInicial.negocio.produtos.get(op-1))){
                        System.out.println("Voce ja produzira esse produto.");
                    } else {
                        int op2 = -1;
                        while (op2 <= 0 || op2 > confInicial.predio2.producao[op-1]){
                            System.out.println("Quantas unidades deseja produzir deste produto? Maximo por mes = " + confInicial.predio2.producao[op-1]);
                            System.out.print("> ");
                            op2 = scan.nextInt();
                        }
                        Produto prod = confInicial.negocio.produtos.get(op-1);
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
                        confInicial.estoque2.add(confInicial.negocio.produtos.get(op-1));
                        if (MenuWindow.debugMode)
                            System.out.println("Produto escolhido: " + confInicial.estoque2.get(confInicial.estoque2.size() - 1).nome);
                    }
                }
            }
        }
    }
    
    private double calculaDinheiroTotal(boolean inicial, int player, Settings conf){
        double gastoTotal = 0;
        if (inicial){
            if (player == 1)
                gastoTotal = (conf.investimentoInicial - (conf.predio.preco + conf.predio.gastoFixo) - 
                    (getValorEstoque(conf.estoque) * conf.gastoPropaganda) - 
                    (getValorEstoque(conf.estoque) * conf.gastoPesquisa));
            else
                gastoTotal = (conf.investimentoInicial - (conf.predio2.preco + conf.predio2.gastoFixo) - 
                    (getValorEstoque(conf.estoque2) * conf.gastoPropaganda2) -
                    (getValorEstoque(conf.estoque2) * conf.gastoPesquisa));
        } else {
            if (player == 1)
                gastoTotal = (conf.predio.gastoFixo) - (getValorEstoque(conf.estoque) * conf.gastoPropaganda) -
                        (getValorEstoque(conf.estoque) * conf.gastoPesquisa);
            else
                gastoTotal = (conf.predio2.gastoFixo) - (getValorEstoque(conf.estoque2) * conf.gastoPropaganda2) -
                        (getValorEstoque(conf.estoque2) * conf.gastoPesquisa);
        }
        return gastoTotal;
    }
    
    private void imprimeEstoque(ArrayList<Produto> estoque){
        System.out.println("");
        System.out.println("ESTOQUE");
        for (Produto p : estoque){
            System.out.println("Produto: " + p.nome);
            System.out.println("    Preco Venda: " + p.precoProduzir);
            System.out.println("    Quantidade: " + p.quantidadePorMes);
            System.out.println("    Gasto: " + (p.precoProduzir * p.quantidadePorMes));
        }
    }
    
    private double getValorEstoque(ArrayList<Produto> estoque){
        double valorTotal = 0;
        for (Produto p: estoque){
            valorTotal += (p.precoProduzir * p.quantidadePorMes);
        }
        return valorTotal;
    }
    
    private void mostraRodada(Settings rodada){
        System.out.println("");
        System.out.println("");
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
}