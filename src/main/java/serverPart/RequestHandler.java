package serverPart;

import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler extends Thread{
    private Socket socket;
    private RequestFromCl request;
    private ResponseToCl response;
    private DBHandler dbHandler;
    private Order order;

    public RequestHandler(Socket socket, RequestFromCl request, Order order) {
        this.socket = socket;
        this.request = request;
        this.order = order;
        dbHandler = new DBHandler();
        dbHandler.setConnection();
    }

    @Override
    public void run() {
        if ("get".equals(request.getCode())) {
            String columns = request.getField();
            response = new ResponseToCl(
                    dbHandler.getResponse("Select " + columns + " from Films;", columns));
            System.out.println(response.toString());
            try (OutputStream writer = socket.getOutputStream()) {
                writer.write((response.toString() + "\n").getBytes());
                writer.flush();
            } catch (Exception ex) {}
        } else if ("send".equals(request.getCode())) {
            String field = request.getField();
            String value = request.getBody();
            order.setField(field, value);
            System.out.println(request.toString());
        }
    }
}

