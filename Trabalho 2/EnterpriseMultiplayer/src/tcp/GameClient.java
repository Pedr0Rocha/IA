
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
            this.socket = new Socket(ip, port);

            // Inicializa o fluxo de dados
            this.input = new ObjectInputStream(this.socket.getInputStream());
            this.output = new ObjectOutputStream(this.socket.getOutputStream());

            // Envia o magic number
            this.output.writeInt(GameClient.MAGICNUMBER);
            this.output.flush();
	}

	public void send(Object data) throws IOException
	{
            this.output.writeObject(data);
            this.output.flush();
	}

	public Object receive() throws Exception
	{
            return this.input.readObject();
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
            GameClient client;
            
            try
            {
                client = new GameClient(InetAddress.getByName("127.0.0.1"), 7777);
                client.send("Pedro");
                client.send(1);
            }
            
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
}