package cn.greenwishing.bms.domain;

import java.util.Collection;
import java.util.List;

/**
 * @author Frank wu
 */
public interface Repository {

    void save(Domain domain);

    void saveOrUpdate(Domain domain);

    <T extends Domain> T findByGuid(Class<T> clazz, String guid);

    <T extends Domain> List<T> findAll(Class<T> clazz);

    List<Object> find(String hql, Object... params);

    Object singleResult(String hql, Object... params);

    void update(String queryString, Object... params);

    void remove(Domain domain);

    <T extends Domain> void saveAll(Collection<T> domains);

    void removeAll(Collection list);

    <T extends Domain> List<T> findByGuids(Class<T> clazz, List<String> guids);

    <T extends Domain> void changeStatus(Class<T> clazz, List<String> guids, Enum status);

    <T extends Domain> void remove(Class<T> clazz, List<String> guids);

    <T extends Domain> void  remove(Class<T> clazz, String guid);
}
