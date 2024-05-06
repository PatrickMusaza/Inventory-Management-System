import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportToCSV {

    public static void exportCreditorsToCSV(String customerID) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement statement = con.prepareStatement(
                    "SELECT s.SaleDate, si.ItemName, si.Measurement, si.SalesQty, si.UnitPrice, si.TotalPrice AS Credited, s.SOUT AS Debited "
                    + "FROM sales s "
                    + "JOIN salesitem si ON s.InvoiceID = si.RefSale OR s.CustomerID=s.Ref_Inv "
                    + "WHERE s.CustomerID = ?"
            );

            // Set the customer ID parameter
            statement.setString(1, customerID);

            ResultSet rs = statement.executeQuery();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String csvFilePath = getCSVFilePath();

            try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
                // Headers
                String[] headers = {"Sale Date", "Description", "Measurement", "Qty", "Unit Price", "Credited", "Debited", "Balance"};
                csvWriter.append(String.join(",", headers));
                csvWriter.append("\n");

                // Data
                double balance = 0;
                while (rs.next()) {
                    Date saleDate = rs.getDate("SaleDate");
                    String itemName = rs.getString("ItemName");
                    String measure = rs.getString("Measurement");
                    double salesQty = rs.getDouble("SalesQty");
                    double unitPrice = rs.getDouble("UnitPrice");
                    double credited = rs.getDouble("Credited");
                    double debited = rs.getDouble("Debited");

                    // Format the sale date
                    String formattedDate = dateFormat.format(saleDate);

                    // Calculate row balance
                    double rowBalance = credited - debited;
                    balance += rowBalance;

                    // Write data to CSV
                    String[] rowData = {formattedDate, itemName, measure, String.valueOf(salesQty), String.valueOf(unitPrice),
                            String.valueOf(credited), String.valueOf(debited), String.valueOf(balance)};
                    csvWriter.append(String.join(",", rowData));
                    csvWriter.append("\n");
                }

                JOptionPane.showMessageDialog(null, "CSV file created successfully at: " + csvFilePath, "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCSVFilePath() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getPath() + ".csv";
        }
        return null;
    }
}
