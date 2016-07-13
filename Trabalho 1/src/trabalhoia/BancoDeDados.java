package trabalhoia;

import java.util.ArrayList;

/**
 *
 * @author pedronote
 */
public class BancoDeDados {
    
    ArrayList<Negocio> negocios;
    
    public BancoDeDados(){
        negocios = new ArrayList<>();
        carregaBanco();
    }
    
    public void carregaBanco(){
        Negocio tecnologia = new Negocio("Tecnologia", criaEstruturasTecnologia(), criaProdutosTecnologia());
        negocios.add(tecnologia);
    }
    
    private ArrayList<Estrutura> criaEstruturasTecnologia(){
        ArrayList<Estrutura> predios = new ArrayList<Estrutura>();
        
        int[] producaoMensalNivel0 = new int[3];
        producaoMensalNivel0[0] = 10;
        producaoMensalNivel0[1] = 0;
        producaoMensalNivel0[2] = 0;
        predios.add(new Estrutura("Garagem", 30000.00, producaoMensalNivel0, 0, 80.00));
                
        int[] producaoMensalNivel1 = new int[3];
        producaoMensalNivel1[0] = 20;
        producaoMensalNivel1[1] = 7;
        producaoMensalNivel1[2] = 0;
        predios.add(new Estrutura("Sala Comercial", 80000.00, producaoMensalNivel1, 1, 200.00));
        
        int[] producaoMensalNivel2 = new int[3];
        producaoMensalNivel2[0] = 35;
        producaoMensalNivel2[1] = 14;
        producaoMensalNivel2[2] = 5;
        predios.add(new Estrutura("Prédio", 350000.00, producaoMensalNivel2, 2, 400.00));
        
        return predios;
    }
    
    private ArrayList<Produto> criaProdutosTecnologia(){
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        
        produtos.add(new Produto("Website", 0, 2000.00, 00.00));
        produtos.add(new Produto("Aplicativo", 1, 4000.00, 00.00));
        produtos.add(new Produto("Sistema", 2, 10000.00, 00.00));
                
        return produtos;
    }
    
    public ArrayList<Negocio> getNegocios(){
        return this.negocios;
    }
    
    public Negocio getNegocioByNome(String nomeRamo){
        for (Negocio n : negocios)
            if (n.ramo.equals(nomeRamo)) return n;
        return null;
    }
    
}
