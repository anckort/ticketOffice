package connectors;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    Connection connection;

    public ConnectionToDB() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String userName = "root";
        String password = "1";
        String connectionUrl = "jdbc:mysql://localhost:3306/ticket_office";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = (Connection) DriverManager.getConnection(connectionUrl, userName, password);
            System.err.println("---------------------------------Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("---------------------------------Error of connection");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
