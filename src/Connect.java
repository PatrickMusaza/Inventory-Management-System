
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {

    public static Connection con;

    public static final String dbName = "ivm";
    public static final String username = "root";
    public static final String password = "";
    public static final String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;

    private static final String WAMP_PROCESS_NAME = "mysqld";

    // Method to start WAMPP server if it's not already running
    public static void startWAMPP() throws IOException, InterruptedException {
        if (!isWAMPPRunning()) {
            System.out.println("WAMPP server is not running. Starting it...");
            String wampPath = "C:\\wamp64\\wampmanager.exe";
            ProcessBuilder processBuilder = new ProcessBuilder(wampPath);
            processBuilder.start();
            System.out.println("WAMPP server started.");
        } else {
            System.out.println("WAMPP server is already running.");
        }
    }

    // Method to check if WAMPP server is running
    private static boolean isWAMPPRunning() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("tasklist.exe");
        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(WAMP_PROCESS_NAME)) {
                    return true; // WAMPP server process found
                }
            }
        }
        return false; // WAMPP server process not found
    }

    // Method to create the database and tables if they don't exist
    public static void initializeDatabase() throws SQLException {

        String createCustomerQuery, createItemQuery, createPurchaseQuery, createPurchaseitemQuery, createPettyCash, createSalesQuery, createPaymentQuery, createSalesitemQuery, createStaffQuery, createInsertstaffQuery, createStockQuery, createImportQuery, createBankquery, createExpenseQuery, createUserQuery;

        createCustomerQuery
                = """
        CREATE TABLE IF NOT EXISTS customer (        
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          Type text    NULL,
                          TIN text   NOT NULL,
                          Name text   NOT NULL,
                          Bank text    NULL,
                          Address text    NULL,
                          Delegator text    NULL,
                          Nationality text   NOT NULL,
                          Email text    NULL,
                          Account text    NULL,
                          Remark text    NULL,
                          Phone1 text   NOT NULL,
                          Phone2 text    NULL,
                          FAX text    NULL,
                          Depositor text   NOT NULL,
                          Active text   NOT NULL,
                          createdBy text ,
                          createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                        )""";

        createItemQuery
                = """
                CREATE TABLE IF NOT EXISTS item (        
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          ItemCode text  ,
                          ItemName text  ,
                          UseBarcode text  ,
                          Origin text  ,
                          ItemType text  ,
                          PkgUnit text  ,
                          QtyUnit text  ,
                          PurchaseUnit float DEFAULT NULL,
                          SalePrice float DEFAULT NULL,
                          TaxType text  ,
                          BeginningStock int DEFAULT NULL,
                          CurrentStock float DEFAULT 0,
                          SafetyStock int DEFAULT NULL,
                          Description text  DEFAULT NULL,
                          Active text  ,
                          createdBy text ,
                          createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                        )""";
        createPurchaseQuery
                = """
                            CREATE TABLE IF NOT EXISTS purchase (        
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                Type text NOT NULL,
                                SupplierID text NOT NULL,
                                SupplierSDCD text NOT NULL,
                                SupplierReceipt text  NULL,
                                TotalAmount text NOT NULL,
                                PurchaseReason text  NULL,
                                Remark text  NULL,
                                PurchaseCode text  NULL, 
                                SupplierName text NOT NULL,
                                Status text  NULL,
                                Method text  NULL,
                                ReleaseDate text NULL,
                                VAT text  NULL,
                                CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                CreatedBy text NOT NULL,
                                InvoiceID varchar(255) NOT NULL DEFAULT 'INV 001'
                            )""";
        createPurchaseitemQuery
                = """
                 CREATE TABLE IF NOT EXISTS purchaseitem (                          
                          id int NOT NULL AUTO_INCREMENT,
                          ItemCode text   NOT NULL,
                          ItemName text   NOT NULL,
                          UnitPrice text   NOT NULL,
                          PurchaseQty text   NOT NULL,
                          PurchasePrice text   NOT NULL,
                          TaxType text   NOT NULL,
                          VAT text   NOT NULL,
                          TotalPrice text    NULL,
                          createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          RefPurchase varchar(255)   NOT NULL DEFAULT 'PRC 001',
                          KEY FK_Sales (RefPurchase(100)),                          
                          PRIMARY KEY (id)
                        )""";
        createSalesQuery
                = """
                    CREATE TABLE IF NOT EXISTS sales (        
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          Type text   NOT NULL,
                          CustomerID text   NOT NULL,
                          SaleDate text NOT NULL,
                          TotalAmount float  DEFAULT NULL,
                          Remark text    NULL,
                          PurchaseCode text,
                          CustomerName text   NOT NULL,
                          ReleaseDate text  NULL, 
                          SIN float  DEFAULT NULL,
                          SOUT float  DEFAULT NULL,
                          Balance float  DEFAULT NULL,
                          Ref_Inv text NULL,
                          VAT text    NULL,
                          Status text  NULL,
                          Method text  NULL,
                          CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          CreatedBy text   NOT NULL,
                          InvoiceID varchar(255)   NOT NULL DEFAULT 'INV 001'
                        )""";
        createSalesitemQuery
                = """
                 CREATE TABLE IF NOT EXISTS salesitem (
                          id int NOT NULL AUTO_INCREMENT,
                          ItemCode text   NOT NULL,
                          ItemName text   NOT NULL,
                          UnitPrice text   NOT NULL,
                          SalesQty text   NOT NULL,
                          SalesPrice text   NOT NULL,
                          TaxType text   NOT NULL,
                          VAT text   NOT NULL,
                          TotalPrice text   NOT NULL,
                          DRatio text    NULL,
                          DAmount text    NULL,
                          createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          RefSale varchar(255)   NOT NULL DEFAULT 'INV 001',
                          KEY FK_Sales (RefSale(100)),
                          PRIMARY KEY (id)
                        )""";
        createStaffQuery
                = """
                CREATE TABLE IF NOT EXISTS staff (
                          id int NOT NULL AUTO_INCREMENT,
                          user_role TEXT DEFAULT NULL,
                          user_pass TEXT NOT NULL,
                          email TEXT DEFAULT NULL,
                          username TEXT NOT NULL,
                          full_name TEXT DEFAULT NULL,    
                          createdAt timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (id),
                          UNIQUE KEY email (email(100))
                        )                                  
                  """;

        createInsertstaffQuery
                = """
                  INSERT IGNORE INTO staff (id, user_role, user_pass, email, username, full_name)
                  SELECT 1, 'Admin', '335b202e18be9e2030a3d3ec6f6a355035e6ea47a0ab813230dcbe48dc18c7d0', 'info@tech.happylight.rw', 'Admin', 'System Admin'
                  FROM dual
                  WHERE NOT EXISTS (SELECT * FROM staff WHERE id = 1)
                  """;
        createStockQuery
                = """
                CREATE TABLE IF NOT EXISTS stock (        
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          ItemCode text  ,
                          ItemName text  ,
                          SafetyQty int DEFAULT NULL,
                          Purchase int DEFAULT 0,
                          Sales int DEFAULT 0,
                          BeginningStock float DEFAULT NULL,
                          CurrentStock float DEFAULT NULL,
                          UnitPrice float DEFAULT NULL,
                          PurchasePrice float DEFAULT NULL,
                          StockAmount text DEFAULT NULL,
                          ChangeQuantity float DEFAULT NULL,
                          NewQuantity float Default Null,
                          Remark text,
                          Status text,
                          createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                        ) """;
        createImportQuery
                = """
                CREATE TABLE IF NOT EXISTS import (        
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               OpCode text NOT NULL,
                                               DeclDate text NULL,
                                               Seq text DEFAULT NULL,
                                               HsCode text  NULL,
                                               ItemCode text  NULL,
                                               Origin text  NULL,
                                               Export text  NULL,
                                               PkgQty text  NULL,
                                               Qty text  NULL,
                                               Unit text NULL,
                                               ItemDesc text NULL,
                                               Supplier text NULL,
                                               Agent text NULL,
                                               TaxPayerName text NULL,
                                               ItemName text NULL,
                                               GrossWT text,
                                               NetWT text DEFAULT NULL,
                                               InvoiceAMT text  NULL,
                                               InvoiceCurrency text NULL,
                                               Ratio text NULL,
                                               Status text NOT NULL,
                                               ApproveDate text Default NULL,
                                               createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                  )""";

        createPettyCash
                = """
                CREATE TABLE IF NOT EXISTS pettycash (
                  ID int NOT NULL AUTO_INCREMENT,
                  PIN float  DEFAULT NULL,
                  POUT float  DEFAULT NULL,
                  Purpose text,
                  GivenBy text ,
                  ReceivedBy text ,
                  Balance float NOT NULL,
                  RefNo text,
                  TxnId text NOT NULL,
                  InvoiceNo text,
                  file_path text,
                  CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                  PRIMARY KEY (id)
                )""";

        createBankquery
                = """
                CREATE TABLE IF NOT EXISTS bank (
                  ID int NOT NULL AUTO_INCREMENT,
                  Purpose text,
                  GivenBy text ,
                  ReceivedBy text ,
                  BIN float  DEFAULT NULL ,
                  BOUT float  DEFAULT NULL ,
                  Balance float NOT NULL,
                  Method text,
                  Bank text,
                  TxnId text NOT NULL,
                  InvoiceNo text,
                  file_path text,
                  CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                  PRIMARY KEY (id)
                )""";

        createPaymentQuery
                = """
                 CREATE TABLE IF NOT EXISTS Payment (        
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   Code text,
                                   Method text,
                                   Amount text,
                                   CreatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)
                 """;

        createExpenseQuery
                = """
 		Create table if not exists Expense (
 			ID text,
 			Name text,
 			Description text,
 			Source text,
                        Ref text,
                        Amount text,
 			CreatedBy text,
 			CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 			PRIMARY KEY(ID(100)))
 	""";

        createUserQuery = """
                          
                     CREATE TABLE IF NOT EXISTS `user` (
                              ID int AUTO_INCREMENT PRIMARY KEY,
                              Action text,
                              DateUsed DATE NULL,
                              Found int NULL DEFAULT 0,
                              openedAt TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              LastOpenedDate DATE
                          );
        """;

        try {
            Statement statement1 = con.createStatement();
            Statement statement2 = con.createStatement();
            Statement statement3 = con.createStatement();
            Statement statement4 = con.createStatement();
            Statement statement5 = con.createStatement();
            Statement statement6 = con.createStatement();
            Statement statement7 = con.createStatement();
            Statement statement8 = con.createStatement();
            Statement statement9 = con.createStatement();
            Statement statement10 = con.createStatement();
            Statement statement11 = con.createStatement();
            Statement statement12 = con.createStatement();
            Statement statement13 = con.createStatement();
            Statement statement14 = con.createStatement();
            Statement statement15 = con.createStatement();
            statement1.executeUpdate(createItemQuery);
            statement2.executeUpdate(createCustomerQuery);
            statement3.executeUpdate(createStaffQuery);
            statement4.executeUpdate(createStockQuery);
            statement6.executeUpdate(createPurchaseQuery);
            statement7.executeUpdate(createSalesQuery);
            statement8.executeUpdate(createSalesitemQuery);
            statement9.executeUpdate(createPurchaseitemQuery);
            statement10.executeUpdate(createImportQuery);
            statement11.executeUpdate(createPettyCash);
            statement12.executeUpdate(createBankquery);
            statement13.executeUpdate(createPaymentQuery);
            statement14.executeUpdate(createExpenseQuery);
            statement15.executeUpdate(createUserQuery);
            statement5.executeUpdate(createInsertstaffQuery);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to open local server connection
    public static void openLocalServerConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Creating the database if it doesn't exist
            try (var stmt = conn.createStatement()) {
                stmt.executeUpdate(createDatabaseQuery);
                System.out.println("Database created successfully.");
            }
            con = DriverManager.getConnection(url + dbName, username, password);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {

            String url = "jdbc:mysql://localhost:3306/";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = url + dbName;
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load database driver", e);
        }
    }

    public static Connection getCloudConnection() throws SQLException {
        try {

            String url = "jdbc:mysql://34.69.198.229:3306/ivm";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = url + dbName;
            return DriverManager.getConnection(url, "patson", "pat123");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load database driver", e);
        }
    }

    public void main(String[] args) throws SQLException, IOException, InterruptedException {
        try {
            //step 1: Licence Validation

            // Step 2: Start WAMPP server
            startWAMPP();

            // Step 3: Open local server connection
            openLocalServerConnection();

            // Step 4: Initialize the database
            initializeDatabase();

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }
}
