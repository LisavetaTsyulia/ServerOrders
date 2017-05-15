package serverPart;

import java.io.OutputStream;

public class RequestHandler extends Thread{
    private OutputStream outputStream;
    private RequestFromCl request;
    private ResponseToCl response;
    private DBHandler dbHandler;
    private Order order;

    public RequestHandler(OutputStream outputStream, RequestFromCl request, Order order) {
        this.outputStream = outputStream;
        this.request = request;
        this.order = order;
        dbHandler = new DBHandler();
        dbHandler.setConnection();
    }

    @Override
    public void run() {
        if ("get".equals(request.getCode())) {
            response = new ResponseToCl(
                    dbHandler.getResponse(request.getBody(), request.getField()));
            System.out.println(response.toString());
            try {
                outputStream.write((response.toString() + "\n").getBytes());
                outputStream.flush();
            } catch (Exception ex) {}
        } else if ("send".equals(request.getCode())) {
            String field = request.getField();
            String value = request.getBody();
            order.setField(field, value);
            System.out.println(request.toString());
        }
    }
}

