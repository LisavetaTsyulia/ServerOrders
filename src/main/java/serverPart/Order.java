package serverPart;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

public class Order {
    private String filmName;
    private String cinema;
    private String date;
    private String time;
    private int code;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setField(String field, String value) {
        switch (field) {
            case "cinema": setCinema(value); break;
            case "filmName": setFilmName(value); break;
            case "date": setDate(value);break;
            case "time": setTime(value);break;
            case "code": setCode(Integer.parseInt(value));break;
        }
    }
}
