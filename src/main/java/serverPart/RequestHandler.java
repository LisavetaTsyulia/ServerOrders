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
            if ("seats".equals(request.getField())) {
                String [] requestForSeats = request.getBody().split("/");
                String [] seatsIndex = requestForSeats[1].split(",");
                Boolean sendOrNot = true;
                response = new ResponseToCl(
                        dbHandler.getResponse(requestForSeats[2], request.getField()));
                String seansString = response.toString();
                for (String index:
                     seatsIndex) {
                    if ( "1".equals(String.valueOf(seansString.charAt(Integer.parseInt(index))))) {
                        sendOrNot = false;
                    }
                }
                if (sendOrNot) {
                    dbHandler.updateDB(requestForSeats[0]);
                    response.setRespFromDB("true");
                } else {
                    response.setRespFromDB("false");
                }
            } else {
                dbHandler.updateDB(request.getBody());
            }
            try {
                outputStream.write((response.toString() + "\n").getBytes());
                outputStream.flush();
            } catch (Exception ex) {}
        }
    }
}

