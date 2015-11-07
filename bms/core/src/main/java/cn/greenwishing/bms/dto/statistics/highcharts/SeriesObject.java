package cn.greenwishing.bms.dto.statistics.highcharts;

import cn.greenwishing.bms.domain.billing.BillingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Wufan
 * @date 2015/6/23.
 */
public class SeriesObject {

    private String name;
    private List<SeriesObjectData> data = new ArrayList<>();

    public static SeriesObject valueOf(BillingType billingType, List<Object[]> results) {
        SeriesObject obj = new SeriesObject();
        obj.name = billingType.getLabel();
        for (Object[] result : results) {
            SeriesObjectData data = SeriesObjectData.valueOf(result);
            obj.data.add(data);
        }
        Collections.sort(obj.data);
        return obj;
    }

    public String getName() {
        return name;
    }

    public List<SeriesObjectData> getData() {
        return data;
    }
}
