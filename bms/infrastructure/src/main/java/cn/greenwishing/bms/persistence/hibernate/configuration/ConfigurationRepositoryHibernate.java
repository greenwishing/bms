package cn.greenwishing.bms.persistence.hibernate.configuration;

import cn.greenwishing.bms.domain.config.ConfigurationRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/13
 */
@Repository("configurationRepository")
public class ConfigurationRepositoryHibernate extends AbstractRepositoryHibernate implements ConfigurationRepository {
    @Override
    @SuppressWarnings("unchecked")
    public String findByKey(String key) {
        List list = getHibernateTemplate().find("select c.value from Configuration c where c.key=?", key);
        return list.isEmpty() ? null : (String) list.get(0);
    }
}
