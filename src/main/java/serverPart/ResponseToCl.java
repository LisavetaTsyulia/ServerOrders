package serverPart;

public class ResponseToCl {
    private String respFromDB;

    public ResponseToCl(String str) {
        respFromDB = str;
    }

    public String toString() {
        return respFromDB;
    }
}
