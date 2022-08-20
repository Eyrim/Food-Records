package me.eyrim.foodrecords.util.databases;

public class Table {
    private String _TableName;
    private Column[] _Columns;

    public Table(String tableName, Column[] columns) {
        this._TableName = tableName;
        this._Columns = columns;
    }

    public String getTableName() {
        return _TableName;
    }

    public void setTableName(String tableName) {
        this._TableName = tableName;
    }

    public Column[] getColumns() {
        return _Columns;
    }

    public Column GetColumnFromName(String columnName) {
        for (Column c : _Columns) {
            if (c.getColumnName() == columnName) {
                return c;
            }
        }

        return null;
    }
}