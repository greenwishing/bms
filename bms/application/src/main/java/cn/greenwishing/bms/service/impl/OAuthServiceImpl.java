package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.oauth.OAuthClientDetails;
import cn.greenwishing.bms.dto.oauth.OAuthClientDetailsDTO;
import cn.greenwishing.bms.service.OAuthService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wufan
 * @date 2015/11/7.
 */
@Service("oAuthService")
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<OAuthClientDetailsDTO> loadOAuthClients() {
        List<OAuthClientDetails> list = hibernateTemplate.find("from OAuthClientDetails");
        return OAuthClientDetailsDTO.toDTOs(list);
    }

    @Override
    public OAuthClientDetailsDTO loadOAuthClientByClientId(String clientId) {
        OAuthClientDetails client = findOAuthClientDetailsByClientId(clientId);
        return client == null ? null : new OAuthClientDetailsDTO(client);
    }

    private OAuthClientDetails findOAuthClientDetailsByClientId(String clientId) {
        List list = hibernateTemplate.find("from OAuthClientDetails d where d.clientId=?", clientId);
        return list.isEmpty() ? null : (OAuthClientDetails) list.get(0);
    }

    @Override
    public void saveOrUpdate(OAuthClientDetailsDTO clientDTO) {
        OAuthClientDetails clientDetails;
        String clientId = clientDTO.getClientId();
        if (ValidationUtils.isNotEmpty(clientId)) {
            clientDetails = findOAuthClientDetailsByClientId(clientId);
        } else {
            clientDetails = new OAuthClientDetails();
        }
        clientDTO.updateOAuthClientDetails(clientDetails);
        hibernateTemplate.saveOrUpdate(clientDetails);
    }
}
