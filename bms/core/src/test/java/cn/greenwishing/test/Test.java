package cn.greenwishing.test;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:42
 */
public class Test {

    public static void main(String[] args) {
        MySqlTempTable table = new MySqlTempTable(User.class);
        System.out.println(table.createSql());
        System.out.println(table.dropSql());
    }
}
