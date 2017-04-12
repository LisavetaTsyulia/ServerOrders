package serverPart;

import java.io.PrintWriter;
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
        if (request.getCode().equals("get")) {
            String columns = request.getField();
            response = new ResponseToCl(
                    dbHandler.getResponse("Select " + columns + " from animals;", columns));
            System.out.println(response.toString());
            try (PrintWriter writer = new PrintWriter(socket.getOutputStream())){
                writer.write(response.toString());
                writer.flush();
            }catch (Exception ex) {}
        } else if (request.getCode().equals("send")) {
            String field = request.getField();
            String value = request.getBody();
            order.setField(field, value);
            System.out.println(request.toString());
        }
    }
}

