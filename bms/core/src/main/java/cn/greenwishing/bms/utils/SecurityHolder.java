package cn.greenwishing.bms.utils;

import cn.greenwishing.bms.cache.AppUserCache;
import cn.greenwishing.bms.shared.PublicUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * User: Wu Fan
 */
public class SecurityHolder {

    public static final String ANONYMOUS_USER = "anonymousUser";

    public static Authentication get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUserGuid() {
        Object principal = get().getPrincipal();
        if (principal instanceof String) { // is app
            if (ANONYMOUS_USER.equals(principal)) {
                throw new RuntimeException("Bad Credentials");
            }
            String appId = (String) principal;
            return AppUserCache.get(appId);
        } else if (principal instanceof PublicUserDetails) {
            PublicUserDetails user = (PublicUserDetails) principal;
            return user.getGuid();
        }
        return null;
    }
}
