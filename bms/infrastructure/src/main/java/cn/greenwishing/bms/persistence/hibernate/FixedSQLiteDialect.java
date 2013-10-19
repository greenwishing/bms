package cn.greenwishing.bms.persistence.hibernate;

import com.applerao.hibernatesqlite.dialect.SQLiteDialect;

/**
 * @author Wu Fan
 */
public class FixedSQLiteDialect extends SQLiteDialect {

    /**
     * 解决SQLite分页问题
     */
    @Override
    public boolean bindLimitParametersInReverseOrder() {
        return true;
    }
}
