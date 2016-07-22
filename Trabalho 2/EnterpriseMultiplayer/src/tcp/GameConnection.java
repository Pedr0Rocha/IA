
package tcp;

import java.io.*;
import java.net.*;
import java.util.concurrent.CyclicBarrier;

public class GameConnection extends Thread
{
    private static final int MAGICNUMBER = 0x1AD42823;
    CyclicBarrier barrier;
    Socket socket;
    String clientName;
    int clientType;

    public GameConnection(Socket clientSocket, CyclicBarrier barrier) throws IOException
    {
        this.socket = clientSocket;
        this.barrier = barrier;
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

            // Verifica se a conexão é autêntica
            if(obin.readInt() != GameConnection.MAGICNUMBER)
                throw new InterruptedException("Magic number mismatch");

            // Obtém os dados do jogador
            this.clientName = (String) obin.readObject();
            this.clientType = obin.readInt();
            System.out.println("[GameConnection] Cliente identificado - " + this.clientName + " => " + socket.getInetAddress().getHostAddress());

            // Dorme a thread até que todos os players se conectem
            this.barrier.await();

            // Neste momento o jogo será iniciado
            // (?)
        }

        catch(InterruptedException e) { /* Apenas encerrar a conexão */ }

        catch(Exception e)
        {
            System.out.println("[GameConnection] Erro:" + e.getMessage());
        }

        finally
        {
            System.out.println("[GameConnection] Cliente desconectado - " + socket.getInetAddress().getHostAddress() + ".");
            try
            {
                obin.close();
                obout.close();
                this.socket.close();
            }
            catch(Exception e) {}
        }
    }
}