package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static Connection connection;
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(ConnectionManager.class.getClassLoader().getResourceAsStream("application.properties"));
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Unable to establish connection to database." +  e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
