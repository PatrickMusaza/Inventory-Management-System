import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectCloud {

    private static final String DB_URL = "jdbc:mysql://34.28.10.216:3306/customerDB";
    private static final String DB_USER = "happylight";
    private static final String DB_PASS = "g@Secur!";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load database driver", e);
        }
    }

    public static boolean isDatabaseReachable() {
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            String query = "SELECT 1 FROM dual";
            try (ResultSet rs = st.executeQuery(query)) {
                return true; // If query executes successfully, the database is reachable
            }
        } catch (SQLException e) {
            System.err.println("Database connection check failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean isInternetReachable(String domainName) {
        try {
            InetAddress inetAddress = InetAddress.getByName(domainName);
            return inetAddress.isReachable(1000); // Timeout set to 1 second
        } catch (IOException e) {
            return false; // Failed to connect
        }
    }
}
