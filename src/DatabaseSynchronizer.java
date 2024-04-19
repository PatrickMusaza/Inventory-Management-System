
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DatabaseSynchronizer {

    private Connection localConn;
    private Connection cloudConn;

    public DatabaseSynchronizer(Connection localConn, Connection cloudConn) {
        this.localConn = localConn;
        this.cloudConn = cloudConn;
    }

    public void synchronizeDatabases() throws SQLException {
        // Check internet connection
        if (!isInternetConnected()) {
            System.out.println("No internet connection available.");
            return;
        } else {
            System.out.println("Internet connection available!");
        }

        // Check if local database exists, create if not
        if (!isLocalDatabaseExists()) {
            createLocalDatabase();
        }

        // Fetch remote database structure
        DatabaseMetaData metaData = localConn.getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

        // Iterate over tables and create corresponding tables in local database
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            createLocalTable(tableName);
            copyDataToLocalTable(tableName);
        }
    }

    private boolean isInternetConnected() {
        try {
            // Open a connection to a known external website (e.g., google.com)
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method (GET by default)
            connection.setRequestMethod("HEAD");

            // Set timeout for the connection (5 seconds)
            connection.setConnectTimeout(5000);

            // Check response code to verify if connection is successful
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            // An error occurred while trying to connect (e.g., network error)
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLocalDatabaseExists() throws SQLException {
        DatabaseMetaData metaData = cloudConn.getMetaData();
        ResultSet databases = metaData.getCatalogs();
        while (databases.next()) {
            String dbName = databases.getString("TABLE_CAT");
            if (dbName.equals("ivm")) { // Change to your actual remote database name
                Statement statement = cloudConn.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS staff (\n"
                        + "                          id int NOT NULL AUTO_INCREMENT,\n"
                        + "                          user_role varchar(50) DEFAULT NULL,\n"
                        + "                          user_pass varchar(255) NOT NULL,\n"
                        + "                          created timestamp NULL DEFAULT CURRENT_TIMESTAMP,\n"
                        + "                          email varchar(200) DEFAULT NULL,\n"
                        + "                          username varchar(50) NOT NULL,\n"
                        + "                          full_name varchar(100) DEFAULT NULL,\n"
                        + "                          PRIMARY KEY (id),\n"
                        + "                          UNIQUE KEY email (email)\n"
                        + "                        )");
                return true;
            }
        }
        return false;
    }

    public void createLocalDatabase() throws SQLException {

        Statement statement = cloudConn.createStatement();
        statement.executeUpdate("CREATE DATABASE ivm"); // Change to your actual remote database name
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS staff (\n"
                + "                          id int NOT NULL AUTO_INCREMENT,\n"
                + "                          user_role varchar(50) DEFAULT NULL,\n"
                + "                          user_pass varchar(255) NOT NULL,\n"
                + "                          created timestamp NULL DEFAULT CURRENT_TIMESTAMP,\n"
                + "                          email varchar(200) DEFAULT NULL,\n"
                + "                          username varchar(50) NOT NULL,\n"
                + "                          full_name varchar(100) DEFAULT NULL,\n"
                + "                          PRIMARY KEY (id),\n"
                + "                          UNIQUE KEY email (email)\n"
                + "                        )");
        System.out.println("Cloud database created.");
    }

    private void createLocalTable(String tableName) throws SQLException {//     
        DatabaseMetaData metaData = localConn.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        // Truncate the table if it exists 
        try {
            Statement localStatement = cloudConn.createStatement();
            localStatement.executeUpdate("TRUNCATE TABLE " + tableName);
            //System.out.println("Table Truncated");
        } catch (SQLException e) {
            // Ignore error if the table doesn't exist 
            if (!e.getMessage().contains("doesn't exist")) {
                throw e; // Re-throw the exception if it's a different error
            }
        }
        if (!tableExists(tableName)) {
            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE ");
            createTableQuery.append(tableName).append(" (");

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");

                String columnDefinition = columnName + " " + columnType;
                if (!columnType.equalsIgnoreCase("TIMESTAMP")) {
                    columnDefinition += "(" + columnSize + ")";
                }
                createTableQuery.append(columnDefinition).append(",");
            }

            createTableQuery.setLength(createTableQuery.length() - 1); // Remove the last comma
            createTableQuery.append(")");

            Statement statement = cloudConn.createStatement();
            statement.executeUpdate(createTableQuery.toString());
            System.out.println("Table " + tableName + " created in cloud database.");

        }

    }

    private void copyDataToLocalTable(String tableName) throws SQLException {
        //Ensure the table exists before copying
        if (!tableExists(tableName)) {
            createLocalTable(tableName);
        } else {
            Statement remoteStatement = localConn.createStatement();
            ResultSet resultSet = remoteStatement.executeQuery("SELECT * FROM " + tableName);
            Statement localStatement = cloudConn.createStatement();
            System.out.println("Table Truncated Sucessfuly!");
            while (resultSet.next()) {
                StringBuilder insertQuery = new StringBuilder("INSERT INTO ");
                insertQuery.append(tableName).append(" VALUES (");
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    insertQuery.append("'").append(resultSet.getString(i)).append("',");
                }

                insertQuery.setLength(insertQuery.length() - 1); // Remove the last comma
                insertQuery.append(")");
                localStatement.executeUpdate(insertQuery.toString());
            }
            System.out.println("Data copied: " + tableName + " table to cloud database.");
        }

    }

    // Function to check table existence
    private boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData metaData = cloudConn.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
        return resultSet.next();
    }
}
