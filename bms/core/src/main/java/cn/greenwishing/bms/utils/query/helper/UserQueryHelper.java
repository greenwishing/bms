package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.UserPaging;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * User: Wufan
 * Date: 2016/7/13
 */
public class UserQueryHelper extends AbstractQueryHelper<User, UserPaging> {

    public UserQueryHelper(HibernateTemplate hibernateTemplate, UserPaging paging) {
        super(hibernateTemplate, paging);

        String key = paging.getKey();
        if (ValidationUtils.isNotEmpty(key)) {
            addFilter(keyFilter(key));
        }
    }

    private Filter keyFilter(final String key) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("account", key);
                query.setParameter("username", "%" + key + "%");
            }

            @Override
            public String getSubHql() {
                return " and (u.account=:account or u.username like :username)";
            }
        };
    }

    @Override
    public String getResultHql() {
        return "from User u where 1=1" + getSubHql();
    }

    @Override
    public String getTotalCountHql() {
        return "select count(*) from User u where 1=1" + getSubHql();
    }
}
