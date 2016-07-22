
package tcp;

import java.io.*;
import java.net.*;

public class GameConnection extends Thread
{
    private static int MagicNumber = 0x1AD42823;
    Socket serverSocket;
    Socket clientSocket;
    String clientName;
    int clientType;

    public GameConnection(Socket serverSocket, Socket clientSocket) throws IOException
    {
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
        System.out.println("[GameConnection] Cliente conectado - " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort() + ".");
        this.start();
    }

    @Override
    public void run()
    {
        try 
        {
            // Inicializa o fluxo de dados
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            ObjectInputStream obin = new ObjectInputStream(input);
            ObjectOutputStream obout = new ObjectOutputStream(output);

            // Verifica se a conexão é autêntica
            if(obin.readInt() != this.MagicNumber)
                throw new InterruptedException();

            // Obtém os dados do jogador
            this.clientName = (String) obin.readObject();
            this.clientType = obin.readInt();
            System.out.println("[GameConnection] Cliente identificado - " + this.clientName);

            // Dorme a thread até que todos os players se conectem
            wait();

            // Neste momento o jogo já terá iniciado
            // (?)
        }

        catch(InterruptedException e) { /* Apenas encerrar a conexão */ }

        catch(Exception e)
        {
            System.out.println("[GameConnection] Erro:" + e.getMessage());
        }

        finally
        {
            System.out.println("[GameConnection] Cliente desconectado - " + this.clientName + ".");
            if(obin) obin.close();
            if(obout) obout.close();
            this.clientSocket.close();
        }
    }
}