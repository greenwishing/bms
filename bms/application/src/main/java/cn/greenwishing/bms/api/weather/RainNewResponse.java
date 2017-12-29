package cn.greenwishing.bms.api.weather;

import cn.greenwishing.bms.api.BaseBmsResponse;
import com.google.gson.annotations.SerializedName;

/**
 * {"time":"2017-10-22 17:20",
 * "values":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.02,0.02,0.02,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],
 * "status":"true",
 * "times":["2017-10-22 17:10:00","2017-10-22 17:15:00","2017-10-22 17:20:00","2017-10-22 17:25:00",
 * "2017-10-22 17:30:00","2017-10-22 17:35:00","2017-10-22 17:40:00","2017-10-22 17:45:00","2017-10-22 17:50:00",
 * "2017-10-22 17:55:00","2017-10-22 18:00:00","2017-10-22 18:05:00","2017-10-22 18:10:00","2017-10-22 18:15:00",
 * "2017-10-22 18:20:00","2017-10-22 18:25:00","2017-10-22 18:30:00","2017-10-22 18:35:00","2017-10-22 18:40:00",
 * "2017-10-22 18:45:00","2017-10-22 18:50:00","2017-10-22 18:55:00","2017-10-22 19:00:00","2017-10-22 19:05:00"],
 * "ph":"rain",
 * "msg":"55分钟后开始下小雨，不过70分钟后雨就停了"}
 * @author Wufan
 * @date 2017/12/29
 */
public class RainNewResponse extends BaseBmsResponse {

    @SerializedName("msg")
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
