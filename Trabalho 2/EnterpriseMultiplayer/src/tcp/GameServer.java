
package tcp;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Phaser;
import structures.GameDatabase;
import structures.Product;
import structures.Warehouse;
import utils.CONSTANTS;
import utils.DatabaseLoader;
import utils.PopulationManager;
import utils.ProtocolMessage;

public class GameServer 
{
    public static int MaxPlayers = 1;
    public final ArrayList<GameConnection> clients;
    public ServerSocket socket;
    public final Phaser phaser;
    private double initialMoney;
    private int businessType;
    private int maxMonths;
    GameDatabase db = GameDatabase.getInstance();
    DatabaseLoader gl = new DatabaseLoader();
    
    private PopulationManager populationManager;

    public GameServer(int port, double initialMoney, int businessType, int maxMonths)
    {
        this.clients = new ArrayList<GameConnection>();
        this.phaser = new Phaser(1);
        this.initialMoney = initialMoney;
        this.businessType = businessType;
        this.maxMonths = maxMonths;

        try
        {
            // Inicializar socket TCP
            this.socket = new ServerSocket(port);
            System.out.println("[GameServer] Iniciado na porta " + port + ".");

            // Aceitar novos players até que a lista esteja completa
            while(this.clients.size() < MaxPlayers)
            {
                // Aceitar a conexão e criar a thread
                Socket client = this.socket.accept();
                this.clients.add(new GameConnection(this, client));
            }

            // Espera todos os players se conectarem
            phaser.arriveAndAwaitAdvance();

            // Envia configurações iniciais aos players
            System.out.println("[GameServer] Jogadores conectados. Enviando configurações Iniciais...");
            
            // Espera todos os players receberem a confirmação de jogo
            phaser.arriveAndAwaitAdvance();

            // Iniciar
            System.out.println("[GameServer] Jogo iniciado!");
            
            for (int i = 0; i < this.maxMonths; i++) 
            {
                System.out.println("[GameServer] Iniciado mes " + i + ".");
                phaser.arriveAndAwaitAdvance();
                // Aqui vai a verificacao de jogadas
                int k = 0;
                int marketingInvestment = calcMarketingInvestments();
                populationManager = new PopulationManager(marketingInvestment);
                ArrayList<Warehouse> whs = new ArrayList<Warehouse>();
                
                for(GameConnection gc : this.clients) {
                    ++k;
                    ProtocolMessage msg = gc.message;
                    Warehouse wh = Warehouse.deserialize(msg.getSerializedWarehouse());
                    for (Product p : wh.getStock())
                        p.setCostBenefit(msg.getMarketingInvestment(), msg.getResearchInvestment());
                    whs.add(wh);
                }
                
                ArrayList<Product> prods = GameDatabase.getInstance().getProductsByBusinessType(CONSTANTS.TECHBUSSINESS);
                for (int j = 0; j < prods.size(); j++) {
                    if (populationManager.productLevelHasDemand(j)) {
                        int buyers = populationManager.getCustomerByProductLevel(j);
                        while(buyers >= 1) {
                            int indexBestPlayer = getBestPlayer(whs, prods.get(j));
                            Product prodToSell = whs.get(indexBestPlayer).getProductOnStock(prods.get(j));
                            if (indexBestPlayer == -1) break;
                            
                            if (buyers > prodToSell.getQuantityInStock()) {
                                System.out.println("Player " + indexBestPlayer + " sold " + prods.get(j).getName());
                                int quantityBeforeSale = prodToSell.getQuantityInStock();
                                this.clients.get(j).message.updateProfit(sellItem(whs.get(indexBestPlayer), prods.get(j), buyers));
                                buyers -= quantityBeforeSale;
                            } else {
                                System.out.println("Player " + indexBestPlayer + " sold ALL" + prods.get(j).getName());
                                this.clients.get(j).message.updateProfit(sellItem(whs.get(indexBestPlayer), prods.get(j), buyers));
                                buyers = 0;
                            }
                        }
                    }
                }
                for(int j = 0; j < this.clients.size(); j++) 
                    this.clients.get(j).message.setSerializedWarehouse(Warehouse.serialize(whs.get(j)));

                phaser.arriveAndAwaitAdvance();
            }
        }

        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("[GameServer] Erro:" + e.getMessage());
        }

        finally
        {
            System.out.println("[GameServer] Finalizando...");

            try
            {
                // Unir threads e encerrar socket
                this.phaser.forceTermination();
                for(GameConnection con: this.clients)
                    if(con.isAlive()) con.join();
                this.socket.close();
            }

            catch(Exception e) { }

            System.out.println("[GameServer] Servidor finalizado.");
        }
    }
    
    public static void main(String[] args)
    {
        int serverPort = (args.length == 1) ? Integer.parseInt(args[0]) : 7777;
        double money = (args.length == 1) ? Double.parseDouble(args[1]) : 100000.00;
        int bsType = (args.length == 1) ? Integer.parseInt(args[2]) : 0;
        int months = (args.length == 1) ? Integer.parseInt(args[3]) : 12;
        new GameServer(serverPort, money, bsType, months);
    }     

    public double getInitialMoney() {
        return initialMoney;
    }

    public int getBusinessType() {
        return businessType;
    }

    public int getMaxMonths() {
        return maxMonths;
    }
    
    private int calcMarketingInvestments() {
        int totalInvested = 0;
        for(GameConnection j : this.clients)
            totalInvested += j.message.getMarketingInvestment();
        return (int)totalInvested;
    }
    
    private int getBestPlayer(ArrayList<Warehouse> whs, Product product) {
        double bestCostBenefit = 0.0;
        int bestPlayerIndex = -1;
        for (int i = 0; i < this.clients.size(); i++) {
            Warehouse wh = whs.get(i);
            if (wh.has(product)) 
                if (wh.getProductOnStock(product).getCostBenefit() > bestCostBenefit) {
                    bestCostBenefit = wh.getProductOnStock(product).getCostBenefit();
                    bestPlayerIndex = i;
                }
        }
        return bestPlayerIndex; 
    }
    
    private double sellItem(Warehouse warehouse, Product prod, int buyers) {
        Product productToSell = warehouse.getProductOnStock(prod);
        double sellingPrice = productToSell.getSellPrice();
        int quantityInStock = productToSell.getQuantityInStock();
        int updatedQuantity = buyers - quantityInStock;
        if (updatedQuantity == 0)
            warehouse.getStock().remove(prod);
        else 
            productToSell.setQuantityInStock(updatedQuantity);
        
        int unitsSold = buyers - updatedQuantity;
        double moneyEarned = productToSell.getSellPrice() * unitsSold;
        
        return moneyEarned;
    }
}