
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Patrick
 */
public class Staff extends javax.swing.JPanel {

    /**
     * Creates new form customer
     */
    public Staff() {

        initComponents();
        tb_load();

    }

    public void tb_load() {

        try {

            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            dt.setRowCount(0);

            Connection con = Connect.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(" SELECT * FROM staff WHERE id!=1");

            while (rs.next()) {

                Vector v = new Vector();

                v.add(rs.getString("ID"));
                v.add(rs.getString("full_name"));
                v.add(rs.getString("username"));
                v.add(rs.getString("email"));
                v.add(rs.getString("user_role"));

                dt.addRow(v);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        c_name = new javax.swing.JTextField();
        c_user = new javax.swing.JTextField();
        Delete = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Searcch = new javax.swing.JButton();
        c_email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        c_id = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        c_pass = new javax.swing.JPasswordField();
        c_pass_conf = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        c_role = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        c_search = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        c_search_tbl = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Name :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Username :");

        c_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        c_user.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete1.png"))); // NOI18N
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Searcch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Searcch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search x30.png"))); // NOI18N
        Searcch.setText("Search");
        Searcch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearcchActionPerformed(evt);
            }
        });

        c_email.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Email :");

        c_id.setEditable(false);
        c_id.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("ID :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("New Password :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Confirm Password :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Role :");

        c_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Staff", "Manager" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Searcch)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(c_user)
                    .addComponent(c_email, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(c_id, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(c_name)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Update)
                        .addGap(18, 18, 18)
                        .addComponent(Delete))
                    .addComponent(c_pass)
                    .addComponent(c_pass_conf)
                    .addComponent(c_role, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(c_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(c_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(c_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(c_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(c_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(c_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(c_pass_conf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Searcch)
                    .addComponent(Update)
                    .addComponent(Delete))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Username", "Email", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Search ID :");

        c_search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Staff Info :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(c_search, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(c_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Search (Name):");

        c_search_tbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        c_search_tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_search_tblKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(c_search_tbl))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(c_search_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearcchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearcchActionPerformed
        // search btn code
        String search = c_search.getText();
        try {

            Connection con = Connect.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(" SELECT * FROM staff WHERE id = '" + search + "'AND id!=1");

            if (rs.next()) {

                c_name.setText(rs.getString("full_name"));
                c_user.setText(rs.getString("username"));
                c_email.setText(rs.getString("email"));
                c_id.setText(rs.getString("id"));
                c_role.setSelectedItem(rs.getString("user_role"));

            }

        } catch (SQLException e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_SearcchActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // update btn code
        if (!ValidateForm.isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
                JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
        String name = c_name.getText();
        String user = c_user.getText();
        String role = c_role.getSelectedItem().toString();
        String id = c_search.getText();
        String email = c_email.getText();
        String no = c_id.getText();
        String pass = c_pass.getText();
        String c_pass = c_pass_conf.getText();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update user '" + user + "' ", "Update User", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            if (pass.equals(c_pass)) {
                try {

                    String hpass = hashPassword(pass);
                    Connection con = Connect.getConnection();
                    Statement s = con.createStatement();
                    s.executeUpdate(" UPDATE staff SET full_name ='" + name + "' ,username ='" + user + "',email  ='" + email + "',user_pass ='" + hpass + "',user_role ='" + role + "' WHERE id  = '" + no + "' ");
                    JOptionPane.showMessageDialog(null, "Data Updated");

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "The Password you typed, it doesn't match with the one you confirmed");
            }

            c_name.setText("");
            c_user.setText("");
            c_role.setSelectedItem("");
            c_search.setText("");
            c_email.setText("");
            c_id.setText("");
            this.c_pass.setText("");
            c_pass_conf.setText("");

            tb_load();

        }
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        //delete btn code
        if (!ValidateForm.isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
                JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
        String id = c_search.getText();
        String user = c_user.getText();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user '" + user.toUpperCase() + "' ", "Delete User", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {

                Connection con = Connect.getConnection();
                Statement s = con.createStatement();
                s.executeUpdate("DELETE FROM staff WHERE id = '" + id + "'");
                JOptionPane.showMessageDialog(null, "User Deleted");

            } catch (SQLException e) {
                System.out.println(e);
            }

            c_name.setText("");
            c_user.setText("");
            c_role.setSelectedItem("");
            c_search.setText("");
            c_email.setText("");
            c_id.setText("");
            this.c_pass.setText("");
            c_pass_conf.setText("");

            tb_load();
        }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // mouse clk & get data to textfeld

        int r = jTable1.getSelectedRow();

        String id = jTable1.getValueAt(r, 0).toString();
        String name = jTable1.getValueAt(r, 1).toString();
        String user = jTable1.getValueAt(r, 2).toString();
        String email = jTable1.getValueAt(r, 3).toString();
        String role = jTable1.getValueAt(r, 4).toString();

        c_search.setText(id);
        c_id.setText(id);
        c_name.setText(name);
        c_user.setText(user);
        c_email.setText(email);
        c_role.setSelectedItem(role);


    }//GEN-LAST:event_jTable1MouseClicked

    private void c_search_tblKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_search_tblKeyReleased

        DefaultTableModel src = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(src);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(c_search_tbl.getText(), 1));

    }//GEN-LAST:event_c_search_tblKeyReleased
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] hashedPasswordBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPasswordBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where SHA-256 algorithm is not available
            // For example, you might log the error or notify the user
            e.printStackTrace(); // This prints the stack trace, which may be useful for debugging
            return null; // Or you can return a default value, or throw a custom exception
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton Searcch;
    private javax.swing.JButton Update;
    private javax.swing.JTextField c_email;
    private javax.swing.JTextField c_id;
    private javax.swing.JTextField c_name;
    private javax.swing.JPasswordField c_pass;
    private javax.swing.JPasswordField c_pass_conf;
    private javax.swing.JComboBox<String> c_role;
    private javax.swing.JTextField c_search;
    private javax.swing.JTextField c_search_tbl;
    private javax.swing.JTextField c_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
