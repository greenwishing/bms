package cn.greenwishing.bms.dto.statistics.highcharts;

import cn.greenwishing.bms.utils.JodaUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author Wufan
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
            data.name = new SimpleDateFormat(JodaUtils.MONTH_CN_FORMAT).format(date);
        }
        Object r1 = result[1];
        if (r1 != null) {
            data.y = ((BigDecimal) r1).floatValue();
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
