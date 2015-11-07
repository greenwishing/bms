package cn.greenwishing.bms.domain.user;

import cn.greenwishing.bms.domain.AbstractDomain;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Wu Fan
 */
@Entity
@Table(name = "`user`")
public class User extends AbstractDomain {

    public static final String ADMIN_GUID = "cd4b1012-e53f-47bf-b0db-91a7fe9fb09b";

    @Column(name = "username")
    private String username;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime lastLoginTime;

    @Column(name = "`status`")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

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
