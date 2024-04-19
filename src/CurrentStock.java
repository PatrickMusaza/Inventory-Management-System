
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrentStock {

    private PreparedStatement select;

    public int[] getCurrentStock(String code) {
        int currentStock = 0;
        int amount = 0;
        int[] result = new int[2];

        try {
            Connection con = Connect.getConnection();

            select = con.prepareStatement("SELECT Purchase, Sales, BeginningStock, PurchasePrice FROM stock WHERE ItemCode = ?");
            select.setString(1, code);

            ResultSet rs = select.executeQuery();

            try {
                if (rs.next()) {

                    int purchase = rs.getInt("Purchase");
                    int sales = rs.getInt("Sales");
                    int beginningStock = rs.getInt("BeginningStock");
                    int price = rs.getInt("PurchasePrice");

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
