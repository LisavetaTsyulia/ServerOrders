package serverPart;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class DBHandler {
    private static final String url = "jdbc:mysql://localhost:3306/orders";
    private static final String login = "lisa";
    private static final String pass = "mentalist";
    private Connection connection;

    public void setConnection() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(url, login, pass);
            if (!connection.isClosed()) {
                System.out.println("Connection with DB established:)");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getResponse(String reqLine, String columns) {
        String result = "";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(reqLine);
            while (rs.next()) {
                result += rs.getString(columns);
                result += '/';
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }

    //statement.execute("INSERT INTO Orders(FilmName, Cinema) VALUES ('Titanic', 'Aurora');");
        //statement.executeUpdate("UPDATE Orders SET FilmName='Leegaly Blond' WHERE OrderID = 1;");
        //ResultSet set = statement.executeQuery("SELECT * FROM Orders");
            /*
            statement.addBatch("INSERT INTO Orders (FilmName, Cinema) VALUES ('Trolls', 'Kiey');");
            statement.addBatch("INSERT INTO Orders (FilmName, Cinema) VALUES ('Split', 'Komsomolets');");
            statement.addBatch("INSERT INTO Orders (FilmName, Cinema) VALUES ('Beauty & the Beast', 'Moskva');");

            statement.executeBatch();
            statement.clearBatch();
            */

        // our SQL SELECT query.
        // if you only need a few columns, specify them by name instead of using "*"
        //String query = "SELECT OrderID, Cinema, FilmName FROM Orders";

        // execute the query, and get a java resultset
        //ResultSet rs = statement.executeQuery(query);

        // iterate through the java resultset
        /*
        Order order = new Order();
        while (rs.next())
        {
            order.setId(rs.getInt("OrderID"));
            order.setCinema(rs.getString("Cinema"));
            order.setFilmName(rs.getString("FilmName"));
            //Date dateCreated = rs.getDate("date_created");
            System.out.println(order.toString());
        }
        statement.close();

        String INSERT_Q = "INSERT  INTO Orders(Cinema, FilmName) VALUES(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Q);
        preparedStatement.setString(1, "Berestye");
        preparedStatement.setString(2, "Power Rangers");
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
        */

}
