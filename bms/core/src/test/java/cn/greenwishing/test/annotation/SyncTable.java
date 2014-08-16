package cn.greenwishing.test.annotation;

import java.lang.annotation.*;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:42
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SyncTable {
    String value();
}
