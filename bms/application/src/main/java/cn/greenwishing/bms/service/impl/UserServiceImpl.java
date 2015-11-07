package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.service.UserService;
import cn.greenwishing.bms.shared.PublicUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Wu Fan
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByAccount(username);
        if (user == null) {
            return new PublicUserDetails();
        }
        return new PublicUserDetails(user);
    }
}
