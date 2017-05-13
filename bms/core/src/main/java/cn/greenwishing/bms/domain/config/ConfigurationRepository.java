package cn.greenwishing.bms.domain.config;

import cn.greenwishing.bms.domain.Repository;

/**
 * User: Wufan
 * Date: 2017/5/13
 */
public interface ConfigurationRepository extends Repository {
    String findByKey(String key);
}
