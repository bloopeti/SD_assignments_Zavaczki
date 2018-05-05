package connection;

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
                Object o = inputStream.readObject();
                //TODO process input
                System.out.println(o.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
