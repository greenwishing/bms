package cn.greenwishing.test;

import cn.greenwishing.test.annotation.SyncColumn;
import cn.greenwishing.test.annotation.SyncTable;

/**
 * @author Wufan
 * @datetime 2014-08-16 12:00
 */
@SyncTable("family")
public class Family {
    @SyncColumn(value = "id", types = {ColumnType.INT, ColumnType.AUTO_INCREMENT, ColumnType.PRIMARY_KEY})
    private Integer id;
    @SyncColumn(value = "name", types = ColumnType.VARCHAR)
    private String name;
}
