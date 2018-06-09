package cn.greenwishing.bms.api.weixin.weapp;

import cn.greenwishing.bms.api.BaseBmsRequest;
import cn.greenwishing.bms.cache.ConfigurationCache;

/**
 * @author Wufan
 * @date 2018/6/9
 */
public class AccessTokenRequest extends BaseBmsRequest<AccessTokenResponse> {

    @Override
    protected String getRequestUrl() {
        String appId = ConfigurationCache.get("weixin.weapp.appid");
        String appSecret = ConfigurationCache.get("weixin.weapp.appsecret");
        return String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, appSecret);
    }

    @Override
    protected Class<AccessTokenResponse> getResponseClass() {
        return AccessTokenResponse.class;
    }
}
