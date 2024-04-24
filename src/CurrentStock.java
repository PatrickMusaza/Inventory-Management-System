
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrentStock {

    private PreparedStatement select;

    public float[] getCurrentStock(String code) {
        float currentStock = 0;
        float amount = 0;
        float[] result = new float[2];

        try {
            Connection con = Connect.getConnection();

            select = con.prepareStatement("SELECT Purchase, Sales, BeginningStock, PurchasePrice FROM stock WHERE ItemCode = ?");
            select.setString(1, code);

            ResultSet rs = select.executeQuery();

            try {
                if (rs.next()) {

                    float purchase = rs.getFloat("Purchase");
                    float sales = rs.getFloat("Sales");
                    float beginningStock = rs.getFloat("BeginningStock");
                    float price = rs.getFloat("PurchasePrice");

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
