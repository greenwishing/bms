package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.oauth.OAuthAppDTO;

import java.util.List;

/**
 * @author Frank wu
 * @date 2015/11/7.
 */
public interface AppService {
    List<OAuthAppDTO> loadApps(String userGuid);

    OAuthAppDTO loadAppByAppId(String appId);

    void saveOrUpdate(OAuthAppDTO appDTO);

    Long loadAppCount(String userGuid);
}
