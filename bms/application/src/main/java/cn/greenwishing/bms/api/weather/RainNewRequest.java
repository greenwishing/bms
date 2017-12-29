package cn.greenwishing.bms.api.weather;

import cn.greenwishing.bms.api.BaseBmsRequest;

/**
 * 下雨预报
 * @author Wufan
 * @date 2017/12/29
 */
public class RainNewRequest extends BaseBmsRequest<RainNewResponse> {

    private transient long stationId;
    private transient float lat;
    private transient float lng;

    public RainNewRequest(long stationId, float lat, float lng) {
        this.stationId = stationId;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    protected String getRequestUrl() {
        return String.format("http://d3.weather.com.cn/webgis_rain_new/webgis/minute?lat=%s&lon=%s&stationid=%s", lat, lng, stationId);
    }

    @Override
    protected Class<RainNewResponse> getResponseClass() {
        return RainNewResponse.class;
    }
}
