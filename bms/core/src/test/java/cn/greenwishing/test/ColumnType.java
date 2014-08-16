package cn.greenwishing.test;

import java.lang.reflect.Type;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:48
 */
public enum ColumnType {
    INT("int"),
    BIGINT("bigint"),
    VARCHAR("varchar(255)"),
    TEXT("text"),
    DATE("date"),
    DATETIME("datetime"),
    DECIMAL("decimal(10,2)"),

    PRIMARY_KEY("primary key"),
    AUTO_INCREMENT("auto_increment"),
    ;

    private String type;

    ColumnType(String type) {
        this.type = type;
    }

    public String getName() {
        return name();
    }

    public String getType() {
        return type;
    }
}
