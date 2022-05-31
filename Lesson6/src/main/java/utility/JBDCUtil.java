package utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JBDCUtil {

    public static Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/test_db?currentSchema=booking";
        String user = "root";
        String passwd = "root";
        return DriverManager.getConnection(url, user, passwd);
    }
}
