package cn.greenwishing.bms.dto.configuration;

import cn.greenwishing.bms.domain.config.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/13
 */
public class ConfigurationDTO {

    private String guid;
    private String key;
    private String value;
    private String description;

    public ConfigurationDTO() {
    }

    public ConfigurationDTO(Configuration configuration) {
        this.guid = configuration.guid();
        this.key = configuration.key();
        this.value = configuration.value();
        this.description = configuration.description();
    }

    public static List<ConfigurationDTO> toDTOs(List<Configuration> configurations) {
        List<ConfigurationDTO> configurationDTOs = new ArrayList<>();
        configurations.forEach(cfg -> configurationDTOs.add(new ConfigurationDTO(cfg)));
        return configurationDTOs;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
