package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.open.OpenUserDTO;
import cn.greenwishing.bms.dto.open.WeAppUserInfo;
import cn.greenwishing.bms.dto.user.UserDTO;

import java.util.List;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public interface WeAppService {
    OpenUserDTO register(WeAppUserInfo userInfo);

    void bindOpenUser(String openid, String userGuid);

    void generateDefaultData(String userGuid);

    UserDTO loadUserByOpenid(String openid);

    List<OpenUserDTO> loadAllOpenUser();

    void uploadAvatar(String openid);

    void updateAccount(String userGuid, String account, String password);
}
