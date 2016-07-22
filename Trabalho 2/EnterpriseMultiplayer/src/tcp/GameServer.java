
package tcp;

import java.net.*;
import java.io.*;

public class GameServer 
{
    private boolean enabled;
    private ServerSocket socket;

    public GameServer(int port)
    {
        try
        {
            this.enabled = true;
            this.socket = new ServerSocket(port);
            System.out.println("[GameServer] Servidor iniciado na porta " + port + ".");

            while(true)
            {
                Socket client = this.socket.accept();
                new GameConnection(this, client);
            }
        }

        catch(KeyboardInterrupt e) { /* Fazer nada */ }
        
        catch(Exception e)
            System.out.println("[GameServer] Erro:" + e.getMessage());

        finally
        {
            if(this.socket) this.socket.close();
            System.out.println("[GameServer] Servidor finalizado.");
        }
    }

    public static void main(String[] args)
    {
        new GameServer(7777);    
    }     
}