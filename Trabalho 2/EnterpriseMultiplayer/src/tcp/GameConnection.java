
package tcp;

import java.io.*;
import java.net.*;
import java.util.concurrent.Phaser;

public class GameConnection extends Thread
{
    private static final int MAGICNUMBER = 0x1AD42823;
    public Socket socket;
    private GameServer server;

    public GameConnection(GameServer server, Socket client) throws IOException
    {
        this.socket = client;
        this.server = server;
        this.server.phaser.register();
        System.out.println("[GameConnection] Cliente conectado - " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + ".");
        this.start();
    }

    @Override
    public void run()
    {
        InputStream input;
        OutputStream output;
        ObjectInputStream obin = null;
        ObjectOutputStream obout = null;
        String clientName;
        Integer clientType;

        try 
        {
            // Inicializa o fluxo de dados
            input = socket.getInputStream();
            output = socket.getOutputStream();
            obin = new ObjectInputStream(input);
            obout = new ObjectOutputStream(output);

            // Verifica se a conexão é autêntica
            if(obin.readInt() != GameConnection.MAGICNUMBER)
                throw new InterruptedException("Magic number mismatch");

            // Obtém os dados do jogador
            clientName = (String) obin.readObject();
            clientType = (Integer) obin.readObject();

            System.out.println("[GameConnection] Cliente identificado - " + clientName + " => " + this.socket.getInetAddress().getHostAddress());

            // Dorme a thread até que todos os players se conectem
            this.server.phaser.arriveAndAwaitAdvance();

            // Neste momento o jogo será iniciado
            // (?)
        }

        catch(InterruptedException e) { /* Apenas encerrar a conexão */ }
        
        catch(Exception e)
        {
            System.out.println("[GameConnection] Conexão interrompida: " + e.getMessage());
        }

        finally
        {
            System.out.println("[GameConnection] Cliente desconectado - " + socket.getInetAddress().getHostAddress() + ".");

            try
            {
                obin.close();
                obout.close();
                this.socket.close();
                this.server.clients.remove(this);
                this.server.phaser.arriveAndDeregister();
            }
            catch(Exception e) {}
        }
    }
}