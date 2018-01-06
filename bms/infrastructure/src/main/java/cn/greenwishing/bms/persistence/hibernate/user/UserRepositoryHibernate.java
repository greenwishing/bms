package cn.greenwishing.bms.persistence.hibernate.user;

import cn.greenwishing.bms.domain.open.OpenUser;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.paging.UserPaging;
import cn.greenwishing.bms.utils.query.helper.UserQueryHelper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Frank wu
 */
@Repository("userRepository")
public class UserRepositoryHibernate extends AbstractRepositoryHibernate implements UserRepository {
    @Override
    @SuppressWarnings("unchecked")
    public User findUserByAccount(String account) {
        List<User> list = (List<User>) getHibernateTemplate().find("from User u where u.account=?", account);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public UserPaging findUserPaging(UserPaging paging) {
        UserQueryHelper helper = new UserQueryHelper(getHibernateTemplate(), paging);
        return helper.queryResult();
    }

    @Override
    public String findUserGuidByAppId(String appId) {
        List list = getHibernateTemplate().find("select u.guid from UserApp uc join uc.user u join uc.app c where c.appId=?", appId);
        return list.isEmpty() ? null : (String) list.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public OpenUser findOpenUserByOpenid(String openid) {
        List<OpenUser> list = (List<OpenUser>) getHibernateTemplate().find("from OpenUser u where u.openid=?", openid);
        return list.isEmpty() ? null : list.get(0);
    }
}
