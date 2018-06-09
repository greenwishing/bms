package cn.greenwishing.bms.api.weixin.weapp;

import cn.greenwishing.bms.api.BaseBmsResponse;
import com.google.gson.annotations.SerializedName;

/**
 * @author Wufan
 * @date 2018/6/9
 */
public class AccessTokenResponse extends BaseBmsResponse {

    /**
     * 获取到的凭证
     */
    @SerializedName("access_token")
    private String accessToken;
    /**
     * 凭证有效时间，单位：秒
     */
    @SerializedName("expires_in")
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }
}
