package cn.greenwishing.bms.dto.open;

import cn.greenwishing.bms.domain.open.OpenUser;
import cn.greenwishing.bms.domain.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public class OpenUserDTO {

    private String openid;
    private String nickname;
    private String avatar;

    private String userGuid;
    private String userAccount;

    public OpenUserDTO(OpenUser openUser) {
        this.openid = openUser.openid();
        this.nickname = openUser.nickname();
        this.avatar = openUser.avatar();
        User user = openUser.user();
        if (user != null) {
            this.userGuid = user.guid();
            this.userAccount = user.account();
        }
    }

    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public static List<OpenUserDTO> valueOf(List<OpenUser> users) {
        List<OpenUserDTO> list = new ArrayList<>();
        for (OpenUser user : users) {
            OpenUserDTO userDTO = new OpenUserDTO(user);
            list.add(userDTO);
        }
        return list;
    }
}
