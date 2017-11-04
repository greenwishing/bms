package cn.greenwishing.bms.cache;

import cn.greenwishing.bms.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank wu
 * @date 2017/5/13
 */
public class ValidationCodeCache {

    private static final Map<String, ValidationCode> codes = new HashMap<>();

    public static String gen(String email) {
        String randomCode = StringUtils.random(8);
        codes.put(email, new ValidationCode(randomCode));
        return randomCode;
    }

    public static boolean isValid(String email, String code) {
        if (!codes.containsKey(email)) return false;
        ValidationCode validationCode = codes.get(email);
        if (validationCode == null || validationCode.isExpired()) return false;
        if (!validationCode.getCode().equals(code)) return false;
        return true;
    }
}
