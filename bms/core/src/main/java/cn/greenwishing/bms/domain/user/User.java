package cn.greenwishing.bms.domain.user;

import cn.greenwishing.bms.domain.AbstractDomain;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Frank wu
 */
@Entity
@Table(name = "`user`")
public class User extends AbstractDomain {

    public static final String ADMIN_GUID = "cd4b1012-e53f-47bf-b0db-91a7fe9fb09b";

    @Column(name = "account")
    private String account;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastLoginTime;

    @Column(name = "`status`")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ENABLED;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateStatus(UserStatus status) {
        this.status = status;
    }

    public String username() {
        return username;
    }

    public String account() {
        return account;
    }

    public String password() {
        return password;
    }

    public DateTime lastLoginTime() {
        return lastLoginTime;
    }

    public UserStatus status() {
        return status;
    }
}
