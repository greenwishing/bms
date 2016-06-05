package cn.greenwishing.bms.persistence.hibernate.metro;

import cn.greenwishing.bms.domain.metro.MetroLineStation;
import cn.greenwishing.bms.domain.metro.MetroRepository;
import cn.greenwishing.bms.domain.metro.Station;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wufan
 * @date 2016/6/5
 */
@Repository("metroRepository")
public class MetroRepositoryHibernate extends AbstractRepositoryHibernate implements MetroRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<MetroLineStation> findMetroLineStations(String metroLineGuid) {
        String queryString = "select s from MetroLineStation s where s.line.guid=? order by s.sort";
        return getHibernateTemplate().find(queryString, metroLineGuid);
    }
}
