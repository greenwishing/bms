package cn.greenwishing.bms.dto.statistics.mobiscroll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Wufan
 * @date 2017/11/11
 */
public class Wheel {

    private String label;
    private List<WheelData> data = new ArrayList<>();

    public Wheel(String label) {
        this.label = label;
    }

    public void add(WheelData data) {
        this.data.add(data);
    }

    public String getLabel() {
        return label;
    }

    public List<WheelData> getData() {
        return data;
    }

    public Wheel sort() {
        Collections.sort(data);
        return this;
    }
}
