
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class SalesPrintable implements Printable {

        private JTable table;
        private String headerText;
        private String footerText;
        private String id;
        private String customerName;
        private String date;
        private String total;
        private String vat;

        public void setPrintTableData(JTable table, String headerText, String footerText, String id, String customerName, String date, String total, String vat) {
            this.table = table;
            this.headerText = headerText;
            this.footerText = footerText;
            this.id = id;
            this.customerName = customerName;
            this.date = date;
            this.total = total;
            this.vat = vat;
        }

        /**
         *
         * @param graphics
         * @param pageFormat
         * @param pageIndex
         * @return
         * @throws PrinterException
         */

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            // Set font styles
            g2d.setFont(new Font("Arial", Font.BOLD, 16)); // Header font
            g2d.setColor(Color.BLACK);

            // Print header text
            g2d.drawString(headerText, (int) pageFormat.getImageableX() + 20, (int) pageFormat.getImageableY() + 30);

            // Print content
            g2d.setFont(new Font("Arial", Font.PLAIN, 12)); // Content font
            g2d.drawString("INVOICE NO: " + id, (int) pageFormat.getImageableX() + 20, (int) pageFormat.getImageableY() + 60);
            g2d.drawString("CUSTOMER NAME: " + customerName, (int) pageFormat.getImageableX() + 20, (int) pageFormat.getImageableY() + 80);
            g2d.drawString("SALE DATE: " + date, (int) pageFormat.getImageableX() + 20, (int) pageFormat.getImageableY() + 100);
            g2d.drawString("TOTAL AMOUNT: " + total, (int) pageFormat.getImageableX() + 20, (int) pageFormat.getImageableY() + 120);
            g2d.drawString("VAT: " + vat, (int) pageFormat.getImageableX() + 20, (int) pageFormat.getImageableY() + 140);

            // Set font and print footer text
            g2d.setFont(new Font("Arial", Font.ITALIC, 10)); // Footer font
            g2d.drawString(footerText, (int) pageFormat.getImageableX() + 20, (int) pageFormat.getImageableHeight() - 20);

            // Print the table
            int headerY = (int) pageFormat.getImageableY() + 170; // Adjust the starting y-coordinate for the table
            int rowHeight = table.getRowHeight();
            for (int row = 0; row < table.getRowCount(); row++) {
                int currentY = headerY + row * rowHeight;
                for (int col = 0; col < table.getColumnCount(); col++) {
                    TableColumn column = table.getColumnModel().getColumn(col);
                    Object value = table.getValueAt(row, col);
                    g2d.drawString(value.toString(), (int) pageFormat.getImageableX() + 20 + column.getWidth() * col, currentY);
                }
            }
            return Printable.PAGE_EXISTS;
        }
    }
    