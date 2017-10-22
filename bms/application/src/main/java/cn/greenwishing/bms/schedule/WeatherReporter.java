package cn.greenwishing.bms.schedule;

import cn.greenwishing.bms.handler.MailSender;
import cn.greenwishing.bms.utils.BmsClient;
import net.sf.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Wufan
 * @date 2017/10/22
 */
@Component
public class WeatherReporter {

    /**
     * 实时天气预报: http://forecast.weather.com.cn/town/api/v1/sk?lat=30.605027&lng=104.155828
     * {"WS": "2级","WD": "东风","temp": 14, "weather": "阴","weathercode": "d02","humidity": 83}
     *
     * 下雨预报: http://d3.weather.com.cn/webgis_rain_new/webgis/minute?lat=30.605027&lon=104.155828&stationid=101270102
     * {"time":"2017-10-22 17:20","values":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.02,0.02,0.02,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"status":"true","times":["2017-10-22 17:10:00","2017-10-22 17:15:00","2017-10-22 17:20:00","2017-10-22 17:25:00","2017-10-22 17:30:00","2017-10-22 17:35:00","2017-10-22 17:40:00","2017-10-22 17:45:00","2017-10-22 17:50:00","2017-10-22 17:55:00","2017-10-22 18:00:00","2017-10-22 18:05:00","2017-10-22 18:10:00","2017-10-22 18:15:00","2017-10-22 18:20:00","2017-10-22 18:25:00","2017-10-22 18:30:00","2017-10-22 18:35:00","2017-10-22 18:40:00","2017-10-22 18:45:00","2017-10-22 18:50:00","2017-10-22 18:55:00","2017-10-22 19:00:00","2017-10-22 19:05:00"],"ph":"rain","msg":"55分钟后开始下小雨，不过70分钟后雨就停了"}
     *
     */
    @Scheduled(cron = "0 0 7 * * ? *")
    public void report() {
        String stationId = "101270102";
        float lat = 30.605027f;
        float lng = 104.155828f;

        StringBuilder sb = new StringBuilder();
        // 实时天气预报
        String forecastUrl = String.format("http://forecast.weather.com.cn/town/api/v1/sk?lat=%s&lng=%s", lat, lng);
        String forecastResult = BmsClient.doGet(forecastUrl);
        JSONObject forecast = JSONObject.fromObject(forecastResult);
        if (forecast.has("weather")) {
            String weather = forecast.getString("weather");
            sb.append("天气：").append(weather).append("\n");
        }
        if (forecast.has("temp")) {
            String temp = forecast.getString("temp");
            sb.append("温度：").append(temp).append("摄氏度").append("\n");
        }

        // 下雨预报
        String rainUrl = String.format("http://d3.weather.com.cn/webgis_rain_new/webgis/minute?lat=%s&lon=%s&stationid=%s" + stationId, lat, lng, stationId);
        String rainResult = BmsClient.doGet(rainUrl);
        JSONObject rain = JSONObject.fromObject(rainResult);
        if (rain.has("msg")) {
            String msg = rain.getString("msg");
            sb.append(msg).append("\n");
        }
        MailSender.systemSend("greenwishing@msn.cn", "实时天气", sb.toString().replaceAll("\n", "<br/>"));
    }
}
