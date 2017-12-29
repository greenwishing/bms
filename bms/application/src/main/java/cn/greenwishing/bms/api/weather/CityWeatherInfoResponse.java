package cn.greenwishing.bms.api.weather;

import cn.greenwishing.bms.api.BaseBmsResponse;
import com.google.gson.annotations.SerializedName;

/**
 * 城市一日天气
 * @author Wufan
 * @date 2017/12/29
 */
public class CityWeatherInfoResponse extends BaseBmsResponse {

    @SerializedName("weatherinfo")
    private WeatherInfo weatherInfo;

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }
}
