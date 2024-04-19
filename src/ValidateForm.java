
import java.io.File;
import java.io.FileWriter;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ValidateForm extends javax.swing.JFrame {

    /*------------------------------ Declaration of all useful global variables------------------------------*/
    String currentSerialNumber = ComputerSerialNumber.getMotherboardSerialNumber();
    int usageCount = 0;
    String licenseKeyVar;
    public static File licenseFile = new File("C:\\Users\\Default\\AppData\\Local\\Microsoft\\lic.txt");
    public static File licenseFileBack = new File("C:\\Users\\Default\\AppData\\Roaming\\Microsoft\\Windows\\lic.txt");
    public static String fileName = "C:\\Users\\Default\\AppData\\Local\\Microsoft\\lic.txt";

    /*---------------------------------- Declaration ends here -------------------------------------------------*/
    public ValidateForm() {

        initComponents();
        Image logo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
        this.setIconImage(logo);
       this.loadingSpinner1.setVisible(false);
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
        licenseKey = new javax.swing.JTextField();
        ValidateBtn = new javax.swing.JButton();
        loadingSpinner1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("License ");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(0, 0, 51));
        Right.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 204, 0));
        jLabel6.setText("License Validation");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Copyright © 2024 Happy Light Tech Ltd |  All rights reserved");

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel5))
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel7)))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(86, 86, 86))
        );
        RightLayout.setVerticalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addGap(89, 89, 89)
                .addComponent(jLabel7)
                .addGap(48, 48, 48))
        );

        jLabel5.getAccessibleContext().setAccessibleName("");

        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 470);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Welcome");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Please enter License Key");

        licenseKey.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        licenseKey.setForeground(new java.awt.Color(102, 102, 102));

        ValidateBtn.setBackground(new java.awt.Color(0, 0, 51));
        ValidateBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ValidateBtn.setForeground(new java.awt.Color(255, 255, 255));
        ValidateBtn.setText("Validate");
        ValidateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValidateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ValidateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(licenseKey, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(licenseKey, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ValidateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        jPanel1.add(Left);
        Left.setBounds(400, 0, 400, 380);

        loadingSpinner1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/spinner.gif"))); // NOI18N
        jPanel1.add(loadingSpinner1);
        loadingSpinner1.setBounds(750, 410, 32, 32);

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

    public void ValidateBtnActionPerformed(java.awt.event.ActionEvent evt) {
        /*--------------------- What happens when 'Validate Button is pressed.' -------------------------------*/

        this.loadingSpinner1.setVisible(true);
        
        if ("".equals(licenseKey.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "License key is required to proceed", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            licenseKeyVar = licenseKey.getText();
            // Check if the license key is valid

            if (isValidLicense(licenseKeyVar)) {

                int licenseCount = UpdateCustomerData.fetchLicenseCount(licenseKeyVar);
                // Now you can use the license count as needed
                System.out.println("""
                                   Getting allocated # of license for customer ...
                                   """);
                System.out.println("Times this licenses has been used : " + usageCount + " ...\n");
                System.out.println("Allocated licenses: " + licenseCount + " ...\n");

                // Perform necessary actions for a valid license key
                getLicenseUsageCount(licenseKeyVar);

                UpdateCustomerData.updateDateUsed(licenseKeyVar);
                System.out.println("""
                                   License Key Date Updated ...
                                   """);

                UpdateCustomerData.updateDaysLeft(licenseKeyVar);
                System.out.println("""
                                   License Key Days Left Updated! ...
                                   """);

                createLocalLicenseFile();
                createLocalLicenseFileBackUp();

                if (usageCount <= licenseCount) {
                    //If the usage count is below 3, proceed with further actions

                    System.out.println("License key is valid! Proceeding to login screen \n");
                    JOptionPane.showMessageDialog(this, "License key is valid!");
                    showLoginFrame();

                    // Close the current validation window
                    this.dispose();
                } else {
                    // If the usage count is 3 or above, display a message and do not proceed
                    JOptionPane.showMessageDialog(this, "Sorry! The maximum number of license uses has been reached. Please contact the software developer for assistance.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Sorry! Cant log you in as maximum number of license usages reached.");
                    System.exit(0);
                }
            } else {
                // If the license key is invalid, show an error message
                JOptionPane.showMessageDialog(this, "Invalid license key. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);

                System.out.println("Invalid license key. Please try again!");
            }
        }
    }

    public boolean isValidLicense(String licenseKeyVar) {
        ConnectCloud connector = new ConnectCloud();

        try {
            Connection con = connector.getConnection();
            System.out.println("DB Connected!");

            System.out.println("Computer Serial Number: " + currentSerialNumber);
            if (con != null) {
                String query = "SELECT * FROM `hlt-customers` WHERE cst_license_key = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, licenseKeyVar);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Retrieve additional details from the database
                    String companyName = rs.getString("company_name");
                    String licenseKey = rs.getString("cst_license_key");
                    usageCount = rs.getInt("cst_license_key_used");
                    String validFrom = rs.getString("valid_from");
                    String validTo = rs.getString("valid_to");
                    String dbIp = rs.getString("db_ip");
                    String dbUrl = rs.getString("db_url");
                    String dbUser = rs.getString("db_user");
                    String dbPass = rs.getString("db_pass");
                    String dateUsed = rs.getString("date_used");
                    String daysLeft = rs.getString("days_left");
                    String serialNumber = rs.getString("serial_number");

                    System.out.println("Company Name: " + companyName + " ...\n");
                    System.out.println("License Key: " + licenseKey + " ...\n");
                    System.out.println("Usage Count: " + usageCount + " ...\n");
                    System.out.println("Valid From: " + validFrom + " ...\n");
                    System.out.println("Valid To: " + validTo + " ...\n");
                    System.out.println("DB IP: " + dbIp + " ...\n");
                    System.out.println("DB URL: " + dbUrl + " ...\n");
                    System.out.println("DB User: " + dbUser + " ...\n");
                    System.out.println("DB Pass: " + dbPass + " ...\n");
                    System.out.println("Date Used: " + dateUsed + " ...\n");
                    System.out.println("Days Left: " + daysLeft + " ...\n");
                    System.out.println("Serial Number: " + serialNumber + " ...\n");

                    // License key is found in the database
                    return true;
                } else {
                    // License key is not found in the database
                    return false;
                }
            } else {
                System.out.println("Failed to connect to the database.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int getLicenseUsageCount(String licenseKeyVar) {
        ConnectCloud connector = new ConnectCloud();

        try {
            Connection con = connector.getConnection();

            if (con != null) {

                // Fetch the serial number array from the database for the given license key
                String query = "SELECT serial_number FROM `hlt-customers` WHERE cst_license_key = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, licenseKeyVar);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Serial number array exists in the database
                    String serialNumbersStr = rs.getString("serial_number");
                    String[] serialNumbers = serialNumbersStr.split(",");

                    // Check if the serial number exists in the array
                    for (String serial : serialNumbers) {
                        if (serial.trim().equals(currentSerialNumber)) {
                            // Serial number exists in the array, no need to increment usage count
                            return 1;
                        }
                    }

                    // Serial number does not exist in the array, add it and increment usage count
                    serialNumbersStr += "," + currentSerialNumber;
                    String updateQuery = "UPDATE `hlt-customers` SET cst_license_key_used = cst_license_key_used + 1, serial_number = ? WHERE cst_license_key = ?";
                    PreparedStatement updatePs = con.prepareStatement(updateQuery);
                    updatePs.setString(1, serialNumbersStr);
                    updatePs.setString(2, licenseKeyVar);
                    updatePs.executeUpdate();

                    return 1;
                } else {
                    // Serial number array does not exist in the database, create a new array with current serial number
                    String currentSerialNumber = ComputerSerialNumber.getMotherboardSerialNumber();
                    String updateQuery = "UPDATE `hlt-customers` SET cst_license_key_used = cst_license_key_used + 1, serial_number = ? WHERE cst_license_key = ?";
                    PreparedStatement updatePs = con.prepareStatement(updateQuery);
                    updatePs.setString(1, currentSerialNumber);
                    updatePs.setString(2, licenseKeyVar);
                    updatePs.executeUpdate();

                    return 1;
                }
            } else {
                System.out.println("Failed to connect to the database.");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void checkForInternetConnection() {
        // Show the popup message indicating that the system is waiting for internet
        showWaitingForInternetPopup();

        // Continuously check for internet connectivity until it becomes reachable
        while (!isInternetReachable()) {

            // Show the popup message indicating that the system is waiting for internet
            showWaitingForInternetPopup();

        }

        // Once internet is available, close the popup message
        closeWaitingForInternetPopup();
    }

    public static void waitForInternetConnection() {
        // Show the popup message indicating that the system is waiting for internet

        if (!isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

// Method to check if the internet is reachable
    public static boolean isInternetReachable() {
        try {
            // Try to connect to a known host (e.g., Google's DNS server)
            return InetAddress.getByName("8.8.8.8").isReachable(1000); // Timeout set to 1 second
        } catch (IOException e) {
            return false; // Failed to connect
        }
    }

// Method to show the waiting for internet popup message
    private static void showWaitingForInternetPopup() {
        // Add code to show the popup message here
        try {

            Connection conn = Connect.getConnection();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
    }

// Method to close the waiting for internet popup message
    private static void closeWaitingForInternetPopup() {
        // Add code to close the popup message here
        JOptionPane.showMessageDialog(null, "There is internet Connection!");
    }

    public void createLocalLicenseFile() {

        // Check if the license file already exists
        if (licenseFile.exists()) {
            System.out.println("""
                               License file already exists. ...
                               """);
            return; // Exit the method if the file already exists
        }

        // Create the license file with the valid license key
        try {
            FileWriter writer = new FileWriter(licenseFile);
            writer.write(licenseKeyVar); // Write the validated license key
            writer.close();
            System.out.println("""
                               Local license file created successfully. ...
                               """);
        } catch (IOException e) {
            System.err.println("Error creating local license file: " + e.getMessage());
        }
    }

    public void createLocalLicenseFileBackUp() {

        // Check if the license file already exists
        if (licenseFileBack.exists()) {
            System.out.println("""
                               License file already exists. ...
                               """);
            return; // Exit the method if the file already exists
        }

        // Create the license file with the valid license key
        try {
            FileWriter writer = new FileWriter(licenseFileBack);
            writer.write(licenseKeyVar); // Write the validated license key
            writer.close();
            System.out.println("""
                               Local license file created successfully. ...
                               """);
        } catch (IOException e) {
            System.err.println("Error creating local license file: " + e.getMessage());
        }
    }

    private static void showLoader() {
        JFrame loaderFrame = new JFrame();
        loaderFrame.setUndecorated(true);
        loaderFrame.setBackground(new Color(0, 0, 0, 0));
        loaderFrame.setVisible(true);
        JLabel loadingLabel = new JLabel();
        loadingLabel.setIcon(new ImageIcon(Login.class.getResource("/loader.gif")));
        loaderFrame.add(loadingLabel);
        loaderFrame.getContentPane().add(loadingLabel);
        loaderFrame.pack();
        loaderFrame.setSize(300, 300);
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
        // Check if the license file already exists

        showLoader();
        if (ValidateForm.isInternetReachable()) {

            if (licenseFile.exists()) {
                validateLicense();
            } else {
                closeLoader();
                showValidateFrame();
            }
        } else {
            if (licenseFile.exists() && licenseFileBack.exists()) {
                try {
                    if (compareFiles(licenseFile, licenseFileBack)) {
                        showLoginFrame();
                        closeLoader();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid License Key", "Invalid License", JOptionPane.ERROR_MESSAGE);
                        closeLoader();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ValidateForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid License Files.\nCall System Administrator!", "Invalid License", JOptionPane.ERROR_MESSAGE);
                closeLoader();
            }
        }
    }

    private static boolean compareFiles(File file1, File file2) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));

        String line1 = reader1.readLine();
        String line2 = reader2.readLine();

        while (line1 != null && line2 != null) {
            if (!line1.equals(line2)) {
                reader1.close();
                reader2.close();
                return false; // Files are not identical
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
        }

        boolean filesMatch = (line1 == null && line2 == null);
        reader1.close();
        reader2.close();
        return filesMatch;
    }

    public static void showLoginFrame() {

        try {
            Connect.startWAMPP();
            Connect.openLocalServerConnection();
            Connect.initializeDatabase();
        } catch (IOException | InterruptedException | SQLException ex) {
            ex.printStackTrace();
        }

        closeLoader();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    public static void validateLicense() {
        try {
            File file = new File(fileName);
            String licenseKey = "";
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    licenseKey += scanner.nextLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ValidateForm.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("File not found: " + fileName);
                ex.printStackTrace();
            }

            try {
                Connection con = ConnectCloud.getConnection();
                System.out.println("DB Connected!");

                if (con != null) {
                    if (isValidLicenseKey(licenseKey)) {
                        showLoginFrame();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid License Key. Please Try Again!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                        System.out.println("Invalid License Key. Please Try Again!");
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the database", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to the database: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidLicenseKey(String licenseKey) {
        ConnectCloud connector = new ConnectCloud();
        try (Connection con = connector.getConnection()) {
            if (con != null) {
                String query = "SELECT * FROM `hlt-customers` WHERE cst_license_key = ?";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, licenseKey);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        // Get the current date
                        Date currentDate = new Date();

                        // Get the valid_to date from the result set
                        Date validToDate = rs.getDate("valid_to");

                        // Calculate the difference in days between valid_to and current date
                        long differenceInMillis = validToDate.getTime() - currentDate.getTime();
                        long daysLeft = differenceInMillis / (1000 * 60 * 60 * 24); // Convert milliseconds to days

                        // Update the days_left column in the database
                        String updateQuery = "UPDATE `hlt-customers` SET days_left = ? WHERE cst_license_key = ?";
                        PreparedStatement updatePs = con.prepareStatement(updateQuery);
                        updatePs.setLong(1, daysLeft);
                        updatePs.setString(2, licenseKey);
                        updatePs.executeUpdate();

                        return true;
                    }
                }

            } else {
                System.out.println("Failed to connect to the database.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static void showValidateFrame() {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ValidateForm showValidateFrame = new ValidateForm();
                new ValidateForm().setVisible(true);
                showValidateFrame.pack();
                showValidateFrame.setLocationRelativeTo(null);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel Right;
    private javax.swing.JButton ValidateBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField licenseKey;
    private javax.swing.JLabel loadingSpinner1;
    // End of variables declaration//GEN-END:variables

    public static javax.swing.JTextField licenseKeyVarInput = new javax.swing.JTextField();

}
