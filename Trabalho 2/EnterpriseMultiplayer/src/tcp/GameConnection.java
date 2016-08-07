
package tcp;

import java.io.*;
import java.net.*;

public class GameConnection extends Thread
{
    private static final int MAGICNUMBER = 0x1AD42823;
    public final Socket socket;
    private final GameServer server;

    public GameConnection(GameServer server, Socket client) throws IOException
    {
        this.socket = client;
        this.server = server;
        this.server.phaser.register();
        System.out.println("[GameConnection] Cliente conectado - " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        this.start();
    }

    @Override
    public void run()
    {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        String clientname;

        try 
        {
            // Inicializa o fluxo de dados
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            output.flush();

            // Verifica se a conexão é autêntica
            int magic = (Integer) input.readObject();

            if(magic != GameConnection.MAGICNUMBER)
            {
                throw new InterruptedException("Magic number mismatch");
            }


            // Obtém os dados do jogador
            clientname = (String) input.readObject();

            // Dorme a thread até que todos os players se conectem
            this.server.phaser.arriveAndAwaitAdvance();

            // mandando configs iniciais para os clients
            double money = server.getInitialMoney();
            output.writeObject(money);
            int bsType = server.getBusinessType();
            output.writeObject(bsType);
            int months = server.getMaxMonths();
            output.writeObject(months);
            output.flush();
            
            int confirm = (Integer) input.readObject();
            this.server.phaser.arriveAndAwaitAdvance();
            
            // Neste momento o jogo será iniciado
            // (?)
        }

        catch(InterruptedException e) { /* Apenas encerrar a conexão */ }
        
        catch(Exception e)
        {
            System.out.println("[GameConnection] Conexão interrompida: " + e.getMessage());
        }

        finally
        {
            System.out.println("[GameConnection] Cliente desconectado - " + socket.getInetAddress().getHostAddress() + ".");

            try
            {
                input.close();
                output.close();
                this.socket.close();
                this.server.clients.remove(this);
                this.server.phaser.arriveAndDeregister();
            }
            catch(Exception e) {}
        }
    }
}