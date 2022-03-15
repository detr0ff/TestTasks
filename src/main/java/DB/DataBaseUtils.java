package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtils {
    protected static String host = "jdbc:mysql://localhost:3307/union_reporting?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    protected static String login = "root";
    protected static String password = "root";

    public static Connection connection(){
        try {
            Connection conn = DriverManager.getConnection(host, login, password);
            return conn;
        } catch (SQLException throwables) {
            throw new RuntimeException("Connection error");
        }
    }
}
