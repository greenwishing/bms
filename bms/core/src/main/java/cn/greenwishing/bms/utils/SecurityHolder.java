package cn.greenwishing.bms.utils;

import cn.greenwishing.bms.shared.PublicUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Wu Fan
 */
public class SecurityHolder {

    public static PublicUserDetails get() {
        return (PublicUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUserGuid() {
        return get().getGuid();
    }
}
