
package tcp;

import java.net.*;
import java.io.*;

public class GameServer
{
    public static void main(String args[]) throws IOException
    {
        try
        {
            int serverPort = 7777;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Servidor iniciado na porta " + serverPort + ".");
            while(true) new GameConnection(listenSocket.accept());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}