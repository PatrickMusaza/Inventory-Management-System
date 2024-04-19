
import java.sql.*;
import java.util.*;

public class DynamicSyncD {

    public static void synchronize(Connection localConnection, Connection cloudConnection) {
        try {
            // Synchronize from local to cloud
            syncFromSourceToDestination(localConnection, cloudConnection);

            // Synchronize from cloud to local
            syncFromSourceToDestination(cloudConnection, localConnection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void syncFromSourceToDestination(Connection sourceConnection, Connection destinationConnection) throws SQLException {
        DatabaseMetaData sourceMetaData = sourceConnection.getMetaData();
        ResultSet sourceTables = sourceMetaData.getTables(null, null, null, new String[]{"TABLE"});
        while (sourceTables.next()) {
            String tableName = sourceTables.getString("TABLE_NAME");
            synchronizeTable(sourceConnection, destinationConnection, tableName);
        }
    }

    private static void synchronizeTable(Connection sourceConnection, Connection destinationConnection, String tableName) throws SQLException {
        createTableIfNotExists(destinationConnection, sourceConnection, tableName);

        if (!tableExists(sourceConnection, tableName)) {
            System.out.println("Table " + tableName + " does not exist in the source database.");
            return;
        }

        String selectQuery = "SELECT * FROM " + tableName;
        PreparedStatement selectStatement = sourceConnection.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columnNames = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        while (resultSet.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (String columnName : columnNames) {
                Object value = resultSet.getObject(columnName);
                rowData.put(columnName, value);
            }
            syncRow(destinationConnection, tableName, columnNames, rowData);
        }
    }

    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, tableName, null);
        return tables.next();
    }

    private static void createTableIfNotExists(Connection destinationConnection, Connection sourceConnection, String tableName) throws SQLException {
        DatabaseMetaData metaData = destinationConnection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, tableName, null);
        if (!tables.next()) {
            String createQuery = getCreateTableQuery(sourceConnection, tableName);
            Statement statement = destinationConnection.createStatement();
            statement.executeUpdate(createQuery);
            System.out.println("Table " + tableName + " created in destination database.");
        }
    }

    private static String getCreateTableQuery(Connection sourceConnection, String tableName) throws SQLException {
        StringBuilder createQuery = new StringBuilder();
        createQuery.append("CREATE TABLE ").append(tableName).append(" (");
        DatabaseMetaData metaData = sourceConnection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        List<String> primaryKeyColumns = getPrimaryKeyColumns(sourceConnection, tableName);
        Set<String> uniqueColumns = getUniqueColumns(metaData, tableName);

        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            String dataType = columns.getString("TYPE_NAME");
            int columnSize = columns.getInt("COLUMN_SIZE");
            boolean isNullable = columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
            boolean isAutoIncrement = columns.getBoolean("IS_AUTOINCREMENT");
            String defaultValue = columns.getString("COLUMN_DEF");

            createQuery.append(columnName).append(" ").append(dataType);
            if (dataType.equalsIgnoreCase("VARCHAR") || dataType.equalsIgnoreCase("CHAR")) {
                createQuery.append("(").append(columnSize).append(")");
            }
            if (!isNullable) {
                createQuery.append(" NOT NULL");
            }
            if (isAutoIncrement) {
                createQuery.append(" AUTO_INCREMENT");
            }
            if (defaultValue != null && !defaultValue.isEmpty()) {
                createQuery.append(" DEFAULT ").append(defaultValue);
            }
            if (primaryKeyColumns.contains(columnName)) {
                createQuery.append(" PRIMARY KEY");
            }
            if (uniqueColumns.contains(columnName)) {
                createQuery.append(" UNIQUE");
            }
            createQuery.append(",");
        }
        // Add foreign key constraints
        addForeignKeyConstraints(sourceConnection, tableName, createQuery);

        createQuery.deleteCharAt(createQuery.length() - 1);
        createQuery.append(")");

