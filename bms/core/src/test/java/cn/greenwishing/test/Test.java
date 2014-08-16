package cn.greenwishing.test;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:42
 */
public class Test {

    public static void main(String[] args) {
        TempTable instance1 = TempTableFactory.getInstance(Family.class);
        System.out.println(instance1.createSql());
        System.out.println(instance1.dropSql());
        TempTable instance2 = TempTableFactory.getInstance(User.class);
        System.out.println(instance2);
        System.out.println(instance2.createSql());
        System.out.println(instance2.dropSql());
        System.out.println(TempTable.generateInsertSql(new Family(1, "ffff")));
        System.out.println(TempTable.generateInsertSql(new User(1, "wufan", new LocalDate(1989,8,30), new DateTime(), BigInteger.valueOf(new DateTime().getMillis()))));

    }
}
