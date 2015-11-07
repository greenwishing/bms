package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.oauth.OAuthClientDetailsDTO;

import java.util.List;

/**
 * @author Wufan
 * @date 2015/11/7.
 */
public interface OAuthService {
    List<OAuthClientDetailsDTO> loadOAuthClients();

    OAuthClientDetailsDTO loadOAuthClientByClientId(String clientId);

    void saveOrUpdate(OAuthClientDetailsDTO clientDTO);
}
