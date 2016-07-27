
package tcp;

import java.net.*;
import java.io.*;


public class GameClient
{
	private static final int MAGICNUMBER = 0x1AD42823;
	private final Socket socket;
	private final ObjectInputStream input;
	private final ObjectOutputStream output;

	public GameClient(InetAddress ip, int port) throws IOException
	{
		System.out.println("[GameClient] Conectando...");
		this.socket = new Socket(ip, port);

		// Inicializa o fluxo de dados
		this.output = new ObjectOutputStream(this.socket.getOutputStream());
		this.output.flush();
		this.input = new ObjectInputStream(this.socket.getInputStream());

		// Envia o magic number
		this.send(GameClient.MAGICNUMBER);

		// Conectado!
		System.out.println("[GameClient] Conectado - " + ip.getHostAddress());
	}

	public void send(Object data) throws IOException
	{
		this.output.writeObject(data);
		this.output.flush();
	}

	public Object receive() throws IOException
	{
		Object ret = null;
		try
		{
			ret = this.input.readObject();
		}
		catch(ClassNotFoundException e)
		{
			this.close();
			throw new IOException("Invalid object received");
		}
		return ret;
	}

	public void close()
	{
		try
		{
			this.input.close();
			this.output.close();
			this.socket.close();
		}

		catch(Exception e) {}
	}
        
    public static void main(String[] args)
    {
        GameClient client = null;
        
        try
        {
            client = new GameClient(InetAddress.getLocalHost(), 7777);
            client.send("Pedro");
            client.send(1);
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                client.close();
            }

            catch(Exception e) {}
        }
    }
}