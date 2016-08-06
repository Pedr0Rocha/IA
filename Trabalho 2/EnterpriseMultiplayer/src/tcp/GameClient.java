
package tcp;

import java.net.*;
import java.io.*;


public class GameClient
{
    private static final int MAGICNUMBER = 0x1AD42823;
    private final Socket socket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private final String clientname;
    private boolean closed;

    public GameClient(InetAddress ip, int port, String clientname) throws IOException
    {
        this.socket = new Socket(ip, port);
        this.clientname = clientname;
        this.closed = false;

        // Inicializa o fluxo de dados
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
        this.input = new ObjectInputStream(this.socket.getInputStream());

        // Verifica o magic number
        this.sendObject(GameClient.MAGICNUMBER);
        int ans = this.receiveInt();
        if(ans != 1) throw new IOException("Magic number mismatch");

        // Envia o nome do jogador
        this.sendObject(this.clientname);
    }

    public void sendObject(Object data) throws IOException
    {
        try
        {
            this.output.writeObject(data);
            this.output.flush();
        }
        catch(Exception e)
        {
            this.close();
            throw new IOException("Connection closed");
        }
    }

    public void sendInt(int data) throws IOException
    {
        try
        {
            this.output.writeInt(data);
            this.output.flush();
        }
        catch(Exception e)
        {
            this.close();
            throw new IOException("Connection closed");
        }
    }

    public Object receiveObject() throws IOException
    {
        try
        {
            return this.input.readObject();
        }
        catch(Exception e)
        {
            this.close();
            throw new IOException("Connection closed");
        }
    }

    public int receiveInt() throws IOException
    {
        try
        {
            return this.input.readInt();
        }
        catch(Exception e)
        {
            this.close();
            throw new IOException("Connection closed");
        }
    }

    public void close()
    {
        if(!this.closed)
        {
            try
            {
                this.input.close();
                this.output.close();
                this.socket.close();
            }
            catch(Exception e) { }
            this.closed = true;
        }
    }
        
    public static void main(String[] args)
    {
        GameClient client = null;
        
        try
        {
            System.out.println("[GameClient] Conectando...");
            client = new GameClient(InetAddress.getLocalHost(), 7777, "Pedro");
            System.out.println("[GameClient] Conectado!");
        }
        
        catch(IOException e) {}
        
        catch(Exception e)
        {
            e.printStackTrace();
        }

        finally
        {
            System.out.println("[GameClient] Desconectado.");
            client.close();
        }
    }
}