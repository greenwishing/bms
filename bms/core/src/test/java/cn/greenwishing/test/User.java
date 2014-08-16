package cn.greenwishing.test;


import cn.greenwishing.test.annotation.SyncColumn;
import cn.greenwishing.test.annotation.SyncTable;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * @author Wufan
 * @datetime 2014-08-16 10:42
 */
@SyncTable("user")
public class User {
    @SyncColumn(value = "id", types = {ColumnType.INT, ColumnType.AUTO_INCREMENT, ColumnType.PRIMARY_KEY})
    private Integer id;
    @SyncColumn(value = "name", types = ColumnType.VARCHAR)
    private String name;
    @SyncColumn(value = "date_of_born", types = ColumnType.DATE)
    private LocalDate dob;
    @SyncColumn(value = "creation_time", types = ColumnType.DATETIME)
    private DateTime creationTime;
}
