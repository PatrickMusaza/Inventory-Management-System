
import java.sql.ResultSetMetaData;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Patrick
 */
public class SalesTransaction extends javax.swing.JFrame {

    /**
     * Creates new form SalesTransaction
     */
    public SalesTransaction() {
        initComponents();

        Image logo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
        this.setIconImage(logo);

        this.SaleDate.setDate(new Date());
        this.ReleaseDate.setDate(new Date());
        SaleDate.setEnabled(false);

        generateInvoiceCode();
        table_update();
        PaymentMethod();

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

    public static void setCustomerDetails(String code, String name) {
        SalesTransaction.CustomerID.setText(code);
        SalesTransaction.CustomerName.setText(name);
    }

    public static void setItemDetails(String code, String name) {
        SalesTransaction.ItemCode.setText(code);
        SalesTransaction.ItemName.setText(name);
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
                Tax.setSelectedItem(taxType);

                String UnitPrice = rs.getString("SalePrice");
                SalesTransaction.UnitPrice.setText(UnitPrice);

                String SalesPrice = rs.getString("PurchaseUnit");
                SalesTransaction.SalesPrice.setText(SalesPrice);

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public String generateInvoiceCode() {
        String Code = "";
        try {
            Connection con = Connect.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(CreatedAt) FROM sales");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int count = result.getInt(1);
                Code = "INV " + (1000 + count + 1);
                this.InvoiceID.setText(Code);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return Code;
    }

    private void table_update() {

        int count;

        try {
            Connection con = Connect.getConnection();
            insert = con.prepareStatement("select * from salesitem where RefSale=?");
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
                    v2.add(rs.getString("SalesQty"));
                    v2.add(rs.getString("TotalPrice"));

                }

                Df.addRow(v2);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private void totalAmount() {
        try {
            Connection con = Connect.getConnection();

            PreparedStatement select = con.prepareStatement("SELECT SUM(TotalPrice) as Total, SUM(VAT) as Vat FROM salesItem WHERE RefSale = ?");
            select.setString(1, generateInvoiceCode());

            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("Total");
                double vat = rs.getDouble("Vat");

                DecimalFormat dfTotal = new DecimalFormat("#.##");
                this.TotalAmount.setText(dfTotal.format(total));
                DecimalFormat dfVAT = new DecimalFormat("#.##");
                this.VAT.setText(dfVAT.format(vat));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesTransaction.class.getName()).log(Level.SEVERE, null, ex);
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
        saveSale = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        printReceipt = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Sale = new javax.swing.JRadioButton();
        CustomerID = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        SaleDate = new com.toedter.calendar.JDateChooser();
        TotalAmount = new javax.swing.JTextField();
        Remark = new javax.swing.JTextField();
        PurchaseCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ReleaseDate = new com.toedter.calendar.JDateChooser();
        CustomerName = new javax.swing.JTextField();
        VAT = new javax.swing.JTextField();
        InvoiceID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        Method = new javax.swing.JComboBox<>();
        Credit = new javax.swing.JRadioButton();
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
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        UnitPrice = new javax.swing.JTextField();
        SalesQty = new javax.swing.JTextField();
        SalesPrice = new javax.swing.JTextField();
        VAT_Item = new javax.swing.JTextField();
        TotalPrice = new javax.swing.JTextField();
        DRatio = new javax.swing.JTextField();
        DAmount = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
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

        setTitle("Sales Transaction");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setText("Sales Registration");

        saveSale.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        saveSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/floppy-disk.png"))); // NOI18N
        saveSale.setText("Save");
        saveSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSaleActionPerformed(evt);
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

        printReceipt.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        printReceipt.setForeground(new java.awt.Color(0, 153, 51));
        printReceipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/printer.png"))); // NOI18N
        printReceipt.setText("Print Receipt");
        printReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptActionPerformed(evt);
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
                .addComponent(saveSale)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printReceipt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Exit)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(printReceipt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(saveSale)
                        .addComponent(Exit)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setText("Sale Type");

        jLabel3.setText("Customer ID");

        jLabel4.setText("Sale Date");

        jLabel5.setText("Total Amount");

        jLabel7.setText("Purchase Code");

        jLabel8.setText("Remark");

        Sale.setText("Sale");
        Sale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaleActionPerformed(evt);
            }
        });

        CustomerID.setEditable(false);
        CustomerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerIDActionPerformed(evt);
            }
        });

        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe.png"))); // NOI18N
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        SaleDate.setOpaque(false);

        TotalAmount.setEditable(false);

        jLabel6.setText("Customer Name");

        jLabel9.setText("Invoice ID");

        jLabel10.setText("Release Date");

        jLabel11.setText("VAT");

        CustomerName.setEditable(false);

        VAT.setEditable(false);

        InvoiceID.setEditable(false);

        jLabel25.setText("Method of Payment");

        Method.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Method.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Bank", "Mobile Money" }));

        Credit.setText("Credit");
        Credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Remark)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(PurchaseCode, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TotalAmount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SaleDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(CustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(Sale)
                                    .addGap(18, 18, 18)
                                    .addComponent(Credit))))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6)
                            .addComponent(jLabel25))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Method, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InvoiceID)
                            .addComponent(ReleaseDate, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(VAT)
                            .addComponent(CustomerName, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                            .addComponent(Sale)
                            .addComponent(Credit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(CustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(SaleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))))
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(InvoiceID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(CustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ReleaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(VAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PurchaseCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel25)
                    .addComponent(Method, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Remark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel12.setText("Sales Item Information");

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

        jLabel15.setText("Unit Price");

        jLabel16.setText("Sales Qty");

        jLabel17.setText("Prch Price");

        jLabel18.setText("Tax Type");

        jLabel19.setText("VAT");

        jLabel20.setText("Total Price");

        jLabel21.setText("D/C Ratio");

        jLabel22.setText("D/C Amount");

        UnitPrice.setEditable(false);

        SalesQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesQtyActionPerformed(evt);
            }
        });
        SalesQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SalesQtyKeyPressed(evt);
            }
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

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/percentage.png"))); // NOI18N

        Tax.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A-EX", "B-18%", "C" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(SalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SalesPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VAT_Item)
                            .addComponent(TotalPrice)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(Tax, 0, 124, Short.MAX_VALUE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DRatio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(VAT_Item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(Tax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(SalesQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(RemoveItem)
                    .addComponent(DeleteAll))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Unit Price", "Sales Qty", "Total Amount"
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    PreparedStatement insert, stock, Current, Bal;

    private void saveSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSaleActionPerformed
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
            String input = SalesTransaction.CustomerID.getText().trim();
            Date input1 = this.ReleaseDate.getDate();
            boolean type = Credit.isSelected();
            boolean type1 = Sale.isSelected();
            JFrame frame = new JFrame();

            if ((type == false) && (type1 == false)) {
                JOptionPane.showMessageDialog(frame, "Please Select whether it is Sale or Credit.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (input.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Customer ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (input1 == null) {
                JOptionPane.showMessageDialog(frame, "Please Enter Release Date.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                this.totalAmount();

                String InvoiceID = this.InvoiceID.getText();
                String CustomerID = this.CustomerID.getText();
                String CustomerName = this.CustomerName.getText();
                String PurchaseCode = this.PurchaseCode.getText();
                Date ReleaseDate = this.ReleaseDate.getDate();
                String Remark = this.Remark.getText();
                Date SaleDate = this.SaleDate.getDate();
                String TotalAmount = this.TotalAmount.getText();
                String VAT = this.VAT.getText();
                String Sale = null;
                String Method = (String) this.Method.getSelectedItem();

                this.generateInvoiceCode();

                String Username = Login.Username.getText();
                String SOUT = null;

                java.sql.Date sqlReleaseDate = new java.sql.Date(ReleaseDate.getTime());
                java.sql.Date sqlSaleDate = new java.sql.Date(SaleDate.getTime());

                String Status = "Waiting Approval";

                this.totalAmount();
                if (this.Sale.isSelected()) {
                    Sale = "Sale";
                } else if (this.Credit.isSelected()) {
                    Sale = "Credit";
                    Method = null;
                    SOUT = TotalAmount;
                }
                try {
                    Connection con = Connect.getConnection();

                    insert = con.prepareStatement("insert into sales (CustomerID,CustomerName,ReleaseDate,SaleDate,Remark,VAT,InvoiceID,TotalAmount,PurchaseCode,Type,createdBy, Status,Method,SOUT,Balance) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    insert.setString(1, CustomerID);
                    insert.setString(2, CustomerName);
                    insert.setDate(3, sqlReleaseDate);
                    insert.setDate(4, sqlSaleDate);
                    insert.setString(5, Remark);
                    insert.setString(6, VAT);
                    insert.setString(7, InvoiceID);
                    insert.setString(8, TotalAmount);
                    insert.setString(9, PurchaseCode);
                    insert.setString(10, Sale);
                    insert.setString(11, Username);
                    insert.setString(12, Status);
                    insert.setString(13, Method);
                    insert.setString(14, SOUT);
                    insert.setString(15, TotalAmount);

                    insert.executeUpdate();
                    String BankPaid=null;
                    if (this.Method.getSelectedItem() != null) {
                        BankPaid = this.Method.getSelectedItem().toString();
                    }

                    if (this.Credit.isSelected()) {
                        PreparedStatement selectSOUT = con.prepareStatement("SELECT SUM(SOUT) AS TotalSOUT,SUM(SIN) AS TotalSIN FROM sales WHERE CustomerID = ?");
                        selectSOUT.setString(1, CustomerID);
                        ResultSet rsSOUT = selectSOUT.executeQuery();
                        double soutTotal = 0, sinTotal = 0;
                        double balance = 0;
                        if (rsSOUT.next()) {
                            soutTotal = rsSOUT.getDouble("TotalSOUT");
                            sinTotal = rsSOUT.getDouble("TotalSIN");
                            balance = soutTotal - sinTotal;

                            PreparedStatement updateBalance = con.prepareStatement("UPDATE sales SET TotalAmount = ? WHERE InvoiceID = ?");
                            updateBalance.setDouble(1, balance);
                            updateBalance.setString(2, InvoiceID);
                            updateBalance.executeUpdate();
                        }
                    } else if (BankPaid.contains("Bank")) {

                        insert = con.prepareStatement("insert into bank(Purpose,GivenBy,ReceivedBy,BIN,Balance,Bank,TxnId) values (?,?,?,?,?,?,?)");
                        insert.setString(1, "Sale");
                        insert.setString(2, CustomerName);
                        insert.setString(3, Username);
                        insert.setString(4, TotalAmount);
                        insert.setString(5, TotalAmount);
                        insert.setString(6, BankPaid);
                        insert.setString(7, InvoiceID);

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
                        insert.setString(2, InvoiceID);

                        insert.executeUpdate();

                    }

                    JOptionPane.showMessageDialog(this, "New Sale Recorded");

                    this.TotalAmount.setText("");
                    this.InvoiceID.setText("");
                    this.Remark.setText("");
                    this.SaleDate.setDate(null);
                    this.ReleaseDate.setDate(null);
                    this.CustomerName.setText(null);
                    this.CustomerID.setText(null);
                    this.PurchaseCode.setText("");
                    this.Sale.setText("Sale");

                    SaleM sale = new SaleM();
                    sale.table_updates();
                    this.dispose();

                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_saveSaleActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
    }//GEN-LAST:event_ExitActionPerformed

    private void printReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceiptActionPerformed
        // TODO add your handling code here:

        MessageFormat header = new MessageFormat("Sales Receipt");

        MessageFormat footer = new MessageFormat("Page {0,number,integer}" + "         " + this.SaleDate.getDate());

        try {

            jTable2.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (java.awt.print.PrinterException ex) {
            System.err.format("Can't Print", ex.getMessage());
        }
    }//GEN-LAST:event_printReceiptActionPerformed

    private void SaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaleActionPerformed
        // TODO add your handling code here:
        this.Credit.setSelected(false);
        this.Sale.setSelected(true);
        this.Method.setVisible(true);
        this.Method.setSelectedIndex(0);
    }//GEN-LAST:event_SaleActionPerformed

    private void CustomerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustomerIDActionPerformed

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
            String input = SalesTransaction.ItemCode.getText().trim();
            String input1 = this.SalesQty.getText().trim();
            JFrame frame = new JFrame();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Item ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (input1.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Item Quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                PreparedStatement insert;
                int a = 1;
                try {
                    Connection con = Connect.getConnection();

                    String value, code;
                    value = this.SalesQty.getText();
                    code = SalesTransaction.ItemCode.getText();

                    Current = con.prepareStatement("select CurrentStock from stock where ItemCode=?");
                    Current.setString(1, code);

                    ResultSet rs = Current.executeQuery();
                    if (rs.next()) {
                        int cur = rs.getInt("CurrentStock");
                        int qty = Integer.parseInt(value);

                        if (qty > cur) {
                            JOptionPane.showMessageDialog(null, "The value you inserted is larger than the current stock=" + cur, "Irrelevant Stock Quantity", JOptionPane.ERROR_MESSAGE);
                            this.SalesQty.setText("");
                            a = 0;
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(SalesTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (a == 1) {

                    try {

                        int index = Tax.getSelectedIndex();

                        String Codes = ItemCode.getText();
                        String Name = ItemName.getText();
                        String taxType = Tax.getItemAt(index);
                        String UnitPrice = SalesTransaction.UnitPrice.getText();
                        String SalesPrice = SalesTransaction.SalesPrice.getText();
                        String SalesQty = this.SalesQty.getText();
                        String VAT_Item = this.VAT_Item.getText();
                        String TotalPrice = this.TotalPrice.getText();
                        String DRatio = this.DRatio.getText();
                        String DAmount = this.DAmount.getText();
                        String INV = generateInvoiceCode();

                        Connection con = Connect.getConnection();

                        insert = con.prepareStatement("INSERT INTO salesitem (ItemCode,ItemName,UnitPrice,SalesQty,SalesPrice,TaxType,VAT,TotalPrice,DRatio,DAmount,RefSale) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                        stock = con.prepareStatement("Update stock set Sales=Sales+? where ItemCode=?");

                        insert.setString(1, Codes);
                        insert.setString(2, Name);
                        insert.setString(3, UnitPrice);
                        insert.setString(4, SalesQty);
                        insert.setString(5, SalesPrice);
                        insert.setString(6, taxType);
                        insert.setString(7, VAT_Item);
                        insert.setString(8, TotalPrice);
                        insert.setString(9, DRatio);
                        insert.setString(10, DAmount);
                        insert.setString(11, INV);

                        stock.setString(1, SalesQty);
                        stock.setString(2, Codes);

                        insert.executeUpdate();
                        stock.executeUpdate();

                        CurrentStock currentStock = new CurrentStock();
                        double current = currentStock.getCurrentStock(Codes)[0];
                        double amount = currentStock.getCurrentStock(Codes)[1];

                        Current = con.prepareStatement("update stock set CurrentStock=?, StockAmount=? where ItemCode=?");
                        Current.setDouble(1, current);
                        Current.setDouble(2, amount);
                        Current.setString(3, Codes);

                        Current.executeUpdate();

                        Current = con.prepareStatement("update item set CurrentStock=? where ItemCode=?");
                        Current.setDouble(1, current);
                        Current.setString(2, Codes);

                        Current.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Item Recorded");

                        ItemCode.setText("");
                        ItemName.setText("");
                        Tax.getItemAt(0);
                        SalesTransaction.UnitPrice.setText("");
                        SalesTransaction.SalesPrice.setText("");
                        this.SalesQty.setText("");
                        this.VAT_Item.setText("");
                        this.TotalPrice.setText("");
                        this.DRatio.setText("");
                        this.DAmount.setText("");

                        table_update();
                        totalAmount();

                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_AddItemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        new ItemList().setVisible(true);
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
                Tax.setEnabled(false);

                String UnitPrice = rs.getString("SalePrice");
                this.UnitPrice.setText(UnitPrice);

                String SalesPrice = rs.getString("PurchaseUnit");
                this.SalesPrice.setText(SalesPrice);

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:

        new CustomerList().setVisible(true);
        totalAmount();
    }//GEN-LAST:event_SearchActionPerformed

    private void SalesQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalesQtyActionPerformed
        // TODO add your handling code here:

        int Qty = Integer.parseInt(this.SalesQty.getText());
        double UnitPrice = Double.parseDouble(this.UnitPrice.getText());
        double total;
        total = Qty * UnitPrice;

        this.TotalPrice.setText(String.valueOf(total));
    }//GEN-LAST:event_SalesQtyActionPerformed

    private void SalesQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalesQtyKeyReleased
        // TODO add your handling code here:

        int Qty = Integer.parseInt(this.SalesQty.getText());
        double UnitPrice = Double.parseDouble(SalesTransaction.UnitPrice.getText());
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

                String UnitPrice = rs.getString("SalePrice");
                this.UnitPrice.setText(UnitPrice);

                String SalesPrice = rs.getString("PurchaseUnit");
                this.SalesPrice.setText(SalesPrice);

            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ItemNameInputMethodTextChanged

    private void SalesQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalesQtyKeyTyped
        try {
            Connection con = Connect.getConnection();

            char c = evt.getKeyChar();

            if (!Character.isDigit(c)) {
                evt.consume();
                String value, code;
                value = this.SalesQty.getText();
                code = SalesTransaction.ItemCode.getText();

                Current = con.prepareStatement("select CurrentStock from stock where ItemCode=?");
                Current.setString(1, code);

                ResultSet rs = Current.executeQuery();
                if (rs.next()) {
                    int cur = rs.getInt("CurrentStock");
                    int qty = Integer.parseInt(value);

                    if (qty > cur) {
                        JOptionPane.showMessageDialog(null, "The value you inserted is larger than the current stock=" + cur, "Irrelevant Stock Quantity", JOptionPane.ERROR_MESSAGE);
                        this.SalesQty.setText("");
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SalesQtyKeyTyped

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

                    insert = con.prepareStatement("delete from salesitem where RefSale=?");
                    insert.setString(1, generateInvoiceCode());

                    PreparedStatement del = con.prepareStatement("select * from salesitem where RefSale=?");
                    del.setString(1, generateInvoiceCode());

                    ResultSet rs = del.executeQuery();

                    while (rs.next()) {

                        String id = rs.getString("ItemCode");
                        String Qty = rs.getString("SalesQty");

                        stock = con.prepareStatement("update stock set Sales=Sales-? where ItemCode=?");
                        stock.setString(1, Qty);
                        stock.setString(2, id);

                        stock.executeUpdate();
                        insert.executeUpdate();

                        CurrentStock currentStock = new CurrentStock();
                        double current = currentStock.getCurrentStock(id)[0];
                        double amount = currentStock.getCurrentStock(id)[1];

                        Current = con.prepareStatement("update stock set CurrentStock=?, StockAmount=? where ItemCode=?");
                        Current.setDouble(1, current);
                        Current.setDouble(2, amount);
                        Current.setString(3, id);

                        Current.executeUpdate();

                        Current = con.prepareStatement("update item set CurrentStock=? where ItemCode=?");
                        Current.setDouble(1, current);
                        Current.setString(2, id);

                        Current.executeUpdate();

                    }

                    insert.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Item Deleted Successfully!");
                    table_update();
                    totalAmount();

                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_DeleteAllActionPerformed

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
                    Connection con = Connect.getConnection();

                    insert = con.prepareStatement("delete from salesitem where ItemCode=? and SalesQty=?");
                    stock = con.prepareStatement("update stock set Sales=Sales-? where ItemCode=?");

                    insert.setString(1, id);
                    insert.setString(2, Qty);

                    stock.setString(1, Qty);
                    stock.setString(2, id);

                    stock.executeUpdate();
                    insert.executeUpdate();

                    CurrentStock currentStock = new CurrentStock();
                    double current = currentStock.getCurrentStock(id)[0];
                    double amount = currentStock.getCurrentStock(id)[1];

                    Current = con.prepareStatement("update stock set CurrentStock=?, StockAmount=? where ItemCode=?");
                    Current.setDouble(1, current);
                    Current.setDouble(2, amount);
                    Current.setString(3, id);

                    Current.executeUpdate();

                    Current = con.prepareStatement("update item set CurrentStock=? where ItemCode=?");
                    Current.setDouble(1, current);
                    Current.setString(2, id);

                    Current.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Item Deleted Successfully!");
                    table_update();
                    totalAmount();

                }

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(PurchaseRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_RemoveItemActionPerformed

    private void SalesQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalesQtyKeyPressed
        // TODO add your handling code here:
        try {
            Connection con = Connect.getConnection();

            String value, code;
            value = this.SalesQty.getText();
            code = ItemCode.getText();

            Current = con.prepareStatement("select CurrentStock from stock where ItemCode=?");
            Current.setString(1, code);

            ResultSet rs = Current.executeQuery();
            if (rs.next()) {
                int cur = rs.getInt("CurrentStock");
                int qty = Integer.parseInt(value);

                if (qty > cur) {
                    JOptionPane.showMessageDialog(null, "The value you inserted is larger than the current stock=" + cur, "Irrelevant Stock Quantity", JOptionPane.ERROR_MESSAGE);
                    this.SalesQty.setText("");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(SalesTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SalesQtyKeyPressed

    private void CreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditActionPerformed
        // TODO add your handling code here:
        this.Sale.setSelected(false);
        this.Credit.setSelected(true);
        this.Method.setSelectedItem(null);
        this.Method.setVisible(false);
    }//GEN-LAST:event_CreditActionPerformed

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
            java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesTransaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesTransaction().setVisible(true);
                SaleM sale = new SaleM();
                sale.table_updates();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItem;
    private javax.swing.JButton Confirm;
    private javax.swing.JRadioButton Credit;
    public static javax.swing.JTextField CustomerID;
    public static javax.swing.JTextField CustomerName;
    private javax.swing.JTextField DAmount;
    private javax.swing.JTextField DRatio;
    private javax.swing.JButton DeleteAll;
    private javax.swing.JButton Exit;
    private javax.swing.JTextField InvoiceID;
    public static javax.swing.JTextField ItemCode;
    public static javax.swing.JTextField ItemName;
    private javax.swing.JComboBox<String> Method;
    private javax.swing.JTextField PurchaseCode;
    private com.toedter.calendar.JDateChooser ReleaseDate;
    private javax.swing.JTextField Remark;
    private javax.swing.JButton RemoveItem;
    private javax.swing.JRadioButton Sale;
    private com.toedter.calendar.JDateChooser SaleDate;
    public static javax.swing.JTextField SalesPrice;
    private javax.swing.JTextField SalesQty;
    private javax.swing.JButton Search;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JButton printReceipt;
    private javax.swing.JButton saveSale;
    // End of variables declaration//GEN-END:variables
}
