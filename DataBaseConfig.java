import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    protected String url = "jdbc:mysql://localhost:3306/geopark_silokek";
    protected String user = "root";
    protected String pass = "";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
