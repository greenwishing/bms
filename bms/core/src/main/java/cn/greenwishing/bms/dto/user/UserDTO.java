package cn.greenwishing.bms.dto.user;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserStatus;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.MD5Utils;
import cn.greenwishing.bms.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Wufan
 * Date: 2016/7/13
 */
public class UserDTO {

    private String guid;
    private String username;
    private String account;
    private String lastLoginTime;
    private UserStatus status = UserStatus.ENABLED;

    // reg
    private String password;
    private String retypePassword;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.guid = user.guid();
        this.username = user.username();
        this.account = user.account();
        this.lastLoginTime = JodaUtils.dateTimeToString(user.lastLoginTime());
        this.status = user.status();
    }

    public static List<UserDTO> toDTOs(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public boolean isAdmin() {
        return User.ADMIN_GUID.equals(guid);
    }
}
