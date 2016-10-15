package cn.greenwishing.bms.domain.oauth;

import cn.greenwishing.bms.domain.Repository;

import java.util.List;

/**
 * User: Wufan
 * Date: 2016/8/13
 */
public interface AppRepository extends Repository {
    List<App> findUserApps(String userGuid);

    App findByAppId(String appId);

    Long findAppCount(String userGuid);
}
