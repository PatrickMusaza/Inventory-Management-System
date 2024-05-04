
import java.sql.ResultSetMetaData;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Patrick
 */
public class PurchaseRegistration extends javax.swing.JFrame {

    /**
     * Creates new form PurchaseRegistration
     */
    public PurchaseRegistration() {
        initComponents();

        Image logo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
        this.setIconImage(logo);

        this.ReleaseDate.setDate(new Date());

        generateInvoiceCode();
        table_update();
        PaymentMethod();

    }

    public static void setCustomerDetails(String code, String name) {
        PurchaseRegistration.SupplierID.setText(code);
        PurchaseRegistration.SupplierName.setText(name);
    }

    public static void setItemDetails(String code, String name) {
        PurchaseRegistration.ItemCode.setText(code);
        PurchaseRegistration.ItemName.setText(name);
    }

    private void PaymentMethod() {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement Method = con.prepareStatement("Select Method from Payment");
            ResultSet met = Method.executeQuery();
            List<String> methods = new ArrayList<>();

            while (met.next()) {
                String method = met.getString("Method");
                methods.add(method);
            }

            this.Method.setModel(new DefaultComboBoxModel<>(methods.toArray(new String[0])));

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generateItemDetails() {

        PreparedStatement select;

        try {

            String Code = ItemCode.getText();
            String Name = ItemName.getText();

            Connection con = Connect.getConnection();

            select = con.prepareStatement("SELECT  TaxType,PurchaseUnit,SalePrice FROM item WHERE ItemCode = ? AND ItemName= ?");
            select.setString(1, Code);
            select.setString(2, Name);

            ResultSet rs = select.executeQuery();

            // Check if the item with the given code exists
            if (rs.next()) {
                // Retrieve values from the ResultSet

                String taxType = rs.getString("TaxType");
                PurchaseRegistration.Tax.setSelectedItem(taxType);

                String UnitPrice = rs.getString("PurchaseUnit");
                PurchaseRegistration.UnitPrice.setText(UnitPrice);

                String SalesPrice = rs.getString("SalePrice");
                PurchaseRegistration.SalesPrice.setText(SalesPrice);

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public String generateInvoiceCode() {
        String Code = "";
        try {

            Connection con = Connect.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(CreatedAt) FROM purchase");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int count = result.getInt(1);
                Code = "PRC " + (1000 + count + 1);
                this.PurchaseID.setText(Code);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return Code;
    }

    private void table_update() {

        int count;

        try {

            Connection con = Connect.getConnection();

            insert = con.prepareStatement("select * from purchaseitem where RefPurchase=?");
            insert.setString(1, generateInvoiceCode());

            ResultSet rs = insert.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            count = rsmd.getColumnCount();

            DefaultTableModel Df = (DefaultTableModel) jTable2.getModel();
            Df.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= count; i++) {

                    v2.add(rs.getString("ItemCode"));
                    v2.add(rs.getString("ItemName"));
                    v2.add(rs.getString("UnitPrice"));
                    v2.add(rs.getString("PurchaseQty"));
                    v2.add(rs.getString("TotalPrice"));

                }

                Df.addRow(v2);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private void totalAmount() {
        try {

            Connection con = Connect.getConnection();

            PreparedStatement select = con.prepareStatement("SELECT SUM(TotalPrice) AS Total, SUM(VAT) AS Vat FROM purchaseItem WHERE RefPurchase = ?");
            select.setString(1, generateInvoiceCode());

            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("Total");
                double vat = rs.getDouble("Vat");

                DecimalFormat dfTotal = new DecimalFormat("#.##");
                this.TotalAmount.setText(dfTotal.format(total));
                DecimalFormat dfVAT = new DecimalFormat("#.##");
                this.VAT.setText(dfVAT.format(vat));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        savePurchase = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Purchase = new javax.swing.JRadioButton();
        SupplierID = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        TotalAmount = new javax.swing.JTextField();
        Remark = new javax.swing.JTextField();
        PurchaseCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ReleaseDate = new com.toedter.calendar.JDateChooser();
        SupplierName = new javax.swing.JTextField();
        VAT = new javax.swing.JTextField();
        PurchaseID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        SupplierSDCD = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        SupplierReceipt = new javax.swing.JTextField();
        PurchaseReason = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        Method = new javax.swing.JComboBox<>();
        Debtor = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        AddItem = new javax.swing.JButton();
        Confirm = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ItemCode = new javax.swing.JTextField();
        ItemName = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        UnitPrice = new javax.swing.JTextField();
        SalesQty = new javax.swing.JTextField();
        SalesPrice = new javax.swing.JTextField();
        VAT_Item = new javax.swing.JTextField();
        TotalPrice = new javax.swing.JTextField();
        Tax = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        RemoveItem = new javax.swing.JButton();
        DeleteAll = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Origin", "Item Type", "Pack Unit", "Qty Unit", "Purchase Price", "Sale Price", "Beginning Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        setTitle("Purchase Registration");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setText("Purchase Registration");

        savePurchase.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        savePurchase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/floppy-disk.png"))); // NOI18N
        savePurchase.setText("Save");
        savePurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePurchaseActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close.png"))); // NOI18N
        Exit.setText("Close");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(savePurchase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Exit)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(savePurchase)
                    .addComponent(Exit))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setText("Sale Type");

        jLabel3.setText("Supplier ID");

        jLabel4.setText("Purchase Reason");

        jLabel5.setText("Total Amount");

        jLabel7.setText("Purchase Code");

        jLabel8.setText("Remark");

        Purchase.setText("Purchase");
        Purchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseActionPerformed(evt);
            }
        });

        SupplierID.setEditable(false);
        SupplierID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierIDActionPerformed(evt);
            }
        });

        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe.png"))); // NOI18N
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        TotalAmount.setEditable(false);

        jLabel6.setText("Supplier Name");

        jLabel9.setText("Purchase ID");

        jLabel10.setText("Release Date");

        jLabel11.setText("VAT");

        SupplierName.setEditable(false);

        VAT.setEditable(false);
        VAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VATActionPerformed(evt);
            }
        });

        PurchaseID.setEditable(false);

        jLabel25.setText("Supplier SDCD");

        SupplierSDCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierSDCDActionPerformed(evt);
            }
        });

        jLabel26.setText("Supplier Receipt Number");

        SupplierReceipt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SupplierReceiptKeyTyped(evt);
            }
        });

        jLabel27.setText("Method of Payment");

        Method.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Method.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MethodActionPerformed(evt);
            }
        });

        Debtor.setText("Debtor");
        Debtor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebtorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel25))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(TotalAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(SupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(Purchase)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Debtor))
                                    .addComponent(SupplierSDCD, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PurchaseReason)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(37, 37, 37)
                                .addComponent(PurchaseCode)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Method, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PurchaseID)
                            .addComponent(ReleaseDate, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(VAT)
                            .addComponent(SupplierName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SupplierReceipt, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(75, 75, 75)
                        .addComponent(Remark)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Purchase)
                            .addComponent(Debtor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(SupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(SupplierSDCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(PurchaseReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))))
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(PurchaseID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(SupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SupplierReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(ReleaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(VAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PurchaseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)
                        .addComponent(Method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(Remark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel12.setText("Purchase Item Information");

        AddItem.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        AddItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add-list.png"))); // NOI18N
        AddItem.setText("Add Item");
        AddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemActionPerformed(evt);
            }
        });

        Confirm.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Confirm.setForeground(new java.awt.Color(0, 153, 51));
        Confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/verified.png"))); // NOI18N
        Confirm.setText("Confirm");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Confirm)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(AddItem)
                    .addComponent(Confirm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Item Code");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Item Name");

        ItemCode.setEditable(false);

        ItemName.setEditable(false);
        ItemName.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                ItemNameInputMethodTextChanged(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(ItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ItemName)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(ItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setText("Purchase Price");

        jLabel16.setText("Purchase Qty");

        jLabel17.setText("Sales Price");

        jLabel18.setText("Tax Type");

        jLabel19.setText("VAT");

        jLabel20.setText("Total Price");

        UnitPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UnitPriceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UnitPriceKeyTyped(evt);
            }
        });

        SalesQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesQtyActionPerformed(evt);
            }
        });
        SalesQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SalesQtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SalesQtyKeyTyped(evt);
            }
        });

        SalesPrice.setEditable(false);

        VAT_Item.setEditable(false);

        TotalPrice.setEditable(false);

        Tax.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A-EX", "B-18%", "C" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(SalesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UnitPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(SalesQty))))
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VAT_Item)
                    .addComponent(Tax, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalPrice))
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(Tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(VAT_Item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(SalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(SalesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel24.setText("Detail List");

        RemoveItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RemoveItem.setText("Remove");
        RemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveItemActionPerformed(evt);
            }
        });

        DeleteAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DeleteAll.setText("Empty");
        DeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(RemoveItem)
                .addGap(18, 18, 18)
                .addComponent(DeleteAll)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DeleteAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(RemoveItem))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Purchase Price", "Purchase Qty", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    PreparedStatement insert, stock, Current, Bal, Balance;

    private void savePurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePurchaseActionPerformed
        // TODO add your handling code here:

        if (!ValidateForm.isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
                JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String input = PurchaseRegistration.SupplierID.getText().trim();
            Date input1 = this.ReleaseDate.getDate();
            boolean type = Purchase.isSelected();
            boolean type1 = Debtor.isSelected();
            JFrame frame = new JFrame();
            if ((type == false) && (type1 == false)) {
                JOptionPane.showMessageDialog(frame, "Please Select whether it is Purchase or Debt.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (input.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Customer ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (input1 == null) {
                JOptionPane.showMessageDialog(frame, "Please Enter Release Date.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                String PurchaseID = this.PurchaseID.getText();
                String SupplierID = this.SupplierID.getText();
                String SupplierName = this.SupplierName.getText();
                String PurchaseCode = this.PurchaseCode.getText();
                Date ReleaseDate = this.ReleaseDate.getDate();
                String Remark = this.Remark.getText();
                String TotalAmount = this.TotalAmount.getText();
                String VAT = this.VAT.getText();
                String SupplierReceipt = this.SupplierReceipt.getText();
                String SupplierSDCD = this.SupplierSDCD.getText();
                String Purchase = null;
                String Method = (String) this.Method.getSelectedItem();

                this.generateInvoiceCode();
                totalAmount();

                String Username = Login.Username.getText();

                if (this.Purchase.isSelected()) {
                    Purchase = "Purchase";
                } else if (this.Debtor.isSelected()) {
                    Purchase = "Debt";
                }

                java.sql.Date sqlReleaseDate = new java.sql.Date(ReleaseDate.getTime());

                String Status = "Waiting Approval";

                try {
                    Connection con = Connect.getConnection();
                    insert = con.prepareStatement("insert into purchase (SupplierID,SupplierName,ReleaseDate,SupplierReceipt,Remark,VAT,InvoiceID,TotalAmount,PurchaseCode,Type,createdBy,SupplierSDCD,Status,Method) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    insert.setString(1, SupplierID);
                    insert.setString(2, SupplierName);
                    insert.setDate(3, sqlReleaseDate);
                    insert.setString(4, SupplierReceipt);
                    insert.setString(5, Remark);
                    insert.setString(6, VAT);
                    insert.setString(7, PurchaseID);
                    insert.setString(8, TotalAmount);
                    insert.setString(9, PurchaseCode);
                    insert.setString(10, Purchase);
                    insert.setString(11, Username);
                    insert.setString(12, SupplierSDCD);
                    insert.setString(13, Status);
                    insert.setString(14, Method);
                    insert.executeUpdate();

                    String BankPaid = this.Method.getSelectedItem().toString();

                    if (BankPaid.contains("Bank")) {

                        insert = con.prepareStatement("insert into bank(Purpose,GivenBy,ReceivedBy,BOUT,Balance,Bank,TxnId) values (?,?,?,?,?,?,?)");
                        insert.setString(1, "Purchase");
                        insert.setString(2, SupplierName);
                        insert.setString(3, Username);
                        insert.setString(4, TotalAmount);
                        insert.setString(5, TotalAmount);
                        insert.setString(6, BankPaid);
                        insert.setString(7, PurchaseID);

                        insert.executeUpdate();

                        Bal = con.prepareStatement("select SUM(BIN) as BIN, SUM(BOUT) as BOUT from bank where bank=?");
                        Bal.setString(1, BankPaid);

                        ResultSet rs = Bal.executeQuery();
                        double IN, OUT, bal = 0;
                        if (rs.next()) {
                            IN = rs.getDouble("BIN");
                            OUT = rs.getDouble("BOUT");
                            bal = IN - OUT;
                        }

                        insert = con.prepareStatement("update bank set Balance=? where TxnId=?");
                        insert.setDouble(1, bal);
                        insert.setString(2, PurchaseID);

                        insert.executeUpdate();

                    }

                    JOptionPane.showMessageDialog(this, "New Purchase Recorded");

                    this.TotalAmount.setText("");
                    this.PurchaseID.setText("");
                    this.Remark.setText("");
                    this.ReleaseDate.setDate(null);
                    this.SupplierName.setText(null);
                    this.SupplierID.setText(null);
                    this.PurchaseCode.setText("");
                    this.Purchase.setText("Purchase");
                    this.SupplierSDCD.setText("");
                    this.SupplierReceipt.setText("");

                    this.dispose();
                    totalAmount();

                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_savePurchaseActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
    }//GEN-LAST:event_ExitActionPerformed

    private void PurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseActionPerformed
        // TODO add your handling code here:
        this.Debtor.setSelected(false);
        Purchase.setSelected(true);
        this.Method.setVisible(true);
        this.Method.setSelectedIndex(0);
    }//GEN-LAST:event_PurchaseActionPerformed

    private void SupplierIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplierIDActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmActionPerformed

    private void AddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemActionPerformed
        // TODO add your handling code here:

        if (!ValidateForm.isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
                JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            PreparedStatement insert;

            String input = PurchaseRegistration.ItemCode.getText().trim();
            String input1 = this.SalesQty.getText().trim();
            JFrame frame = new JFrame();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Item ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (input1.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Item Qty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                try {

                    int index = Tax.getSelectedIndex();

                    String Codes = ItemCode.getText();
                    String Name = ItemName.getText();
                    String taxType = Tax.getItemAt(index);
                    String UnitPrice = PurchaseRegistration.UnitPrice.getText();
                    String SalesPrice = PurchaseRegistration.SalesPrice.getText();
                    String SalesQty = this.SalesQty.getText();
                    String VAT_Item = this.VAT_Item.getText();
                    String TotalPrice = this.TotalPrice.getText();
                    String INV = generateInvoiceCode();

                    Connection con = Connect.getConnection();

                    insert = con.prepareStatement("INSERT INTO purchaseitem (ItemCode,ItemName,UnitPrice,PurchaseQty,PurchasePrice,TaxType,VAT,TotalPrice,RefPurchase) VALUES (?,?,?,?,?,?,?,?,?)");
                    stock = con.prepareStatement("Update stock set Purchase=Purchase+?,PurchasePrice=(((PurchasePrice*CurrentStock)+(?*?))/(CurrentStock+?)) where ItemCode=?");

                    insert.setString(1, Codes);
                    insert.setString(2, Name);
                    insert.setString(3, UnitPrice);
                    insert.setString(4, SalesQty);
                    insert.setString(5, SalesPrice);
                    insert.setString(6, taxType);
                    insert.setString(7, VAT_Item);
                    insert.setString(8, TotalPrice);
                    insert.setString(9, INV);

                    stock.setString(1, SalesQty);
                    stock.setString(2, UnitPrice);
                    stock.setString(3, SalesQty);
                    stock.setString(4, SalesQty);
                    stock.setString(5, Codes);

                    insert.executeUpdate();
                    stock.executeUpdate();

                    stock = con.prepareStatement("insert into stock(Action,StockIN,PurchasePrice,UnitPrice,ItemCode,ItemName,SubTotal) values(?,?,?,?,?,?,?)");

                    stock.setString(1, INV);
                    stock.setString(2, SalesQty);
                    stock.setString(3, SalesPrice);
                    stock.setString(4, UnitPrice);
                    stock.setString(5, Codes);
                    stock.setString(6, Name);
                    stock.setDouble(7, Double.parseDouble(SalesQty) * Double.parseDouble(SalesPrice));

                    stock.executeUpdate();

                    CurrentStock currentStock = new CurrentStock();
                    double current = currentStock.getCurrentStock(Codes)[0];

                    Current = con.prepareStatement("update stock set CurrentStock=? where ItemCode=?");
                    Current.setDouble(1, current);
                    Current.setString(2, Codes);

                    Current.executeUpdate();

                    Current = con.prepareStatement("update item set PurchaseUnit=(((PurchaseUnit*CurrentStock)+(?*?))/(CurrentStock+?)) where ItemCode=?");
                    Current.setString(1, UnitPrice);
                    Current.setString(2, SalesQty);
                    Current.setString(3, SalesQty);
                    Current.setString(4, Codes);

                    Current.executeUpdate();

                    double amount = currentStock.getCurrentStock(Codes)[1];

                    Current = con.prepareStatement("update stock set  StockAmount=(CurrentStock*PurchasePrice) where ItemCode=?");
                    Current.setString(1, Codes);

                    Current.executeUpdate();

                    Current = con.prepareStatement("update item set CurrentStock=? where ItemCode=?");
                    Current.setDouble(1, current);
                    Current.setString(2, Codes);

                    Current.executeUpdate();

                    Balance = con.prepareStatement("select Sum(StockIN) as StockIN, sum(StockOut) as StockOUT from stock where ItemCode=?");
                    Balance.setString(1, Codes);

                    ResultSet Bal = Balance.executeQuery();
                    double StockIN, StockOUT, Balan = 0;

                    if (Bal.next()) {
                        StockIN = Bal.getDouble("StockIN");
                        StockOUT = Bal.getDouble("StockOUT");
                        Balan = StockIN - StockOUT;
                    }

                    Balance = con.prepareStatement("update stock set Balance=? where ItemCode=? and Action=? and StockIN=?");
                    Balance.setDouble(1, Balan);
                    Balance.setString(2, Codes);
                    Balance.setString(3, INV);
                    Balance.setString(4, SalesQty);

                    Balance.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Item Recorded");

                    ItemCode.setText("");
                    ItemName.setText("");
                    Tax.getItemAt(0);
                    PurchaseRegistration.UnitPrice.setText("");
                    PurchaseRegistration.SalesPrice.setText("");
                    this.SalesQty.setText("");
                    this.VAT_Item.setText("");
                    this.TotalPrice.setText("");

                    table_update();
                    totalAmount();

                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_AddItemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        new ItemListP().setVisible(true);
        totalAmount();

        PreparedStatement select;

        try {

            String Code = ItemCode.getText();
            String Name = ItemName.getText();

            Connection con = Connect.getConnection();

            select = con.prepareStatement("SELECT  TaxType,PurchaseUnit,SalePrice FROM item WHERE ItemCode = ? AND ItemName= ?");
            select.setString(1, Code);
            select.setString(2, Name);

            ResultSet rs = select.executeQuery();

            // Check if the item with the given code exists
            if (rs.next()) {
                // Retrieve values from the ResultSet

                String taxType = rs.getString("TaxType");
                this.Tax.setSelectedItem(taxType);

                String UnitPrice = rs.getString("PurchaseUnit");
                this.UnitPrice.setText(UnitPrice);

                String SalesPrice = rs.getString("SalePrice");
                this.SalesPrice.setText(SalesPrice);

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:

        new CustomerListP().setVisible(true);
        totalAmount();
    }//GEN-LAST:event_SearchActionPerformed

    private void ItemNameInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ItemNameInputMethodTextChanged
        // TODO add your handling code here:

        PreparedStatement select;

        try {

            String Code = ItemCode.getText();
            String Name = ItemName.getText();

            Connection con = Connect.getConnection();

            select = con.prepareStatement("SELECT  TaxType,PurchaseUnit,SalePrice FROM item WHERE ItemCode = ? AND ItemName= ?");
            select.setString(1, Code);
            select.setString(2, Name);

            ResultSet rs = select.executeQuery();

            // Check if the item with the given code exists
            if (rs.next()) {
                // Retrieve values from the ResultSet

                String taxType = rs.getString("TaxType");
                this.Tax.setSelectedItem(taxType);

                String UnitPrice = rs.getString("PurchaseUnit");
                this.UnitPrice.setText(UnitPrice);

                String SalesPrice = rs.getString("SalePrice");
                this.SalesPrice.setText(SalesPrice);

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ItemNameInputMethodTextChanged

    private void SupplierSDCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierSDCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplierSDCDActionPerformed

    private void VATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VATActionPerformed

    private void SalesQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalesQtyKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_SalesQtyKeyTyped

    private void SalesQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalesQtyKeyReleased
        // TODO add your handling code here:

        int Qty = Integer.parseInt(this.SalesQty.getText());
        double UnitPrice = Double.parseDouble(PurchaseRegistration.UnitPrice.getText());
        double total;
        total = Qty * UnitPrice;

        String type = "B-18%";
        if (Tax.getSelectedItem().equals(type)) {
            double vat = (double) (total * 0.18);
            DecimalFormat df = new DecimalFormat("#.##");
            VAT_Item.setText(df.format(vat));
        }

        DecimalFormat dfTotal = new DecimalFormat("#.##");
        this.TotalPrice.setText(dfTotal.format(total));
    }//GEN-LAST:event_SalesQtyKeyReleased

    private void SalesQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesQtyActionPerformed
        // TODO add your handling code here:

        int Qty = Integer.parseInt(this.SalesQty.getText());
        double UnitPrice = Double.parseDouble(PurchaseRegistration.UnitPrice.getText());
        double total;
        total = Qty * UnitPrice;

        String type = "B-18%";
        if (Tax.getSelectedItem().equals(type)) {
            double vat = (double) (total * 0.18);
            DecimalFormat df = new DecimalFormat("#.##");
            VAT_Item.setText(df.format(vat));
        }

        DecimalFormat dfTotal = new DecimalFormat("#.##");
        this.TotalPrice.setText(dfTotal.format(total));

    }//GEN-LAST:event_SalesQtyActionPerformed

    private void SupplierReceiptKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SupplierReceiptKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_SupplierReceiptKeyTyped

    private void RemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveItemActionPerformed
        // TODO add your handling code here:

        if (!ValidateForm.isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
                JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DefaultTableModel Df = (DefaultTableModel) jTable2.getModel();
            int selectedIndex = jTable2.getSelectedRow();

            try {

                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete this item", "Warning", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {

                    String id = (Df.getValueAt(selectedIndex, 0).toString());
                    String Qty = (Df.getValueAt(selectedIndex, 3).toString());
                    String Prch = (Df.getValueAt(selectedIndex, 2).toString());
                    Connection con = Connect.getConnection();

                    insert = con.prepareStatement("delete from purchaseitem where ItemCode=? and PurchaseQty=?");
                    insert.setString(1, id);
                    insert.setString(2, Qty);

                    stock = con.prepareStatement("update stock set Purchase=Purchase-? where ItemCode=?");

                    stock.setString(1, Qty);
                    stock.setString(2, id);

                    stock.executeUpdate();

                    stock = con.prepareStatement("update stock set PurchasePrice=(((PurchasePrice*(CurrentStock))-(?*?))/(CurrentStock-?)) where ItemCode=?");
                    stock.setString(1, Qty);
                    stock.setString(2, Prch);
                    stock.setString(3, Qty);
                    stock.setString(4, id);

                    stock.executeUpdate();
                    insert.executeUpdate();

                    CurrentStock currentStock = new CurrentStock();
                    double current = currentStock.getCurrentStock(id)[0];

                    Current = con.prepareStatement("update stock set CurrentStock=? where ItemCode=?");
                    Current.setDouble(1, current);
                    Current.setString(2, id);

                    Current.executeUpdate();

                    Current = con.prepareStatement("update item set PurchaseUnit=(((PurchaseUnit*(CurrentStock))-(?*?))/(CurrentStock-?)) where ItemCode=?");
                    Current.setString(1, Qty);
                    Current.setString(2, Prch);
                    Current.setString(3, Qty);
                    Current.setString(4, id);

                    Current.executeUpdate();

                    double amount = currentStock.getCurrentStock(id)[1];

                    Current = con.prepareStatement("update stock set  StockAmount=(CurrentStock*PurchasePrice) where ItemCode=?");
                    Current.setString(1, id);

                    Current.executeUpdate();

                    Current = con.prepareStatement("update item set CurrentStock=? where ItemCode=?");
                    Current.setDouble(1, current);
                    Current.setString(2, id);

                    Current.executeUpdate();

                    Balance = con.prepareStatement("delete from stock where ItemCode=? and Action=? and StockIN=?");
                    Balance.setString(1, id);
                    Balance.setString(2, PurchaseID.getText());
                    Balance.setString(3, Qty);

                    Balance.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Item Deleted Successfully!");
                    table_update();
                    totalAmount();

                }

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_RemoveItemActionPerformed

    private void DeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllActionPerformed
        // TODO add your handling code here:

        if (!ValidateForm.isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
                JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {

                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete all items", "Warning", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {

                    Connection con = Connect.getConnection();

                    insert = con.prepareStatement("delete from purchaseitem where RefPurchase=?");
                    insert.setString(1, generateInvoiceCode());

                    PreparedStatement del = con.prepareStatement("select * from Purchaseitem where RefPurchase=?");
                    del.setString(1, generateInvoiceCode());

                    ResultSet rs = del.executeQuery();

                    while (rs.next()) {

                        String id = rs.getString("ItemCode");
                        String Qty = rs.getString("PurchaseQty");
                        String Prc = rs.getString("UnitPrice");

                        stock = con.prepareStatement("update stock set Purchase=Purchase-?,PurchasePrice=(((PurchasePrice*(CurrentStock))-(?*?))/(CurrentStock-?)) where ItemCode=?");

                        stock.setString(1, Qty);
                        stock.setString(2, Qty);
                        stock.setString(3, Prc);
                        stock.setString(4, Qty);
                        stock.setString(5, id);

                        stock.executeUpdate();
                        insert.executeUpdate();

                        CurrentStock currentStock = new CurrentStock();
                        double current = currentStock.getCurrentStock(id)[0];

                        Current = con.prepareStatement("update stock set CurrentStock=? where ItemCode=?");
                        Current.setDouble(1, current);
                        Current.setString(2, id);

                        Current.executeUpdate();

                        Current = con.prepareStatement("update item set PurchaseUnit=(((PurchaseUnit*(CurrentStock))-(?*?))/(CurrentStock-?)) where ItemCode=?");
                        Current.setString(1, Qty);
                        Current.setString(2, Prc);
                        Current.setString(3, Qty);
                        Current.setString(4, id);

                        Current.executeUpdate();

                        double amount = currentStock.getCurrentStock(id)[1];

                        Current = con.prepareStatement("update stock set  StockAmount=(CurrentStock*PurchasePrice) where ItemCode=?");
                        Current.setString(1, id);

                        Current.executeUpdate();

                        Current = con.prepareStatement("update item set CurrentStock=? where ItemCode=?");
                        Current.setDouble(1, current);
                        Current.setString(2, id);

                        Current.executeUpdate();

                        Balance = con.prepareStatement("delete from stock where Action=?");
                        Balance.setString(1, PurchaseID.getText());

                        Balance.executeUpdate();

                    }

                    insert.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Items Deleted Successfully!");
                    table_update();
                    totalAmount();

                }

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_DeleteAllActionPerformed

    private void DebtorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebtorActionPerformed
        // TODO add your handling code here:

        this.Purchase.setSelected(false);
        this.Debtor.setSelected(true);
        this.Method.setVisible(false);
        this.Method.setSelectedItem(null);
    }//GEN-LAST:event_DebtorActionPerformed

    private void UnitPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UnitPriceKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }

        if (c == '.' && ((JTextField) evt.getSource()).getText().contains(".")) {
            evt.consume();
        }

    }//GEN-LAST:event_UnitPriceKeyTyped

    private void UnitPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UnitPriceKeyReleased
        // TODO add your handling code here:

        int Qty = Integer.parseInt(this.SalesQty.getText());
        double UnitPrice = Double.parseDouble(PurchaseRegistration.UnitPrice.getText());
        double total;
        total = Qty * UnitPrice;

        String type = "B-18%";
        if (Tax.getSelectedItem().equals(type)) {
            double vat = (double) (total * 0.18);
            DecimalFormat df = new DecimalFormat("#.##");
            VAT_Item.setText(df.format(vat));
        }

        DecimalFormat dfTotal = new DecimalFormat("#.##");
        this.TotalPrice.setText(dfTotal.format(total));

    }//GEN-LAST:event_UnitPriceKeyReleased

    private void MethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MethodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MethodActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItem;
    private javax.swing.JButton Confirm;
    private javax.swing.JRadioButton Debtor;
    private javax.swing.JButton DeleteAll;
    private javax.swing.JButton Exit;
    public static javax.swing.JTextField ItemCode;
    public static javax.swing.JTextField ItemName;
    private javax.swing.JComboBox<String> Method;
    private javax.swing.JRadioButton Purchase;
    private javax.swing.JTextField PurchaseCode;
    private javax.swing.JTextField PurchaseID;
    private javax.swing.JTextField PurchaseReason;
    private com.toedter.calendar.JDateChooser ReleaseDate;
    private javax.swing.JTextField Remark;
    private javax.swing.JButton RemoveItem;
    public static javax.swing.JTextField SalesPrice;
    private javax.swing.JTextField SalesQty;
    private javax.swing.JButton Search;
    public static javax.swing.JTextField SupplierID;
    public static javax.swing.JTextField SupplierName;
    private javax.swing.JTextField SupplierReceipt;
    private javax.swing.JTextField SupplierSDCD;
    public static javax.swing.JComboBox<String> Tax;
    private javax.swing.JTextField TotalAmount;
    private javax.swing.JTextField TotalPrice;
    public static javax.swing.JTextField UnitPrice;
    private javax.swing.JTextField VAT;
    private javax.swing.JTextField VAT_Item;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton savePurchase;
    // End of variables declaration//GEN-END:variables
}
