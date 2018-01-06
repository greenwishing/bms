package cn.greenwishing.bms.api.weixin.weapp;

import cn.greenwishing.bms.api.BaseBmsResponse;
import com.google.gson.annotations.SerializedName;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public class JSCode2SessionResponse extends BaseBmsResponse {

    /**
     * 用户唯一标识
     */
    @SerializedName("openid")
    private String openid;
    /**
     * 会话密钥
     */
    @SerializedName("session_key")
    private String sessionKey;
    /**
     * 用户在开放平台的唯一标识符。本字段在满足一定条件的情况下才返回。具体参看UnionID机制说明
     */
    @SerializedName("unionid")
    private String unionId;


    public String getOpenid() {
        return openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }
}
