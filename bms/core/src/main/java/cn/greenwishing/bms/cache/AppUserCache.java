package cn.greenwishing.bms.cache;

import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.spring.SpringBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank wu
 * @date 2016/8/13
 */
public class AppUserCache {

    private static final Map<String, String> APP_USER_MAP = new HashMap<>();

    public static String get(String appId) {
        if (APP_USER_MAP.containsKey(appId)) {
            return APP_USER_MAP.get(appId);
        }
        String userGuid = getUserRepository().findUserGuidByAppId(appId);
        APP_USER_MAP.put(appId, userGuid);
        return userGuid;
    }

    public static void clear() {
        APP_USER_MAP.clear();
    }

    private static UserRepository instance = null;
    private static UserRepository getUserRepository() {
        if (instance == null) {
            instance = SpringBeanFactory.getBean(UserRepository.class);
        }
        return instance;
    }
}