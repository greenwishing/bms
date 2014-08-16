package cn.greenwishing.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wufan
 * @datetime 2014-08-16 11:52
 */
public class TempTableFactory {
    private static final Map<Class, TempTable> factory = new ConcurrentHashMap<>();

    public static TempTable getInstance(Class<?> clazz) {
        TempTable table = factory.get(clazz);
        if (table == null) {
            table = new TempTable(clazz);
            factory.put(clazz, table);
        }
        return table;
    }
}
