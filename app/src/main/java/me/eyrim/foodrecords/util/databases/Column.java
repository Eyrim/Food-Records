package me.eyrim.foodrecords.util.databases;

public class Column {
    private String _ColumnName;
    private String _ColumnType;
    private Boolean _IsPrimaryKey;
    private Boolean _IsNullable;

    public Column(String columnName, String columnType,
                  Boolean isPrimaryKey, Boolean isNullable) {
        this._ColumnName = columnName;
        this._ColumnType = columnType;
        this._IsPrimaryKey = isPrimaryKey;
        this._IsNullable = isNullable;
    }

    public String getColumnName() {
        return _ColumnName;
    }

    public void setColumnName(String columnName) {
        this._ColumnName = columnName;
    }

    public String getColumnType() {
        return _ColumnType;
    }

    public void setColumnType(String columnType) {
        this._ColumnType = columnType;
    }

    public Boolean isPrimaryKey() {
        return _IsPrimaryKey;
    }

    public void setPrimaryKey(Boolean isKey) {
        this._IsPrimaryKey = isKey;
    }

    public Boolean isNullable() {
        return _IsNullable;
    }

    public void setNullable(Boolean isNullable) {
        this._IsNullable = isNullable;
    }
}