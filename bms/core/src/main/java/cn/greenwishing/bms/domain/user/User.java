package cn.greenwishing.bms.domain.user;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;

/**
 * @author Wu Fan
 */
public class User extends AbstractDomain {

    private String name;

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

    public String getName() {
        return name;
    }
}
