
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrentStock {

    private PreparedStatement select;

    public double[] getCurrentStock(String code) {
        double currentStock = 0;
        double amount = 0;
        double[] result = new double[2];

        try {
            Connection con = Connect.getConnection();

            select = con.prepareStatement("SELECT Purchase, Sales, BeginningStock, PurchasePrice FROM stock WHERE ItemCode = ?");
            select.setString(1, code);

            ResultSet rs = select.executeQuery();

            try {
                if (rs.next()) {

                    double purchase = rs.getDouble("Purchase");
                    double sales = rs.getDouble("Sales");
                    double beginningStock = rs.getDouble("BeginningStock");
                    double price = rs.getDouble("PurchasePrice");

                    currentStock = beginningStock + purchase - sales;
                    amount = price * currentStock;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentStock.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStock.class.getName()).log(Level.SEVERE, null, ex);
        }

        result[0] = currentStock;
        result[1] = amount;
        return result;
    }
}
