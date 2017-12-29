package cn.greenwishing.bms.api.weather;

import com.google.gson.annotations.SerializedName;

/**
 * @author Wufan
 * @date 2017/12/29
 */
public class WeatherInfo {

    @SerializedName("city")
    private String city;

    @SerializedName("temp1")
    private String temp1;

    @SerializedName("temp2")
    private String temp2;

    public String getCity() {
        return city;
    }

    public String getTemp1() {
        return temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    @Override
    public String toString() {
        return city + "，夜间" + temp1 + "，白天" + temp2;
    }
}
