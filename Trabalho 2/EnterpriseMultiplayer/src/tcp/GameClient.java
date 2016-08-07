
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
    private double initialMoney;
    private int businessType;
    private int maxMonths;

    public GameClient(InetAddress ip, int port, String clientname) throws IOException
    {
        this.socket = new Socket(ip, port);
        this.clientname = clientname;
        this.closed = false;

        // Inicializa o fluxo de dados
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
        this.output.flush();
        this.input = new ObjectInputStream(this.socket.getInputStream());

        // Verifica o magic number
        this.send(GameClient.MAGICNUMBER);
        this.send(this.clientname);
        
        this.initialMoney = (Double) receive();
        this.businessType = (Integer) receive();
        this.maxMonths = (Integer) receive();
    }

    public void send(Object data) throws IOException
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

    public Object receive() throws IOException
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

    public double getInitialMoney() {
        return initialMoney;
    }

    public int getBusinessType() {
        return businessType;
    }

    public int getMaxMonths() {
        return maxMonths;
    }
    
    
}