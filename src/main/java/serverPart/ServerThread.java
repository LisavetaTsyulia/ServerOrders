package serverPart;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class ServerThread extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private boolean isAlive;
    private Order order;

    public ServerThread(Socket socket) {
        try {
            this.socket = socket;
            socket.setSoTimeout(2000);
            this.inputStream = socket.getInputStream();
            isAlive = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        order = new Order();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            while (isAlive) {
                try {
                    String line = br.readLine();
                    if (line != null && !line.equals("")) {
                        RequestFromCl request = new RequestFromCl(line);
                        new RequestHandler(socket, request, order).start();
                    }
                } catch (SocketTimeoutException ignored) {
                } catch (IOException e) {
                    e.printStackTrace();
                    isAlive = false;
                }
            }
            System.out.println("END");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

