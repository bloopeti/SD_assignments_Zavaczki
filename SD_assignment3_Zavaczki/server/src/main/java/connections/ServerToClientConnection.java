package connections;

import commands.Command;
import commands.CommandFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerToClientConnection extends Thread {

    private Socket socket = null;
    private int id;
    private static ObjectOutputStream outputStream=null;
    private static ObjectInputStream inputStream=null;
    private Object readObject;

    public ServerToClientConnection(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                String received = (String) inputStream.readObject();
                String[] args = received.split("\n");
                Command command = CommandFactory.getCommand(args);
                if (command != null) {
                    outputStream.writeObject(command.execute());
                } else {
                    outputStream.writeObject(null);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
