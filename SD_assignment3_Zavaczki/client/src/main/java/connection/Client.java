package connection;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private ClientToServerConnection clientToServerConnection;
    private static Client instance;

    private Client() {
        try {
            clientSocket = new Socket("localhost", 6666);
            System.out.println("Starting client");
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            clientToServerConnection = new ClientToServerConnection(inputStream);
            clientToServerConnection.start();
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\serialized.json");
            String msg = "user\ndeserializeFromFile\n" + filePath;
            System.out.println("Requesting: " + msg) ;
            outputStream.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Client getInstance() {
        if(instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public static void main(String[] args) {
        Client client = Client.getInstance();


    }
}
