
package tcp;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class GameServer 
{
    public static int MaxPlayers = 4;
    private ArrayList<GameConnection> clients;
    private ServerSocket socket = null;

    public GameServer(int port)
    {
        this.clients = new ArrayList<GameConnection>();
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
                this.clients.add(new GameConnection(client));

                // Caso alguma thread tenha sido encerrada, remover o jogador correspondente
                for(GameConnection con : this.clients)
                    if(!con.isAlive()) this.clients.remove(con);
            }

            // Acorda as threads
            notifyAll();
            
            // Iniciar o jogo
            // (?)
        }

        catch(Exception e)
        {
            System.out.println("[GameServer] Erro:" + e.getMessage());
        }

        finally
        {
            // Finalizar socket
            try { this.socket.close(); }
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