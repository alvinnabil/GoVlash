package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/govlash_laundry";
    private static final String USER = "root";   
    private static final String PASS = "";       

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Database connected!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
