package cn.greenwishing.test;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:49
 */
public class Column {

    private String field;
    private String column;
    private ColumnType[] types;

    public Column(String field, String column, ColumnType[] types) {
        this.field = field;
        this.column = column;
        this.types = types;
    }

    public String getField() {
        return field;
    }

    public String getColumn() {
        return column;
    }

    public ColumnType[] getTypes() {
        return types;
    }

    public String getSqlDefine() {
        StringBuilder define = new StringBuilder(column);
        for (ColumnType type : types) {
            define.append(" ").append(type.getType());
        }
        return define.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column column1 = (Column) o;

        if (column != null ? !column.equals(column1.column) : column1.column != null) return false;
        if (field != null ? !field.equals(column1.field) : column1.field != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (column != null ? column.hashCode() : 0);
        return result;
    }
}
