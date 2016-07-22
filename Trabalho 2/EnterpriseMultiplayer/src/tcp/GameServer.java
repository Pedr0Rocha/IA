
package tcp;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class GameServer 
{
    public static int MaxPlayers = 4;
    private ArrayList<GameConnection> clients;
    private ServerSocket socket = null;
    private CyclicBarrier barrier;

    public GameServer(int port)
    {
        this.clients = new ArrayList<GameConnection>();
        this.barrier = new CyclicBarrier(MaxPlayers + 1);
        try
        {
            // Inicializar socket TCP
            this.socket = new ServerSocket(port);
            System.out.println("[GameServer] Servidor iniciado na porta " + port + ".");

            // Esperar todos os players se conectarem
            while(this.clients.size() < GameServer.MaxPlayers)
            {
                // Aceitar a conexão e criar a thread
                Socket client = this.socket.accept();
                this.clients.add(new GameConnection(client, this.barrier));

                // Caso alguma thread tenha sido encerrada, remover o jogador correspondente
                for(GameConnection con: this.clients)
                    if(!con.isAlive()) this.clients.remove(con);
            }

            // Sincroniza as threads
            barrier.await();
            
            // Iniciar o jogo
            // (?)
        }

        catch(Exception e)
        {
            System.out.println("[GameServer] Erro:" + e.getMessage());
        }

        finally
        {
            // Unir threads e encerrar socket
            try
            {
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
        new GameServer(serverPort);
    }     
}