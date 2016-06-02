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
    
    public Settings pedeConfiguracao(){
        Settings sett = new Settings();
        sett.tipoGame = this.tipoJogo;
        sett.rodada = 0;
        
        int op = -1;
        while (op <= 0 || op > negocios.size()){
            System.out.println("");
            System.out.println("Escolha o ramo do negócio");
            for (int i = 0; i < negocios.size(); i++)
                System.out.println("[" + (i+1) + "] " + negocios.get(i).ramo);

            System.out.print("> ");
            op = scan.nextInt();
        }
        sett.negocio = negocios.get(op-1);
        if (MenuWindow.debugMode)
            System.out.println("Ramo do Negócio escolhido: " + sett.negocio.ramo);
        
        op = -1;
        while(op <= 11){
            System.out.println("");
            System.out.println("Escolha a duração do jogo em meses (min:12)");
            System.out.print("> ");
            op = scan.nextInt();
        }
        sett.meses = op;
        if (MenuWindow.debugMode)
            System.out.println("Duração do jogo: " + sett.meses + " meses");
        
        op = -1;
        while (op <= 0 || op > dinheiroInicial.size()){
            System.out.println("");
            System.out.println("Escolha o investimento inicial das empresas");
            for (int i = 0; i < dinheiroInicial.size(); i++)
                System.out.println("[" + (i+1) + "] " + dinheiroInicial.get(i));
            
            System.out.print("> ");
            op = scan.nextInt();
        }
        sett.investimentoInicial = dinheiroInicial.get(op-1);
        if (MenuWindow.debugMode)
            System.out.println("Investimento Inicial dos jogadores: " + sett.investimentoInicial);
        
        
        /*
            public double investimentoInicial;
            public Estrutura predio;
            public Produto produto;
            public double precoProduto;
            public int tempoFabricacao;
            public double gastoPropaganda;
            public double gastosFixos;
        */
        System.out.println("");
        System.out.println("== Player 1 ==");

        op = -1;
        while (op <= 0 || op > sett.negocio.predios.size()){
            System.out.println("");
            System.out.println("Escolha o prédio inicial da empresa");
            for (int i = 0; i < sett.negocio.predios.size(); i++){
                System.out.println("[" + (i+1) + "] " + sett.negocio.predios.get(i).nome + " - Nível: " + sett.negocio.predios.get(i).nivel);
                System.out.println("    Preço: " + sett.negocio.predios.get(i).preco);
                System.out.println("    Gasto Fixo: " + sett.negocio.predios.get(i).gastoFixo);
                System.out.println("    Producao:");
                for (Produto p: sett.negocio.produtos)
                    System.out.println("        " + p.nome);
            }

            System.out.print("> ");
            op = scan.nextInt();
        }
        
        return sett;
    }
    
    private void pedeConfiguracao2(){
        
    }
    
    
}