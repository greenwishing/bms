package cn.greenwishing.bms.service;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.dto.user.UserPagingDTO;

/**
 * @author Frank wu
 */
public interface UserService {
    User findByAccount(String account);

    UserPagingDTO loadUserPaging(UserPagingDTO pagingDTO);

    void saveOrUpdateUser(UserDTO userDTO);

    UserDTO loadByGuid(String guid);
}
