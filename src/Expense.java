
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Patrick
 */
public class Expense extends javax.swing.JPanel {

    private boolean isNew = false;

    /**
     * Creates new form Expense
     */
    public Expense() {
        initComponents();

        this.Name.setEditable(false);
        this.Description.setEditable(false);
        this.Ref.setEditable(false);
        this.Amount.setEditable(false);
        this.Source.setSelectedItem("");

        table();

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

            this.Source.setModel(new DefaultComboBoxModel<>(methods.toArray(new String[0])));

        } catch (SQLException ex) {
            Logger.getLogger(Expense.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void table() {
        try {
            Connection con = Connect.getConnection();
            insert = con.prepareStatement("Select * from expense");

            ResultSet rs = insert.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            int count = rsmd.getColumnCount();

            DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
            Df.setRowCount(0);

            while (rs.next()) {
                Vector v1 = new Vector();
                NumberFormat formatter = NumberFormat.getInstance();

                for (int i = 1; i <= count; i++) {
                    v1.add(rs.getString("ID"));
                    v1.add(rs.getString("Name"));
                    v1.add(rs.getString("Description"));
                    v1.add(formatter.format(rs.getDouble("Amount")));
                    v1.add(rs.getString("Source"));
                    v1.add(rs.getString("Ref"));
                    v1.add(rs.getDate("createdAt"));
                }

                Df.addRow(v1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Expense.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String
            generateExpenseCode() {
        String Code = "";
        try {
            Connection con = Connect.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(ID) FROM Expense");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int count = result.getInt(1);
                Code = "EXP " + (count + 1);
                this.ID.setText(Code);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Expense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return Code;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addNew = new javax.swing.JButton();
        saveItem = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Source = new javax.swing.JComboBox<>();
        Ref = new javax.swing.JTextField();
        Name = new java.awt.TextField();
        ID = new java.awt.TextField();
        jLabel7 = new javax.swing.JLabel();
        Description = new java.awt.TextField();
        jLabel9 = new javax.swing.JLabel();
        Amount = new java.awt.TextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        srcCode = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        srcName = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Operating Expense Management");

        addNew.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        addNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/credit-card-add.png"))); // NOI18N
        addNew.setText("New");
        addNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNewMouseClicked(evt);
            }
        });
        addNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewActionPerformed(evt);
            }
        });

        saveItem.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        saveItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/floppy-disk.png"))); // NOI18N
        saveItem.setText("Save");
        saveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });

