
package tcp;

import java.io.*;
import java.net.*;

public class GameConnection extends Thread
{
    BufferedReader input;
    BufferedWriter output;
    Socket clientSocket;
    String clientName;
    int clientType;

    public GameConnection (Socket aClientSocket) throws IOException
    {
        clientSocket = aClientSocket;
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        System.out.println("Conexão iniciada em " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort() + ".");
        this.start();
    }

    @Override
    public void run()
    {
        try 
        {
            String buffer = input.readLine();
            System.out.println("Input: " + buffer);
            
            output.write(buffer, 0, buffer.length());
            output.newLine();
            output.flush();
            
            System.out.println("Output: " + buffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}