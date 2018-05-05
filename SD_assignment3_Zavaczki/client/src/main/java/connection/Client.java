package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private ClientToServerConnection clientToServerConnection;
    private Client instance;

    private Client() {
        try {
            clientSocket = new Socket("localhost", 6666);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            clientToServerConnection = new ClientToServerConnection(inputStream);
            clientToServerConnection.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getInstance() {
        return instance;
    }

    public static void main(String[] args) {

    }
}
