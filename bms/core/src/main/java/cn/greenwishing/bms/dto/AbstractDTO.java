package cn.greenwishing.bms.dto;

import java.io.Serializable;

/**
 * @author Wufan
 * @date 2018/1/13
 */
public class AbstractDTO implements Serializable {

    protected String userGuid;

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
}
