package cn.greenwishing.bms.cache;

import cn.greenwishing.bms.utils.JodaUtils;
import org.joda.time.DateTime;

/**
 * @author Frank wu
 * @date 2017/5/13
 */
public class ValidationCode {

    private String code;
    private DateTime expiryTime = JodaUtils.now().plusMinutes(30); // 默认 30 分钟内有效

    public ValidationCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isExpired() {
        return expiryTime.compareTo(JodaUtils.now()) >= 0;
    }
}
