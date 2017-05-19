package serverPart;

import java.io.OutputStream;

public class RequestHandler extends Thread{
    private OutputStream outputStream;
    private RequestFromCl request;
    private ResponseToCl response;
    private DBHandler dbHandler;

    public RequestHandler(OutputStream outputStream, RequestFromCl request) {
        this.outputStream = outputStream;
        this.request = request;
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
            dbHandler.updateDB(request.getBody());
            System.out.println(request.toString());
        }
    }
}

