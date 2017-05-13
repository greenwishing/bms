package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.cache.AppUserCache;
import cn.greenwishing.bms.cache.ConfigurationCache;
import cn.greenwishing.bms.domain.config.Configuration;
import cn.greenwishing.bms.domain.config.ConfigurationRepository;
import cn.greenwishing.bms.dto.configuration.ConfigurationDTO;
import cn.greenwishing.bms.handler.MailSender;
import cn.greenwishing.bms.service.ConfigurationService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/13
 */
@Service("configurationService")
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public List<ConfigurationDTO> loadConfigurations() {
        List<Configuration> configurations = configurationRepository.findAll(Configuration.class);
        return ConfigurationDTO.toDTOs(configurations);
    }

    @Override
    public ConfigurationDTO loadConfigurationByGuid(String guid) {
        Configuration configuration = configurationRepository.findByGuid(Configuration.class, guid);
        return new ConfigurationDTO(configuration);
    }

    @Override
    public void saveOrUpdateConfiguration(ConfigurationDTO configurationDTO) {
        Configuration configuration;
        String guid = configurationDTO.getGuid();
        if (ValidationUtils.isEmpty(guid)) {
            configuration = new Configuration();
        } else {
            configuration = configurationRepository.findByGuid(Configuration.class, guid);
        }
        String key = configurationDTO.getKey();
        String value = configurationDTO.getValue();
        String description = configurationDTO.getDescription();
        configuration.update(key, value, description);
        configurationRepository.save(configuration);
        ConfigurationCache.update(key, value);
    }

    @Override
    public void refreshCache() {
        ConfigurationCache.clear();
        AppUserCache.clear();
        MailSender.refresh();
    }

    @Override
    public void sendMail(String email, String subject, String content) {
        MailSender.systemSend(email, subject, content);
    }
}
