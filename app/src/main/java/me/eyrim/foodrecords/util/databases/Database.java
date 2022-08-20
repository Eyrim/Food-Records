package me.eyrim.foodrecords.util.databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

//TODO: SANITISE THIS OMG THIS NEEDS IT SO BAD

public class Database {
    private String _DBFilePath = "jdbc:sqlite:";
    private Boolean _PathSet;

    public Database(String filePath) {
        _DBFilePath += filePath;
        _PathSet = true;
    }

    public Boolean createTable(Table table) {
        try {
            if (_PathSet) {
                Connection conn = connect();
                Statement statement = conn.createStatement();
                String sql = generateSqlFromTable(table);

                statement.executeUpdate(sql);
                statement.close();
                //conn.commit();
                conn.close();

                return true;
            }
        } catch (SQLException ignored) {
            return false;
        }

        return false;
    }

    public Boolean insertData(String tableName, Hashtable<?,?> data) {
        try {
            if (_PathSet) {
                Connection conn = connect();
                Statement statement = conn.createStatement();
                String sql = generateInsertSql(data, tableName);

                statement.executeUpdate(sql);
                statement.close();
                //conn.commit();
                conn.close();

                return true;
            }
            return false;
        }  catch (Exception e) { //catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public ResultSet selectDataSingleTable(String[] columnNames, String tableName) {
        try {
            if (_PathSet) {
                Connection conn = connect();
                Statement statement = conn.createStatement();
                String sql = generateSingleTableSelectSQL(columnNames, tableName);

                return statement.executeQuery(sql);
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    private Connection connect() {
        try {
            Connection conn;

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(_DBFilePath);

            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    private String generateSqlFromTable(Table table) {
        StringBuilder sql = new StringBuilder();
        Column[] columns = table.getColumns();

        sql.append("CREATE TABLE ");
        sql.append(table.getTableName());
        sql.append("(");

        for (Column c : columns) {
            sql.append(c.getColumnName());
            sql.append(" ");
            sql.append(c.getColumnType());
            sql.append(" ");
            if (c.isPrimaryKey()) {
                sql.append("PRIMARY KEY ");
            }
            if (!c.isNullable()) {
                sql.append("NOT NULL");
            }
            sql.append(",");
        }

        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(");");

        return sql.toString();
    }

    private String generateInsertSql(Hashtable<?,?> data, String tableName) {
        StringBuilder sql = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Enumeration<?> keysIter = data.keys();
        Enumeration<?> valuesIter = data.elements();

        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append("(");

        values.append("VALUES(");

        while (keysIter.hasMoreElements()) {
            sql.append(keysIter.nextElement());
            sql.append(",");

            values.append('\'');
            values.append(valuesIter.nextElement());
            values.append('\'');
            values.append(",");
        }

        // Remove last comma
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        values = new StringBuilder(values.substring(0, values.length() - 1));

        sql.append(")");
        values.append(");");

        sql.append(values.toString());

        return sql.toString();
    }

    private String generateSingleTableSelectSQL(String[] columnNames, String tableName) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");

        for (String s : columnNames) {
            sql.append(s);
            sql.append(",");
        }

        sql = new StringBuilder(sql.substring(0, sql.length() - 1));

        sql.append(" ");
        sql.append("FROM ");
        sql.append(tableName);
        sql.append(";");

        return sql.toString();
    }
}
