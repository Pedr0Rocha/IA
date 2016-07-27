
package tcp;

import java.io.*;
import java.net.*;
import java.util.concurrent.Phaser;

public class GameConnection extends Thread
{
    private static final int MAGICNUMBER = 0x1AD42823;
    Phaser phaser;
    Socket socket;
    String clientName;
    int clientType;

    public GameConnection(Socket clientSocket, Phaser phaser) throws IOException
    {
        this.socket = clientSocket;
        this.phaser = phaser;
        phaser.register();
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

        try 
        {
            // Inicializa o fluxo de dados
            input = socket.getInputStream();
            output = socket.getOutputStream();
            obin = new ObjectInputStream(input);
            obout = new ObjectOutputStream(output);

            // Libera o buffer da conexão
            obout.flush();

            // Obtém os dados do jogador
            int magic = obin.readInt();
            this.clientName = (String) obin.readObject();
            this.clientType = obin.readInt();

            // Verifica se a conexão é autêntica
            if(magic != GameConnection.MAGICNUMBER)
                throw new InterruptedException("Magic number mismatch");

            System.out.println("[GameConnection] Cliente identificado - " + this.clientName + " => " + this.socket.getInetAddress().getHostAddress());

            // Dorme a thread até que todos os players se conectem
            this.phaser.arriveAndAwaitAdvance();

            // Neste momento o jogo será iniciado
            // (?)
        }

        catch(InterruptedException e) { /* Apenas encerrar a conexão */ }
        
        catch(IOException e) 
        { 
            System.out.println("[GameConnection] Conexão interrompida: " + e.getMessage());
        }

        catch(Exception e)
        {
            System.out.println("[GameConnection] Erro: " + e.getMessage());
        }

        finally
        {
            System.out.println("[GameConnection] Cliente desconectado - " + socket.getInetAddress().getHostAddress() + ".");
            try
            {
                this.phaser.arriveAndDeregister();
                obin.close();
                obout.close();
                this.socket.close();
            }
            catch(Exception e) {}
        }
    }
}