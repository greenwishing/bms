package cn.greenwishing.bms.cache;

import cn.greenwishing.bms.domain.config.ConfigurationRepository;
import cn.greenwishing.bms.spring.SpringBeanFactory;
import cn.greenwishing.bms.utils.ValidationUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank wu
 * @date 2017/5/13
 */
public class ConfigurationCache {

    private static final Map<String, String> configurations = new HashMap<>();

    public static Integer getInt(String key) {
        return getInt(key, null);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        Number number = getNumber(key, defaultValue);
        return number == null ? defaultValue : number.intValue();
    }

    private static Number getNumber(String key, Number defaultValue) {
        String value = get(key);
        if (ValidationUtils.isAllNumber(value)) {
            return new BigDecimal(value);
        }
        return defaultValue;
    }

    public static Long getLong(String key) {
        return getLong(key, null);
    }

    public static Long getLong(String key, Long defaultValue) {
        Number number = getNumber(key, defaultValue);
        return number == null ? defaultValue : number.longValue();
    }

    public static Float getFloat(String key) {
        return getFloat(key, null);
    }

    public static Float getFloat(String key, Float defaultValue) {
        Number number = getNumber(key, defaultValue);
        return number == null ? defaultValue : number.floatValue();
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return ValidationUtils.isNotEmpty(value) ? value : defaultValue;
    }

    public static String get(String key) {
        String value;
        if (!configurations.containsKey(key)) {
            value = getConfigurationRepository().findByKey(key);
            configurations.put(key, value);
        } else {
            value = configurations.get(key);
        }
        return value;
    }

    public static void clear() {
        configurations.clear();
    }

    public static void update(String key, String value) {
        configurations.put(key, value);
    }

    private static ConfigurationRepository instance = null;
    private static ConfigurationRepository getConfigurationRepository() {
        if (instance == null) instance = SpringBeanFactory.getBean(ConfigurationRepository.class);
        return instance;
    }
}
