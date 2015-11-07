package cn.greenwishing.bms.persistence.hibernate.user;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wu Fan
 */
@Repository("userRepository")
public class UserRepositoryHibernate extends AbstractRepositoryHibernate implements UserRepository {
    @Override
    @SuppressWarnings("unchecked")
    public User findUserByAccount(String account) {
        List<User> list = getHibernateTemplate().find("from User u where u.account=?", account);
        return list.isEmpty() ? null : list.get(0);
    }
}
