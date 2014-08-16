package cn.greenwishing.test;


import cn.greenwishing.test.annotation.SyncColumn;
import cn.greenwishing.test.annotation.SyncTable;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigInteger;

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
    @SyncColumn(value = "update_time", types = ColumnType.BIGINT)
    private BigInteger updateTime;
    @SyncColumn(value = "description", types = ColumnType.TEXT)
    private String description;

    public User(Integer id, String name, LocalDate dob, DateTime creationTime, BigInteger updateTime) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }
}
