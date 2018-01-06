package cn.greenwishing.bms.api.weixin.weapp;

import cn.greenwishing.bms.api.BaseBmsRequest;
import cn.greenwishing.bms.cache.ConfigurationCache;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public class JSCode2SessionRequest extends BaseBmsRequest<JSCode2SessionResponse> {

    /**
     * 小程序登录时获取的 code
     */
    private transient String code;

    public JSCode2SessionRequest(String code) {
        this.code = code;
    }

    @Override
    protected String getRequestUrl() {
        String appId = ConfigurationCache.get("weixin.weapp.appid");
        String appSecret = ConfigurationCache.get("weixin.weapp.appsecret");
        return String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, appSecret, code);
    }

    @Override
    protected Class<JSCode2SessionResponse> getResponseClass() {
        return JSCode2SessionResponse.class;
    }
}
