package cn.greenwishing.bms.commons.service;

import java.util.List;

/**
 * User: WuFan
 * Date: 13-7-4
 */
public interface QueryService {

    public <T> List<T> find(String hql, Object... params);

    public <T> T singleResult(String hql, Object... params);

    public <T> List<T> queryResult(String hql, long firstRow, int pageSize, Object... params);

    public long count(String hql, Object... params);
}
