package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.open.OpenUserDTO;
import cn.greenwishing.bms.dto.open.WeAppUserInfo;
import cn.greenwishing.bms.dto.user.UserDTO;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public interface WeAppService {
    OpenUserDTO saveOpenUserInfo(WeAppUserInfo userInfo);

    void bindOpenUser(String openid, String userGuid);

    UserDTO fastRegister(WeAppUserInfo userInfo);

    void generateDefaultData(String userGuid);
}
