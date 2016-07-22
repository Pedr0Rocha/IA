
package tcp;

import java.net.*;
import java.io.*;

public class GameServer 
{
    static int MaxPlayers = 4;
    private ServerSocket socket;
    private ArrayList<GameConnection> clients;

    public GameServer(int port)
    {
        try
        {
            // Inicializar socket TCP
            this.socket = new ServerSocket(port);
            System.out.println("[GameServer] Servidor iniciado na porta " + port + ".");

            // Esperar todos os players se conectarem
            while(this.clients.size() < GameServer.MaxPlayers)
            {
                Socket client = this.socket.accept();
                this.clients.add(new GameConnection(this, client));
            }

            // Acorda as threads
            notifyAll();
            
            // Iniciar o jogo
            // (?)
        }

        catch(KeyboardInterrupt e) { /* Apenas finalizar o servidor */ }

        catch(Exception e)
        {
            System.out.println("[GameServer] Erro:" + e.getMessage());
        }

        finally
        {
            // Finalizar socket
            if(this.socket) this.socket.close();
            System.out.println("[GameServer] Servidor finalizado.");
        }
    }

    public static void main(String[] args)
    {
        int serverPort = (args.length == 1) ? Integer.parseInt(args[0]) : 7777;
        new GameServer(serverPort);
    }     
}