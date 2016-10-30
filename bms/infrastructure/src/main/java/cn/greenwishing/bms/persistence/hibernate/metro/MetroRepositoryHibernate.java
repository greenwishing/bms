package cn.greenwishing.bms.persistence.hibernate.metro;

import cn.greenwishing.bms.domain.metro.MetroLineStation;
import cn.greenwishing.bms.domain.metro.MetroRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.parser.SqlResultParser;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * User: Wufan
 * Date: 2016/6/5
 */
@Repository("metroRepository")
public class MetroRepositoryHibernate extends AbstractRepositoryHibernate implements MetroRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<MetroLineStation> findMetroLineStations(String metroLineGuid) {
        String queryString = "select s from MetroLineStation s where s.line.guid=? order by s.sort";
        return getHibernateTemplate().find(queryString, metroLineGuid);
    }

    @Override
    public List<SqlResultParser> findSimpleMetroLineStations(final Integer metroLineId) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<SqlResultParser>>() {
            @Override
            @SuppressWarnings("unchecked")
            public List<SqlResultParser> doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery("select ms.id id, ms.guid guid, s.guid sGuid, s.name name, s.longitude lng, s.latitude lat, ms.status status" +
                        " from metro_line_station ms join station s on ms.station_id=s.id where ms.metro_line_id=:metroLineId order by ms.sort");
                query.setParameter("metroLineId", metroLineId);
                query.addScalar("id").addScalar("guid").addScalar("sGuid").addScalar("name")
                        .addScalar("lng").addScalar("lat").addScalar("status");
                List<Object[]> results = query.list();
                return SqlResultParser.valueOf(results);
            }
        });
    }
}
