package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String url="jdbc:mysql://localhost:3306/ecom";
    private String userDB = "root";
    private String passDB = "Shardul@297";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection con;

    private DBConnection() {}  //private constructor so that no one can create object of this class

    private static DBConnection dbConnection = new DBConnection();
    //Creating object here which will be singleton

    public static DBConnection getInstance() {
        return dbConnection;
    }
    //method that will return object of this class

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userDB, passDB);
    }

}
