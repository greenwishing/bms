package cn.greenwishing.bms.dto;

import java.io.Serializable;

/**
 * @author Wufan
 * @date 2018/1/13
 */
public class AbstractDTO implements Serializable {

    protected String userGuid;
    protected String formId;
    protected String openId;

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
