package cn.greenwishing.bms.dto.statistics.highcharts;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Frank wu
 * @date 2015/6/23.
 */
public class SeriesObjectData implements Comparable<SeriesObjectData> {

    private Long time;
    private String name;
    private Float y = 0f;

    public static SeriesObjectData valueOf(Object[] result) {
        SeriesObjectData data = new SeriesObjectData();
        Object r0 = result[0];
        if (r0 != null) {
            Date date = (Date) r0;
            data.time = date.getTime();
            DateTime dateTime = new DateTime(data.time);
            String year = dateTime.getMonthOfYear() == 1 ? "YY年" : "";
            data.name = dateTime.toString(year + "MM月");
        }
        Object r1 = result[1];
        if (r1 != null) {
            data.y = ((BigDecimal) r1).abs().floatValue();
        }
        return data;
    }

    public String getName() {
        return name;
    }

    public Float getY() {
        return y;
    }

    @Override
    public int compareTo(SeriesObjectData o) {
        if (this.time == null || o.time == null) return 0;
        return this.time.compareTo(o.time);
    }
}
