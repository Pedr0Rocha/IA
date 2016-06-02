package trabalhoia;

import java.util.ArrayList;
import java.util.Scanner;
import ui.MenuWindow;

/**
 *
 * @author Pedro
 */
public class Jogo {
    
    private int tipoJogo;
    
    private Scanner scan;
    private ArrayList<Negocio> negocios;
    private ArrayList<Double> dinheiroInicial = new ArrayList<Double>();
    public Settings confInicial = new Settings();
    
    public Jogo(int tipoJogo){
        this.tipoJogo = tipoJogo;
        negocios = MenuWindow.getNegocios();
        scan = new Scanner(System.in);
        configuraDinheiroInicial();
        
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
    
    public void configuraDinheiroInicial(){
        dinheiroInicial.add(50000.00);
        dinheiroInicial.add(100000.00);
        dinheiroInicial.add(500000.00);
    }
    
    public void pedeConfiguracao(){
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
        while(op <= 11){
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
        
        
        /*
            public double investimentoInicial; - OK
            public Estrutura predio; - OK
            public Produto produto; - OK
            public double gastosFixos; - OK
            public double precoProduto;
            public double gastoPropaganda;
        */
        System.out.println("");
        System.out.println("== Player 1 ==");

        op = -1;
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
        
        System.out.println("Dinheiro atual: " + (confInicial.investimentoInicial - (confInicial.predio.preco + confInicial.predio.gastoFixo)));
        
        op = -1;
        while (op <= 0 || op > confInicial.negocio.produtos.size()){
            System.out.println("Escolha qual produto irá produzir:");
            //TODO
        }
        
    }
    
    private void pedeConfiguracao2(){
        
    }
    
    
}