package cn.greenwishing.test;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:42
 */
public class Test {

    public static void main(String[] args) {
        TempTable instance1 = TempTableFactory.getInstance(Family.class);
        System.out.println(instance1);
        TempTable instance2 = TempTableFactory.getInstance(User.class);
        System.out.println(instance2);
        TempTable instance3 = TempTableFactory.getInstance(Family.class);
        System.out.println(instance3);
        System.out.println(instance1.createSql());
        System.out.println(instance2.createSql());
        System.out.println(instance3.createSql());
        System.out.println(instance1.dropSql());
        System.out.println(instance2.dropSql());
        System.out.println(instance3.dropSql());

    }
}
