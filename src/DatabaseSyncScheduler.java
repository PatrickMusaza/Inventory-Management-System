
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseSyncScheduler {

    private static String REMOTE_DB_URL;
    private static String REMOTE_DB_USER;
    private static String REMOTE_DB_PASSWORD;

    public static String fileName= ValidateForm.fileName;

    public static void startDatabaseSyncScheduler() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {

            File file = new File(fileName);
            String licenseKey = "";
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    licenseKey += scanner.nextLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ValidateForm.class.getName()).log(Level.SEVERE, null, ex);
               // System.err.println("File not found: " + fileName);
                ex.printStackTrace();
            }

            if (isValidLicenseKey(licenseKey)) {
                try {
                    
                    Connection cloudConns = DriverManager.getConnection(REMOTE_DB_URL, REMOTE_DB_USER, REMOTE_DB_PASSWORD);
                    Connection localConns = Connect.getConnection();
                    System.out.println("Connection successful!");

                    // Create an instance of DatabaseSynchronizer
                    DatabaseSynchronizer synchronizer = new DatabaseSynchronizer(localConns, cloudConns);

                    // Perform database synchronization
                    synchronizer.synchronizeDatabases();

                    // Display success message
                    // JOptionPane.showMessageDialog(null, "Congratulations! The database has been synced.");
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseSyncScheduler.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex.getMessage().contains("sys_config")) {
                        // Log the error (optional)
                        // System.err.println("Skipping 'sys_config' table synchronization.");
                        // Display a success message indicating that synchronization was skipped
                        //JOptionPane.showMessageDialog(null, "Congratulations! The database has been synced.");
                    } else {
                        // Handle other SQL errors
                        ex.printStackTrace();
                    }
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    public static boolean isValidLicenseKey(String licenseKey) {
        ConnectCloud connector = new ConnectCloud();
        try (Connection con = connector.getConnection()) {
            if (con != null) {
                String query = "SELECT * FROM `hlt-customers` WHERE cst_license_key = ?";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, licenseKey);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String dbUrl = rs.getString("db_url");
                            String dbUser = rs.getString("db_user");
                            String dbPass = rs.getString("db_pass");
                            String daysLeft = rs.getString("days_left");
                          
                            REMOTE_DB_URL = dbUrl;
                            REMOTE_DB_USER = dbUser;
                            REMOTE_DB_PASSWORD = dbPass;
                                                        
                            return true;
                        }
                    }
                }
            } else {
                System.out.println("Failed to connect to the database.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
