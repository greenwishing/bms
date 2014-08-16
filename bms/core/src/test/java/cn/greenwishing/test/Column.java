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
}
