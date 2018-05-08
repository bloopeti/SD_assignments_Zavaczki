package connection;

import commands.Command;
import commands.CommandFactory;
import commands.serialization.DeserializeCommand;
import model.Article;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientToServerConnection extends Thread {
    private ObjectInputStream inputStream;

    public ClientToServerConnection(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void run() {
        try {
            while (true) {
//                Object o = inputStream.readObject();
                String received = (String) inputStream.readObject();
                System.out.println("Received: " + received);
                String[] args = received.split("\n");
                //TODO process input
                Command command = CommandFactory.getCommand(args);
                System.out.println(command.execute().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
