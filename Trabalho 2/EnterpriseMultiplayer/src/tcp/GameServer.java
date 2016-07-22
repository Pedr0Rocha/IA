
package tcp;

import java.net.*;
import java.io.*;

public class GameServer 
{
    public static int MaxPlayers = 4;
    private GameConnection[] clients;
    private ServerSocket socket;
    private int clientCount;

    public GameServer(int port)
    {
        this.clients = new GameConnection[MaxPlayers];
        this.clientCount = 0;

        try
        {
            // Inicializar socket TCP
            this.socket = new ServerSocket(port);
            System.out.println("[GameServer] Servidor iniciado na porta " + port + ".");

            // Esperar todos os players se conectarem
            while(this.clientCount < GameServer.MaxPlayers)
            {
                Socket client = this.socket.accept();
                this.clients[this.clientCount++] = new GameConnection(this, client);
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