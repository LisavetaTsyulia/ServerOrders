package serverPart;

import java.io.*;
import java.net.Socket;


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
                    if (line != null) {
                        RequestFromCl request = new RequestFromCl(line);
                        System.out.println(request);
                        new RequestHandler(socket, request, order).start();
                    }
                } catch (IOException e) {
                    isAlive = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

