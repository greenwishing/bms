package cn.greenwishing.bms.utils;

import cn.greenwishing.bms.cache.AppUserCache;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.shared.PublicUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Frank wu
 */
public class SecurityHolder {

    public static final String ANONYMOUS_USER = "anonymousUser";

    public static Authentication get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUserGuid() {
        Object principal = get().getPrincipal();
        if (principal instanceof String && !ANONYMOUS_USER.equals(principal)) { // is app
            String appId = (String) principal;
            return AppUserCache.get(appId);
        } else if (principal instanceof PublicUserDetails) {
            PublicUserDetails user = (PublicUserDetails) principal;
            return user.getGuid();
        }
        return null;
    }

    public static Integer getUserId() {
        Object principal = get().getPrincipal();
        if (principal instanceof PublicUserDetails) {
            PublicUserDetails user = (PublicUserDetails) principal;
            return user.getId();
        }
        return null;
    }

    public static boolean isAdminAccount() {
        Authentication authentication = get();
        return authentication != null && User.ADMIN_GUID.equalsIgnoreCase(getUserGuid());
    }
}
