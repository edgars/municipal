package app;

import app.metamodel.Field;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCConnectionManager {

    private static Connection conn = null;
    private static JDBCConnectionManager me = new JDBCConnectionManager();

    private JDBCConnectionManager() {

    }

    public static JDBCConnectionManager getInstance() {

        return me;
    }

    public static Connection getConnection() {

        return conn;
    }

    private Connection getConnection(String driver, String url, String user, String pass) {
        try {

            conn = DriverManager.getConnection(url, user, pass);

            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<String> getTableNames(String driver, String url, String user, String pass) {

        Connection localConn = getConnection(driver, url, user, pass);

        try {
            // Gets the database metadata
            DatabaseMetaData dbmd = localConn.getMetaData();

            // Specify the type of object; in this case we want tables
            String[] types = {"TABLE"};
            ResultSet resultSet = dbmd.getTables(null, null, "%", types);

            List<String> tables = new java.util.ArrayList<String>();
            while (resultSet.next()) {

                tables.add(resultSet.getString(3));
            }
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public java.util.List<Field> getFileds(Connection conn, String selectedTableName) {
        try {
            // Create a result set
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + selectedTableName);

            // Get result set meta data
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            java.util.List<Field> fields = new ArrayList<Field>();
            Field field = null;

            // Get the column names; column indices start from 1
            for (int i = 1; i < numColumns + 1; i++) {
                field = new Field();
                field.setName(rsmd.getColumnName(i).toLowerCase());
                field.setType(rsmd.getColumnTypeName(i));
                // Get the name of the column's table name
                //String tableName = rsmd.getTableName(i);
                field.setPrecision(Integer.toString(rsmd.getPrecision(i)));

                //field.setJavaType(EnviromentProperties.getInstance().replaceDataType(rsmd.getColumnTypeName(i)));

                fields.add(field);
            }
            return fields;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

