package serverPart;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerListener extends Thread {
    private ServerSocket serverSocket;
    private boolean isAlive;

    public ServerListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        isAlive = true;
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            } catch (SocketTimeoutException ignored) {
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        serverSocket.close();
        super.finalize();
    }
}