        Clear.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close.png"))); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Clear)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addNew)
                    .addComponent(saveItem)
                    .addComponent(Clear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(136, 131, 131)));

        jLabel5.setText("ID*");

        jLabel6.setText("Name*");

        jLabel10.setText("Source");

        jLabel11.setText("Ref");

        ID.setEditable(false);
        ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IDKeyTyped(evt);
            }
        });

        jLabel7.setText("<html> <p>Description</br> <span><center>(Purpose)</center></span></p> </html>");

        jLabel9.setText("Amount*");

        Amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AmountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                            .addComponent(ID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Amount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Source, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(25, 25, 25)
                                .addComponent(Ref))))
                    .addComponent(Description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel5)
                        .addComponent(Source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(Ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 60, 60)));

        jLabel19.setText("ID");

        srcCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srcCodeKeyReleased(evt);
            }
        });

        jLabel20.setText("Expense Name");

        srcName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srcNameKeyReleased(evt);
            }
        });

        Search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe.png"))); // NOI18N
        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srcCode, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srcName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Search)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(srcName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srcCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Description", "Amount", "Source", "Ref", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1166, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 497, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    PreparedStatement insert,Bal;

    private void addNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNewMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addNewMouseClicked

    private void addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
        // TODO add your handling code here:

        this.Name.setText("");
        this.Description.setText("");
        this.Ref.setText("");
        this.Amount.setText("");

        this.Name.setEditable(true);
        this.Description.setEditable(true);
        this.Ref.setEditable(true);
        this.Amount.setEditable(true);

        table();
        PaymentMethod();
        generateExpenseCode();

        isNew = true;
    }//GEN-LAST:event_addNewActionPerformed

    private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
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
            int selectedRow = jTable1.getSelectedRow();
            String Name = this.Name.getText();
            String Description = this.Description.getText();
            String Ref = this.Ref.getText();
            String Amount = this.Amount.getText();
            String Code = this.ID.getText();
            String Source = this.Source.getSelectedItem().toString();
            String Username = Login.Username.getText();

            if (isNew && selectedRow < 0) {
                if (Name.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Name of the Expense", "Enter Name", JOptionPane.ERROR_MESSAGE);
                } else if (Amount.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Total Amount of the Expense", "Enter Name", JOptionPane.ERROR_MESSAGE);
                } else if (Description.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Description of the Expense", "Enter Description", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Connection con = Connect.getConnection();
                        insert = con.prepareStatement("insert into expense(Name, Description, Source, createdBy, Ref, ID, Amount) values (?,?,?,?,?,?,?)");
                        insert.setString(1, Name);
                        insert.setString(2, Description);
                        insert.setString(3, Source);
                        insert.setString(4, Username);
                        insert.setString(5, Ref);
                        insert.setString(6, Code);
                        insert.setString(7, Amount);

                        insert.executeUpdate(); if (Source.contains("Bank")) {

                        insert = con.prepareStatement("insert into bank(Purpose,GivenBy,BOUT,Balance,Bank,TxnId) values (?,?,?,?,?,?)");
                        insert.setString(1, "Expense");
                        insert.setString(2, Username);
                        insert.setString(3, Amount);
                        insert.setString(4, Amount);
                        insert.setString(5, Source);
                        insert.setString(6, Code);

                        insert.executeUpdate();

                        Bal = con.prepareStatement("select SUM(BIN) as BIN, SUM(BOUT) as BOUT from bank where bank=?");
                        Bal.setString(1, Source);

                        ResultSet rs = Bal.executeQuery();
                        double IN, OUT, bal = 0;
                        if (rs.next()) {
                            IN = rs.getDouble("BIN");
                            OUT = rs.getDouble("BOUT");
                            bal = IN - OUT;
                        }

                        insert = con.prepareStatement("update bank set Balance=? where TxnId=?");
                        insert.setDouble(1, bal);
                        insert.setString(2, Code);

                        insert.executeUpdate();

                    }

                    } catch (SQLException ex) {
                        Logger.getLogger(Expense.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(null, "New Expense Recorded");

                    this.Name.setText("");
                    this.Description.setText("");
                    this.Ref.setText("");
                    this.Amount.setText("");

                    table();
                    PaymentMethod();
                    generateExpenseCode();
                }
            } else {

                if (Name.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Name of the Expense", "Enter Name", JOptionPane.ERROR_MESSAGE);
                } else if (Amount.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Total Amount of the Expense", "Enter Name", JOptionPane.ERROR_MESSAGE);
                } else if (Description.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Description of the Expense", "Enter Description", JOptionPane.ERROR_MESSAGE);
                } else {
                    DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
                    String ID = Df.getValueAt(0, selectedRow).toString();

                    try {
                        Connection con = Connect.getConnection();
                        insert = con.prepareStatement("update expense set Name=?, Description=?, Source=?, createdBy=?, Ref=?, Amount=? where ID=?");
                        insert.setString(1, Name);
                        insert.setString(2, Description);
                        insert.setString(3, Source);
                        insert.setString(4, Username);
                        insert.setString(5, Ref);
                        insert.setString(6, Amount);
                        insert.setString(7, ID);

                        insert.executeUpdate();

                    } catch (SQLException ex) {
                        Logger.getLogger(Expense.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JOptionPane.showMessageDialog(null, "Expense Updated");

                    this.Name.setText("");
                    this.Description.setText("");
                    this.Ref.setText("");
                    this.Amount.setText("");

                    table();
                    PaymentMethod();
                    generateExpenseCode();
                }
            }
        }
    }//GEN-LAST:event_saveItemActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:

        this.ID.setText("");
        this.Name.setText("");
        this.Description.setText("");
        this.Ref.setText("");
        this.Amount.setText("");

        PaymentMethod();

    }//GEN-LAST:event_ClearActionPerformed

    private void IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IDKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_IDKeyTyped

    private void srcCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srcCodeKeyReleased
        // TODO add your handling code here:

        DefaultTableModel src = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(src);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(srcCode.getText(), 0));

    }//GEN-LAST:event_srcCodeKeyReleased

    private void srcNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srcNameKeyReleased
        // TODO add your handling code here:

        DefaultTableModel src = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(src);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(srcName.getText(), 1));

    }//GEN-LAST:event_srcNameKeyReleased

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:

        DefaultTableModel src = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(src);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(srcCode.getText(), 0));
        obj.setRowFilter(RowFilter.regexFilter(srcName.getText(), 1));

    }//GEN-LAST:event_SearchActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();
        int modelIndex = jTable1.convertRowIndexToModel(selectedIndex);

        this.ID.setText(Df.getValueAt(modelIndex, 0).toString());
        this.Name.setText(Df.getValueAt(modelIndex, 1).toString());
        this.Description.setText(Df.getValueAt(modelIndex, 2).toString());
        this.Amount.setText(Df.getValueAt(modelIndex, 3).toString());

        String Method = Df.getValueAt(modelIndex, 4).toString();
        this.Source.setSelectedItem(Method);

        this.Ref.setText(Df.getValueAt(modelIndex, 5).toString());

    }//GEN-LAST:event_jTable1MouseClicked

    private void AmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AmountKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }

        if (c == '.' && ((JTextField) evt.getSource()).getText().contains(".")) {
            evt.consume();
        }

    }//GEN-LAST:event_AmountKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField Amount;
    private javax.swing.JButton Clear;
    private java.awt.TextField Description;
    private java.awt.TextField ID;
    private java.awt.TextField Name;
    private javax.swing.JTextField Ref;
    private javax.swing.JButton Search;
    private javax.swing.JComboBox<String> Source;
    private javax.swing.JButton addNew;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton saveItem;
    private javax.swing.JTextField srcCode;
    private javax.swing.JTextField srcName;
    // End of variables declaration//GEN-END:variables
}
