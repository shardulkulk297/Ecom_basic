package util;

public class TestDBConnection {
    public static void main(String[] args) {
        DBConnection dbConnection = DBConnection.getInstance();
        try {
            dbConnection.getConnection();
            System.out.println("Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
