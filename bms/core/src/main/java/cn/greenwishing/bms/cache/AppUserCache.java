package cn.greenwishing.bms.cache;

import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.spring.SpringBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Wufan
 * Date: 2016/8/13
 */
public class AppUserCache {

    private static final Map<String, String> appUserMap = new HashMap<>();

    public static String get(String appId) {
        if (appUserMap.containsKey(appId)) {
            return appUserMap.get(appId);
        }
        String userGuid = getUserRepository().findUserGuidByAppId(appId);
        appUserMap.put(appId, userGuid);
        return userGuid;
    }

    private static UserRepository instance = null;

    private static UserRepository getUserRepository() {
        if (instance == null) instance = SpringBeanFactory.getBean(UserRepository.class);
        return instance;
    }
}