package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.oauth.OAuthAppDTO;

import java.util.List;

/**
 * User: Wufan
 * Date: 2015/11/7.
 */
public interface AppService {
    List<OAuthAppDTO> loadApps();

    OAuthAppDTO loadAppByAppId(String appId);

    void saveOrUpdate(OAuthAppDTO appDTO);

    Long loadAppCount();
}
