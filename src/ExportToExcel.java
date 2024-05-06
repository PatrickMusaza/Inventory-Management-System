
import java.io.FileOutputStream;
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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportToExcel {

    public static void exportToExcel(String customerID) {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement statement = con.prepareStatement(
                    "SELECT s.createdAt, s.CustomerName, si.ItemName, si.Measurement, si.SalesQty, si.UnitPrice, si.TotalPrice, s.SOUT "
                    + "FROM sales s "
                    + "JOIN salesitem si ON s.InvoiceID = si.RefSale "
                    + "WHERE s.CustomerID = ?"
            );

            // Set the customer ID parameter
            statement.setString(1, customerID);

            ResultSet rs = statement.executeQuery();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Creditors");

            // Headers
            Row headerRow = (Row) sheet.createRow(0);
            String[] headers = {"DATE", "DESCRIPTION", "MEASUREMENT", "QUANTITY", "UNIT/PRICE", "CREDITED", "DEBITED", "BALANCE"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Data
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            double balance = 0;

            int rowNum = 1;
            while (rs.next()) {
                Date createdAt = rs.getDate("createdAt");
                String itemName = rs.getString("ItemName");
                String measure = rs.getString("Measurement");
                double salesQty = rs.getDouble("SalesQty");
                double unitPrice = rs.getDouble("UnitPrice");
                double credited = rs.getDouble("TotalPrice");
                double debited = rs.getDouble("SOUT");

                Row row = sheet.createRow(rowNum++);

                // Set Date
                Cell dateCell = row.createCell(0);
                dateCell.setCellValue(dateFormat.format(createdAt));

                // Set Description
                Cell descCell = row.createCell(1);
                descCell.setCellValue(itemName);

                // Set Measurement
                Cell measureCell = row.createCell(2);
                measureCell.setCellValue(measure);

                // Set Quantity
                Cell qtyCell = row.createCell(3);
                qtyCell.setCellValue(salesQty);

                // Set Unit Price
                Cell priceCell = row.createCell(4);
                priceCell.setCellValue(unitPrice);

                // Set Credited
                Cell creditedCell = row.createCell(5);
                creditedCell.setCellValue(credited);

                // Set Debited
                Cell debitedCell = row.createCell(6);
                debitedCell.setCellValue(debited);

                // Calculate Balance
                balance += credited - debited;

                // Set Balance
                Cell balanceCell = row.createCell(7);
                balanceCell.setCellValue(balance);
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Prompt user to select file name and path
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath() + ".xlsx";

                // Write the output to the selected file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(null, "Excel file created successfully at: " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            // Close the workbook
            workbook.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
