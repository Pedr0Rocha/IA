
package tcp;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class GameServer 
{
    public static int MaxPlayers = 1;
    public final ArrayList<GameConnection> clients;
    public ServerSocket socket;
    public final Phaser phaser;

    public GameServer(int port)
    {
        this.clients = new ArrayList<GameConnection>();
        this.phaser = new Phaser(1);

        try
        {
            // Inicializar socket TCP
            this.socket = new ServerSocket(port);
            System.out.println("[GameServer] Iniciado na porta " + port + ".");

            // Esperar todos os players se conectarem
            while(this.clients.size() < MaxPlayers)
            {
                // Aceitar a conexão e criar a thread
                Socket client = this.socket.accept();
                this.clients.add(new GameConnection(this, client));
            }

            // Sincroniza as threads
            phaser.arriveAndAwaitAdvance();

            // Iniciar o jogo
            System.out.println("[GameServer] Jogadores conectados. Iniciando...");

            // Aqui começa o jogo
        }

        catch(Exception e)
        {
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
        new GameServer(serverPort);
    }     
}