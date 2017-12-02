package cn.greenwishing.bms.dto.statistics.mobiscroll;

import java.io.Serializable;

/**
 * @author Wufan
 * @date 2017/11/11
 */
public class WheelData implements Comparable<WheelData> {

    private Integer id;
    private Serializable value;
    private String display;

    private String sign = "";

    public WheelData(Integer id, Serializable value, String display) {
        this.id = id;
        this.value = value;
        this.display = display;
    }

    public WheelData setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public Serializable getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public int compareTo(WheelData t) {
        int result = Integer.compare(this.id, t.id);
        if (result != 0) {
            return result;
        }
        if (this.sign == null) {
            return -1;
        }
        if (t.sign == null) {
            return 1;
        }
        return this.sign.compareTo(t.sign);
    }
}
