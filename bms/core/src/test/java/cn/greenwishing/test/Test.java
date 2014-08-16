package cn.greenwishing.test;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigInteger;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:42
 */
public class Test {

    public static void main(String[] args) {
        Family family = new Family(1, "family");
        System.out.println(TempTable.getCreateSql(family));
        System.out.println(TempTable.getInsertSql(family));
        System.out.println(TempTable.getDropSql(family));

        User user = new User(1, "user", new LocalDate(1989, 8, 30), new DateTime(), BigInteger.valueOf(new DateTime().getMillis()));
        System.out.println(TempTable.getCreateSql(user));
        System.out.println(TempTable.getInsertSql(user));
        System.out.println(TempTable.getDropSql(user));

    }
}
