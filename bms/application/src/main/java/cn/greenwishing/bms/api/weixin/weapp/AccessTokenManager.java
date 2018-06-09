package cn.greenwishing.bms.api.weixin.weapp;

import org.joda.time.DateTime;

/**
 * @author Wufan
 * @date 2018/6/9
 */
public class AccessTokenManager {

    private static AccessToken accessToken;

    public static String get() {
        if (accessToken == null || accessToken.expired()) {
            AccessTokenResponse response = new AccessTokenRequest().execute();
            DateTime expiryTime = new DateTime()
                    .plusSeconds(response.getExpiresIn()).minusSeconds(60);
            accessToken = new AccessToken(response.getAccessToken(), expiryTime);
        }
        return accessToken.token;
    }

    static class AccessToken {
        String token;
        DateTime expiryTime;

        AccessToken(String token, DateTime expiryTime) {
            this.token = token;
            this.expiryTime = expiryTime;
        }

        boolean expired() {
            return expiryTime == null || expiryTime.isBeforeNow();
        }
    }
}
