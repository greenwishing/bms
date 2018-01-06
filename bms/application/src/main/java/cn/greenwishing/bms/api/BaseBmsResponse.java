package cn.greenwishing.bms.api;

import com.google.gson.annotations.SerializedName;

/**
 * @author Wufan
 * @date 2017/12/29
 */
public abstract class BaseBmsResponse implements BmsResponse {

    @SerializedName("errcode")
    private Integer errorCode;
    @SerializedName("errmsg")
    private String errorMessage;

    /**
     * 原始响应内容
     */
    private transient String body;

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getBody() {
        return body;
    }
}
