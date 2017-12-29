package cn.greenwishing.bms.api.weather;

import cn.greenwishing.bms.api.BaseBmsRequest;

/**
 * 实时天气预报
 * @author Wufan
 * @date 2017/12/29
 */
public class ForecastWeatherRequest extends BaseBmsRequest<ForecastWeatherResponse> {

    private transient float lat;
    private transient float lng;

    public ForecastWeatherRequest(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    protected String getRequestUrl() {
        return String.format("http://forecast.weather.com.cn/town/api/v1/sk?lat=%s&lng=%s", lat, lng);
    }

    @Override
    protected Class<ForecastWeatherResponse> getResponseClass() {
        return ForecastWeatherResponse.class;
    }
}
