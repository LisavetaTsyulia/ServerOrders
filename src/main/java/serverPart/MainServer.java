package serverPart;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {
    public static void main(String[] args) {
        new MainServer().setConnection();
    }

    private void setConnection() {
        try {
            ServerSocket serverSocket = new ServerSocket(3456);
            ServerListener serverListener = new ServerListener(serverSocket);
            serverListener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}