package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.dto.user.UserPagingDTO;
import cn.greenwishing.bms.service.UserService;
import cn.greenwishing.bms.shared.PublicUserDetails;
import cn.greenwishing.bms.utils.MD5Utils;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.UserPaging;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Frank wu
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByAccount(username);
        if (user == null) {
            return new PublicUserDetails();
        }
        return new PublicUserDetails(user);
    }

    @Override
    public UserDTO findByAccount(String account) {
        User user = userRepository.findUserByAccount(account);
        return user == null ? null : new UserDTO(user);
    }

    @Override
    public UserPagingDTO loadUserPaging(UserPagingDTO pagingDTO) {
        UserPaging paging = userRepository.findUserPaging(pagingDTO.toPaging());
        return pagingDTO.convertResult(paging);
    }

    @Override
    public UserDTO saveOrUpdateUser(UserDTO userDTO) {
        User user;
        String guid = userDTO.getGuid();
        String username = userDTO.getUsername();
        if (ValidationUtils.isNotEmpty(guid)) {
            user = userRepository.findByGuid(User.class, guid);
        } else {
            String md5Password = MD5Utils.md5(userDTO.getPassword());
            user = new User(userDTO.getAccount(), md5Password);
        }
        user.updateUsername(username);
        user.updateStatus(userDTO.getStatus());
        userRepository.saveOrUpdate(user);
        return new UserDTO(user);
    }

    @Override
    public UserDTO loadByGuid(String guid) {
        User user = userRepository.findByGuid(User.class, guid);
        return new UserDTO(user);
    }
}
