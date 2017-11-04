package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.configuration.ConfigurationDTO;

import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/13
 */
public interface ConfigurationService {
    List<ConfigurationDTO> loadConfigurations();

    ConfigurationDTO loadConfigurationByGuid(String guid);

    void saveOrUpdateConfiguration(ConfigurationDTO configurationDTO);

    void refreshCache();

    void sendMail(String email, String subject, String content);
}
