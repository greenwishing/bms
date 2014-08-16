package cn.greenwishing.test.annotation;

import cn.greenwishing.test.ColumnType;

import java.lang.annotation.*;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:42
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SyncColumn {
    public String value();
    public ColumnType[] types();
}
