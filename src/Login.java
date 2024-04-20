
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Login extends javax.swing.JFrame {

    //public javax.swing.JLabel loadingSpinner1;
    public Login() {

        initComponents();
        Image logo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
        Image logo1 = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
        loadingSpinner1.setVisible(false);

        this.setIconImage(logo);
        this.setIconImage(logo1);

        //Initialize loading spinner
        loadingSpinner1 = new JLabel(new ImageIcon(getClass().getResource("/Icon/spinner.gif")));
        Left.add(loadingSpinner1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        showPassword = new javax.swing.JLabel();
        LoginBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        loadingSpinner1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(0, 0, 51));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/login.png"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(255, 153, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 0));
        jLabel6.setText("User Login");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Copyright © 2024 Happy Light Tech Ltd |  All rights reserved");

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(162, 162, 162))))
            .addGroup(RightLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RightLayout.setVerticalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(43, 43, 43))
        );

        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 470);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel1.setBackground(new java.awt.Color(0, 0, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Please enter you login details");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Username");

        Username.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Username.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Password");

        showPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/view.png"))); // NOI18N
        showPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPasswordMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showPasswordMouseExited(evt);
            }
        });

        LoginBtn.setBackground(new java.awt.Color(0, 0, 51));
        LoginBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LoginBtn.setForeground(new java.awt.Color(255, 255, 255));
        LoginBtn.setText("Login");
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        loadingSpinner1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/spinner.gif"))); // NOI18N

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadingSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addGroup(LeftLayout.createSequentialGroup()
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(showPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(loadingSpinner1))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jPanel1.add(Left);
        Left.setBounds(400, 0, 400, 380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordMouseClicked
        // TODO add your handling code here:

        showPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/hide.png")));
        password.setEchoChar((char) 0);
    }//GEN-LAST:event_showPasswordMouseClicked

    private void showPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordMouseExited
        // TODO add your handling code here:

        showPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/view.png")));
        password.setEchoChar('*');
    }//GEN-LAST:event_showPasswordMouseExited

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // Check for internet connectivity

        if (isInternetReachable()) {
            // If internet is reachable, proceed with the regular login process
            System.out.println("Internet Available!");
            attemptLogin();
            loadingSpinner1.setVisible(true);
        } else {
            // If internet is not reachable, show a message to the user and fetch credentials from the local database
            JOptionPane.showMessageDialog(this, "No internet connection! You are now working offline.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("No internet connection! Fetching credentials from the local database...");
            fetchCredentialsFromLocalDB();
            loadingSpinner1.setVisible(true);
        }
    }

    // Regular login process
    private void attemptLogin() {
        String user, Password, query, fname = null, passDb = null, role = null;
        int notFound = 0;
        try {
            Connection con = Connect.getConnection();
            Statement st = con.createStatement();

            if ("".equals(Username.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Username Address is required", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(password.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                user = Username.getText();
                Password = password.getText();

                // Hash the entered password
                String hashedPassword = hashPassword(Password);

                query = "SELECT * FROM staff WHERE username= '" + user + "'";

                ResultSet rss = st.executeQuery(query);
                while (rss.next()) {
                    passDb = rss.getString("user_pass");
                    fname = rss.getString("full_name");
                    role = rss.getString("user_role");
                    notFound = 1;
                }
                if (notFound == 1 && hashedPassword.equals(passDb) && role.equals("Admin")) {

                    showLoader();
                    if (ValidateForm.isInternetReachable()) {

                        try {
                            Connection conCloud = Connect.getCloudConnection();

                            // Check if there are any records in the user table
                            PreparedStatement checkUserStmt = con.prepareStatement("SELECT COUNT(*) AS count FROM user");
                            ResultSet rs = checkUserStmt.executeQuery();
                            rs.next();
                            int count = rs.getInt("count");

                            // If there are no records, insert initial data
                            if (count == 0) {
                                PreparedStatement insertInitialDataStmt = con.prepareStatement("INSERT INTO user (Action, DateUsed, Found, LastOpenedDate) VALUES (?, CURRENT_DATE, ?, CURRENT_DATE)");
                                insertInitialDataStmt.setString(1, "Opened");
                                insertInitialDataStmt.setInt(2, 0);
                                insertInitialDataStmt.executeUpdate();

                                DynamicSyncUpload.synchronize(con, conCloud);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    closeLoader();
                    HomeA homeFrame = new HomeA();
                    homeFrame.setVisible(true);

                    this.dispose();
                } else if (notFound == 1 && hashedPassword.equals(passDb) && (role.equals("Staff"))) {

                    showLoader();
                    if (ValidateForm.isInternetReachable()) {

                        try {
                            Connection conCloud = Connect.getCloudConnection();

                            // Check if there are any records in the user table
                            PreparedStatement checkUserStmt = con.prepareStatement("SELECT COUNT(*) AS count FROM user");
                            ResultSet rs = checkUserStmt.executeQuery();
                            rs.next();
                            int count = rs.getInt("count");

                            // If there are no records, insert initial data
                            if (count == 0) {
                                PreparedStatement insertInitialDataStmt = con.prepareStatement("INSERT INTO user (Action, DateUsed, Found, LastOpenedDate) VALUES (?, CURRENT_DATE, ?, CURRENT_DATE)");
                                insertInitialDataStmt.setString(1, "Opened");
                                insertInitialDataStmt.setInt(2, 0);
                                insertInitialDataStmt.executeUpdate();

                                DynamicSyncUpload.synchronize(con, conCloud);
                            } else {
                                // Check if the user has opened the app today
                                PreparedStatement checkOpenedStmt = con.prepareStatement("SELECT * FROM user WHERE DateUsed = CURRENT_DATE");
                                rs = checkOpenedStmt.executeQuery();

                                // If there are no results, it means the user hasn't opened the app today
                                if (!rs.next()) {
                                    // Set Found as 1 for the first time
                                    PreparedStatement setFoundStmt = con.prepareStatement("UPDATE user SET Found =? , DateUsed = CURRENT_DATE");
                                    setFoundStmt.setInt(1, 1);
                                    setFoundStmt.executeUpdate();

                                    DynamicSyncUpload.synchronize(con, conCloud);
                                }

                                // Update the LastOpenedDate to today's date
                                PreparedStatement updateLastOpenedStmt = con.prepareStatement("UPDATE user SET LastOpenedDate = CURRENT_DATE WHERE DateUsed = CURRENT_DATE");
                                updateLastOpenedStmt.executeUpdate();
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    closeLoader();
                    HomeUserS HomeUser = new HomeUserS();
                    HomeUser.setVisible(true);

                    this.dispose();
                } else if (notFound == 1 && hashedPassword.equals(passDb) && (role.equals("Manager"))) {

                    showLoader();
                    if (ValidateForm.isInternetReachable()) {

                        try {
                            Connection conCloud = Connect.getCloudConnection();

                            // Check if there are any records in the user table
                            PreparedStatement checkUserStmt = con.prepareStatement("SELECT COUNT(*) AS count FROM user");
                            ResultSet rs = checkUserStmt.executeQuery();
                            rs.next();
                            int count = rs.getInt("count");

                            // If there are no records, insert initial data
                            if (count == 0) {
                                PreparedStatement insertInitialDataStmt = con.prepareStatement("INSERT INTO user (Action, DateUsed, Found, LastOpenedDate) VALUES (?, CURRENT_DATE, ?, CURRENT_DATE)");
                                insertInitialDataStmt.setString(1, "Opened");
                                insertInitialDataStmt.setInt(2, 0);
                                insertInitialDataStmt.executeUpdate();

                                DynamicSyncUpload.synchronize(con, conCloud);
                            } else {
                                // Check if the user has opened the app today
                                PreparedStatement checkOpenedStmt = con.prepareStatement("SELECT * FROM user WHERE DateUsed = CURRENT_DATE");
                                rs = checkOpenedStmt.executeQuery();

                                // If there are no results, it means the user hasn't opened the app today
                                if (!rs.next()) {
                                    // Set Found as 1 for the first time
                                    PreparedStatement setFoundStmt = con.prepareStatement("UPDATE user SET Found =? , DateUsed = CURRENT_DATE");
                                    setFoundStmt.setInt(1, 1);
                                    setFoundStmt.executeUpdate();

                                    DynamicSyncUpload.synchronize(con, conCloud);
                                }

                                // Update the LastOpenedDate to today's date
                                PreparedStatement updateLastOpenedStmt = con.prepareStatement("UPDATE user SET LastOpenedDate = CURRENT_DATE WHERE DateUsed = CURRENT_DATE");
                                updateLastOpenedStmt.executeUpdate();
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    closeLoader();
                    HomeM Homem = new HomeM();
                    Homem.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect Username or Password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                password.setText("");
                password.requestFocus();

            }
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

// Method to fetch credentials from the local database
    private void fetchCredentialsFromLocalDB() {
        String user, Password, query, fname = null, passDb = null, role = null;
        int notFound = 0;
        try {
            Connection con = Connect.getConnection();
            Statement st = con.createStatement();

            if ("".equals(Username.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Username Address is required", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(password.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                user = Username.getText();
                Password = password.getText();

                // Hash the entered password
                String hashedPassword = hashPassword(Password);

                query = "SELECT * FROM staff WHERE username= '" + user + "'";

                ResultSet rss = st.executeQuery(query);
                while (rss.next()) {
                    passDb = rss.getString("user_pass");
                    fname = rss.getString("full_name");
                    role = rss.getString("user_role");
                    notFound = 1;
                }
                if (notFound == 1 && hashedPassword.equals(passDb) && role.equals("Admin")) {

                    HomeA homeFrame = new HomeA();
                    homeFrame.setVisible(true);
                    this.dispose();
                } else if (notFound == 1 && hashedPassword.equals(passDb) && (role.equals("Staff"))) {

                    HomeUserS HomeUser = new HomeUserS();
                    HomeUser.setVisible(true);

                    this.dispose();
                } else if (notFound == 1 && hashedPassword.equals(passDb) && (role.equals("Manager"))) {

                    HomeM Homem = new HomeM();
                    Homem.setVisible(true);

                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect username or password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                password.setText("");

            }
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    // Method to hash the password
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
            e.printStackTrace(); // Print the stack trace for debugging
            return null; // Or handle the error in a different way
        }
    }

    private boolean isInternetReachable() {
        try {
            // Try to connect to a known host (e.g., Google's DNS server)
            return InetAddress.getByName("8.8.8.8").isReachable(1000); // Timeout set to 1 second
        } catch (IOException e) {
            return false; // Failed to connect
        }
    }

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        showLoader();
        ValidateForm.waitForInternetConnection();
        ValidateForm.validateLicense();
        try {
            Connect.startWAMPP();
            Connect.openLocalServerConnection();
            Connect.initializeDatabase();
        } catch (IOException | InterruptedException | SQLException ex) {
            ex.printStackTrace();
        }
        closeLoader();
        showLoginFrame();
    }

    private static void showLoader() {
        JFrame loaderFrame = new JFrame();
        loaderFrame.setUndecorated(true);
        loaderFrame.setBackground(new Color(0, 0, 0, 0));
        loaderFrame.setVisible(true);
        JLabel loadingLabel = new JLabel();
        loadingLabel.setIcon(new ImageIcon(Login.class.getResource("/upload.gif")));
        loaderFrame.add(loadingLabel);
        loaderFrame.getContentPane().add(loadingLabel);
        loaderFrame.pack();
        loaderFrame.setSize(500, 500);
        loaderFrame.setLocationRelativeTo(null);
    }

    private static void closeLoader() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window.isVisible()) {
                window.dispose();
            }
        }
    }

    private static void showLoginFrame() {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
                DatabaseSyncScheduler.startDatabaseSyncScheduler();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPanel Right;
    public static javax.swing.JTextField Username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loadingSpinner1;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel showPassword;
    // End of variables declaration//GEN-END:variables
}
