package cn.greenwishing.bms.domain.user;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;
import org.joda.time.DateTime;

/**
 * @author Wu Fan
 */
public class User extends AbstractDomain {

    public static final String ADMIN_GUID = "cd4b1012-e53f-47bf-b0db-91a7fe9fb09b";

    private String username;
    private String account;
    private String password;
    private DateTime lastLoginTime;
    private UserStatus status;

    private static UserRepository repository;

    private static UserRepository getRepository() {
        if (repository == null) {
            repository = InstanceFactory.getInstance(UserRepository.class);
        }
        return repository;
    }

    public static User findByGuid(String guid) {
        return getRepository().findByGuid(User.class, guid);
    }

    public static User findByAccount(String account) {
        return getRepository().findUserByAccount(account);
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
