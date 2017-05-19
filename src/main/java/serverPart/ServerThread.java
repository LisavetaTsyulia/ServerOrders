package serverPart;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class ServerThread extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private boolean isAlive;

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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             OutputStream os = socket.getOutputStream()) {
            while (isAlive) {
                try {
                    String line = br.readLine();
                    if (line != null && !line.equals("")) {
                        RequestFromCl request = new RequestFromCl(line);
                        new RequestHandler(os, request).start();
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

