package serverPart;

public class Order {
    private int filmID;
    private int cinemaID;
    private String date;
    private String time;
    private String seat;

    public int getFilmName() {
        return filmID;
    }

    public void setFilmName(int filmName) {
        this.filmID = filmName;
    }

    public int getCinema() {
        return cinemaID;
    }

    public void setCinema(int cinema) {
        this.cinemaID = cinema;
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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String code) {
        this.seat = code;
    }

    public void setField(String field, String value) {
        switch (field) {
            case "cinemaID": setCinema(Integer.parseInt(value)); break;
            case "filmID": setFilmName(Integer.parseInt(value)); break;
            case "date": setDate(value);break;
            case "time": setTime(value);break;
            case "code": setSeat(value);break;
        }
    }
}
