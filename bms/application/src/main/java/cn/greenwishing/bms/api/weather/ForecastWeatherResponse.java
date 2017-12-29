package cn.greenwishing.bms.api.weather;

import cn.greenwishing.bms.api.BaseBmsResponse;
import com.google.gson.annotations.SerializedName;

/**
 * {"WS": "2级","WD": "东风","temp": 14, "weather": "阴","weathercode": "d02","humidity": 83}
 * @author Wufan
 * @date 2017/12/29
 */
public class ForecastWeatherResponse extends BaseBmsResponse {

    @SerializedName("weather")
    private String weather;

    @SerializedName("temp")
    private String temp;

    public String getWeather() {
        return weather;
    }

    public String getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "实时天气：" + weather + '，' + temp + '℃';
    }
}
