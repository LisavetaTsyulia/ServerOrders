package serverPart;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDate;

public class MainServer {
    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        mainServer.checkDB();
        mainServer.setConnection();
    }

    private void checkDB() {
        DBHandler dbHandler = new DBHandler();
        dbHandler.setConnection();
        dbHandler.updateDB("DELETE from seans where date < '" + LocalDate.now() + "';");
        dbHandler.updateDB("DELETE from Films where endDate < '" + LocalDate.now() + "';");
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