        return createQuery.toString();
    }

    private static List<String> getPrimaryKeyColumns(Connection connection, String tableName) throws SQLException {
        List<String> primaryKeyColumns = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
        while (primaryKeys.next()) {
            String primaryKeyColumn = primaryKeys.getString("COLUMN_NAME");
            primaryKeyColumns.add(primaryKeyColumn);
        }
        return primaryKeyColumns;
    }

    private static Set<String> getUniqueColumns(DatabaseMetaData metaData, String tableName) throws SQLException {
        Set<String> uniqueColumns = new HashSet<>();
        ResultSet uniqueIndices = metaData.getIndexInfo(null, null, tableName, true, false);
        while (uniqueIndices.next()) {
            String columnName = uniqueIndices.getString("COLUMN_NAME");
            uniqueColumns.add(columnName);
        }
        return uniqueColumns;
    }

    private static void addForeignKeyConstraints(Connection sourceConnection, String tableName, StringBuilder createQuery) throws SQLException {
        DatabaseMetaData metaData = sourceConnection.getMetaData();
        ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
        while (foreignKeys.next()) {
            String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
            String pkTableName = foreignKeys.getString("PKTABLE_NAME");
            String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");

            createQuery.append(", FOREIGN KEY (").append(fkColumnName).append(") ")
                    .append("REFERENCES ").append(pkTableName).append("(").append(pkColumnName).append(")");
        }
    }

    private static void syncRow(Connection destinationConnection, String tableName, List<String> columnNames, Map<String, Object> rowData) {
        try {
            // Check if the row already exists in the destination
            boolean rowExists = rowExistsInDestination(destinationConnection, tableName, rowData);
            if (rowExists) {
                // If the row exists, update it instead of inserting a new row
                updateRow(destinationConnection, tableName, columnNames, rowData);
            } else {
                // If the row doesn't exist, insert it
                insertRow(destinationConnection, tableName, columnNames, rowData);
            }
            System.out.println("Row synchronized for table " + tableName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean rowExistsInDestination(Connection destinationConnection, String tableName, Map<String, Object> rowData) throws SQLException {
        if (tableName == null || tableName.isEmpty()) {
            System.out.println("Invalid table name: " + tableName);
            return false;
        }

        StringBuilder whereClause = new StringBuilder();
        List<String> primaryKeyColumns = getPrimaryKeyColumns(destinationConnection, tableName);
        if (primaryKeyColumns.isEmpty()) {
            System.out.println("No primary key defined for table: " + tableName);
            return false;
        }
        for (String primaryKeyColumn : primaryKeyColumns) {
            whereClause.append(primaryKeyColumn).append("=? AND ");
        }
        whereClause.delete(whereClause.length() - 5, whereClause.length()); // Remove the trailing " AND "

        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE ").append(whereClause);

        PreparedStatement statement = destinationConnection.prepareStatement(selectQuery.toString());
        int parameterIndex = 1;
        for (String primaryKeyColumn : primaryKeyColumns) {
            Object value = rowData.get(primaryKeyColumn);
            statement.setObject(parameterIndex++, value);
        }

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count > 0;
    }

    private static void insertRow(Connection destinationConnection, String tableName, List<String> columnNames, Map<String, Object> rowData) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
        for (String columnName : columnNames) {
            sqlBuilder.append(columnName).append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(") VALUES (");
        for (int i = 0; i < columnNames.size(); i++) {
            sqlBuilder.append("?,");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(")");

        PreparedStatement statement = destinationConnection.prepareStatement(sqlBuilder.toString());
        int parameterIndex = 1;
        for (String columnName : columnNames) {
            Object value = rowData.get(columnName);
            statement.setObject(parameterIndex++, value);
        }

        statement.executeUpdate();
    }

    private static void updateRow(Connection destinationConnection, String tableName, List<String> columnNames, Map<String, Object> rowData) throws SQLException {
        List<String> primaryKeyColumns = getPrimaryKeyColumns(destinationConnection, tableName);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("UPDATE ").append(tableName).append(" SET ");
        for (String columnName : columnNames) {
            if (!primaryKeyColumns.contains(columnName)) {
                sqlBuilder.append(columnName).append("=?,");
            }
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the trailing comma

        sqlBuilder.append(" WHERE ");
        for (String primaryKeyColumn : primaryKeyColumns) {
            sqlBuilder.append(primaryKeyColumn).append("=? AND ");
        }
        sqlBuilder.delete(sqlBuilder.length() - 5, sqlBuilder.length()); // Remove the trailing " AND "

        PreparedStatement statement = destinationConnection.prepareStatement(sqlBuilder.toString());
        int parameterIndex = 1;
        for (String columnName : columnNames) {
            if (!primaryKeyColumns.contains(columnName)) {
                Object value = rowData.get(columnName);
                statement.setObject(parameterIndex++, value);
            }
        }
        for (String primaryKeyColumn : primaryKeyColumns) {
            Object value = rowData.get(primaryKeyColumn);
            statement.setObject(parameterIndex++, value);
        }

        statement.executeUpdate();
    }

    public static void main(String[] args) {
        try (Connection localConnection = Connect.getConnection(); Connection cloudConnection = Connect.getCloudConnection()) {

            synchronize(localConnection, cloudConnection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
