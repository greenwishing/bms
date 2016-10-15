package cn.greenwishing.bms.persistence.hibernate.oauth;

import cn.greenwishing.bms.domain.oauth.App;
import cn.greenwishing.bms.domain.oauth.AppRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Wufan
 * Date: 2016/8/13
 */
@Repository("oAuthRepository")
public class AppRepositoryHibernate extends AbstractRepositoryHibernate implements AppRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<App> findUserApps(String userGuid) {
        return getHibernateTemplate().find("select uc.app from UserApp uc  join uc.user u where u.guid=?", userGuid);
    }

    @Override
    public App findByAppId(String appId) {
        List list = getHibernateTemplate().find("from App d where d.appId=?", appId);
        return list.isEmpty() ? null : (App) list.get(0);
    }

    @Override
    public Long findAppCount(String userGuid) {
        List list = getHibernateTemplate().find("select count(*) from UserApp uc join uc.user u where u.guid=?", userGuid);
        return list.isEmpty() ? 0 : (Long) list.get(0);
    }
}
