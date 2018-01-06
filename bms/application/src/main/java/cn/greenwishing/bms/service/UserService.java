package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.dto.user.UserPagingDTO;

/**
 * @author Frank wu
 */
public interface UserService {
    UserDTO findByAccount(String account);

    UserPagingDTO loadUserPaging(UserPagingDTO pagingDTO);

    UserDTO saveOrUpdateUser(UserDTO userDTO);

    UserDTO loadByGuid(String guid);
}
