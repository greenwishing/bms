package cn.greenwishing.bms.api.weather;

import cn.greenwishing.bms.api.BaseBmsRequest;

/**
 * @author Wufan
 * @date 2017/12/29
 */
public class CityWeatherInfoRequest extends BaseBmsRequest<CityWeatherInfoResponse> {

    private transient long stationId;

    public CityWeatherInfoRequest(long stationId) {
        this.stationId = stationId;
    }

    @Override
    protected String getRequestUrl() {
        return String.format("http://www.weather.com.cn/data/cityinfo/%s.html", stationId);
    }

    @Override
    protected Class<CityWeatherInfoResponse> getResponseClass() {
        return CityWeatherInfoResponse.class;
    }
}
