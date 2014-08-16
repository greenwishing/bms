package cn.greenwishing.test;

import cn.greenwishing.test.annotation.SyncColumn;
import cn.greenwishing.test.annotation.SyncTable;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:44
 */
public class TempTable {

    private Class<?> clazz;
    private String table;

    private Map<String, Column> columns = new LinkedHashMap<>();

    private String createSql;
    private String dropSql;

    public TempTable(Class<?> clazz) {
        this.clazz = clazz;

        init();
    }

    private void init() {
        if (clazz.isAnnotationPresent(SyncTable.class)) {
            SyncTable table = clazz.getAnnotation(SyncTable.class);
            this.table = table.value();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                if (field.isAnnotationPresent(SyncColumn.class)) {
                    SyncColumn syncColumn = field.getAnnotation(SyncColumn.class);
                    Column column = new Column(name, syncColumn.value(), syncColumn.types());
                    this.columns.put(syncColumn.value(), column);
                }
            }
        }
        generateCreateSql();
        generateDropSql();
    }

    public static String getCreateSql(Object obj) {
        return TempTableFactory.getInstance(obj.getClass()).createSql();
    }

    public static String getDropSql(Object obj) {
        return TempTableFactory.getInstance(obj.getClass()).dropSql();
    }

    public static String getInsertSql(Object obj) {
        Class clazz = obj.getClass();
        TempTable instance = TempTableFactory.getInstance(clazz);
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(instance.table);
        sql.append(" (");
        Iterator<String> keySet = instance.columns.keySet().iterator();
        while (keySet.hasNext()) {
            String column = keySet.next();
            sql.append(column);
            if (keySet.hasNext()) sql.append(", ");
        }
        sql.append(") values (");
        Iterator<Column> values = instance.columns.values().iterator();
        while (values.hasNext()) {
            Column column = values.next();
            sql.append(TempTable.fieldValue(obj, column));
            if (values.hasNext()) sql.append(", ");
        }
        sql.append(")");
        return sql.toString();
    }

    private void generateCreateSql() {
        StringBuilder sql = new StringBuilder("create temporary table ");
        sql.append(table);
        sql.append(" (");
        Iterator<Column> iterator = columns.values().iterator();
        while (iterator.hasNext()) {
            Column column = iterator.next();
            sql.append(column.getSqlDefine());
            if (iterator.hasNext()) sql.append(", ");
        }
        sql.append(")");
        this.createSql =  sql.toString();
    }

    private void generateDropSql() {
        this.dropSql = "drop temporary table " + table;
    }

    private static String fieldValue(Object obj, Column column) {
        try {
            Class clazz = obj.getClass();
            Field field = clazz.getDeclaredField(column.getField());
            field.setAccessible(true);
            ColumnType columnType = column.getTypes()[0];
            Object value = field.get(obj);
            switch (columnType) {
                case INT:
                    return String.valueOf(value);
                case BIGINT:
                    return String.valueOf(value);
                case VARCHAR:
                case TEXT:
                    return '\'' + String.valueOf(value) + '\'';
                case DATE:
                    return '\'' + ((LocalDate) value).toString("yyyy-MM-dd") + '\'';
                case DATETIME:
                    return '\'' + ((DateTime) value).toString("yyyy-MM-dd HH:mm") + '\'';
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String createSql() {
        return createSql;
    }

    public String dropSql() {
        return dropSql;
    }
}
