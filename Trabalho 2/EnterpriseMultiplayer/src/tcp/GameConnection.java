
package tcp;

import java.io.*;
import java.net.*;

public class GameConnection extends Thread
{
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

            // Obtém os dados do jogador
            // (?)

            // Dorme a thread até que todos os players se conectem
            wait();

            // Neste momento o jogo já terá iniciado
        }

        catch (Exception e)
        {
            System.out.println("[GameConnection] Erro:" + e.getMessage());
        }

        finally
        {
            System.out.println("[GameConnection] Cliente desconectado - " + this.clientSocket.getInetAddress().getHostAddress() + ".");
            this.clientSocket.close();
        }
    }
}