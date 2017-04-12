package serverPart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestFromCl {
    private String code;
    private String field;
    private String body;

    public RequestFromCl(String line) {
        parseLine(line);
    }

    public void parseLine(String line) {
        Pattern pattern = Pattern.compile("(get|send)>>(.*)>>(.*)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            code = matcher.group(1);
            field = matcher.group(2);
            body = matcher.group(3);
        }
    }

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public String getBody() {
        return body;
    }

    public String toString() {
        return code + ' ' + field + ' ' + body;

    }
}
