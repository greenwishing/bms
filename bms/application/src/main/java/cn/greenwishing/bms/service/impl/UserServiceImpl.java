package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.service.UserService;
import cn.greenwishing.bms.shared.PublicUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Wu Fan
 */
public class UserServiceImpl implements UserService, UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = User.findByAccount(username);
        if (user == null) {
            return new PublicUserDetails();
        }
        return new PublicUserDetails(user);
    }
}
