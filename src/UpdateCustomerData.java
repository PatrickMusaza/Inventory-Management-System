
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateCustomerData {

    public static String fileName = "C:\\Users\\Default\\AppData\\Local\\Microsoft\\lic.txt";

    public static void updateDateUsed(String licenseKeyVar) {
        ConnectCloud connector = new ConnectCloud();

        try {
            Connection con = connector.getConnection();

            if (con != null) {
                // Check if date_used is already set
                String checkQuery = "SELECT date_used FROM `hlt-customers` WHERE cst_license_key = ?";
                PreparedStatement checkPs = con.prepareStatement(checkQuery);
                checkPs.setString(1, licenseKeyVar);
                ResultSet rs = checkPs.executeQuery();

                if (rs.next() && rs.getDate("date_used") != null) {
                    System.out.println("Date already set. Skipping update.");
                    return; // Exit method without updating
                }

                // Get the current date
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = new Date();
                String formattedDate = dateFormat.format(currentDate);

                // Update date_used
                String query = "UPDATE `hlt-customers` SET date_used = ? WHERE cst_license_key = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, formattedDate);
                ps.setString(2, licenseKeyVar);
                ps.executeUpdate();
                System.out.println("Formatted Date: \n" + formattedDate);
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDaysLeft(String licenseKeyVar) {
        ConnectCloud connector = new ConnectCloud();

        try {
            Connection con = connector.getConnection();

            if (con != null) {
                // Get the current date
                Date currentDate = new Date();

                // Fetch the valid_to date from the database
                String query = "SELECT valid_to FROM `hlt-customers` WHERE cst_license_key = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, licenseKeyVar);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Get the valid_to date from the result set
                    Date validToDate = rs.getDate("valid_to");

                    // Calculate the difference in days between valid_to and current date
                    long differenceInMillis = validToDate.getTime() - currentDate.getTime();
                    long daysLeft = differenceInMillis / (1000 * 60 * 60 * 24); // Convert milliseconds to days

                    // Update the days_left column in the database
                    String updateQuery = "UPDATE `hlt-customers` SET days_left = ? WHERE cst_license_key = ?";
                    PreparedStatement updatePs = con.prepareStatement(updateQuery);
                    updatePs.setLong(1, daysLeft);
                    updatePs.setString(2, licenseKeyVar);
                    updatePs.executeUpdate();
                } else {
                    System.out.println("License key not found in the database.");
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch the license count from the database
    public static int fetchLicenseCount(String licenseKeyVar) {
        int licenseCount = 0;
        ConnectCloud connector = new ConnectCloud(); // Connect to the database
        try {
            Connection con = connector.getConnection();
            if (con != null) {
                String query = "SELECT `#lic_given` FROM `hlt-customers` WHERE `cst_license_key` = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, licenseKeyVar);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    licenseCount = rs.getInt("#lic_given"); // Store the fetched license count

                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return licenseCount;
    }
}
