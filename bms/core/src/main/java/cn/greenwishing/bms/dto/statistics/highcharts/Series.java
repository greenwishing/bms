package cn.greenwishing.bms.dto.statistics.highcharts;

import cn.greenwishing.bms.domain.billing.BillingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Frank wu
 * @date 2015/6/23.
 */
public class Series {

    private String name;
    private List<SeriesData> data = new ArrayList<>();

    public static Series valueOf(BillingType billingType, List<Object[]> results) {
        Series obj = new Series();
        obj.name = billingType.getLabel();
        for (Object[] result : results) {
            SeriesData data = SeriesData.valueOf(result);
            obj.data.add(data);
        }
        Collections.sort(obj.data);
        return obj;
    }

    public String getName() {
        return name;
    }

    public List<SeriesData> getData() {
        return data;
    }
}
