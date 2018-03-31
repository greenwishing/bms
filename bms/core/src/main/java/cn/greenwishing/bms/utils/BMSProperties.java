package cn.greenwishing.bms.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Frank wu
 * @date 13-6-4
 */
public class BMSProperties extends PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<>();
        props.forEach((key, value) -> {
            String keyStr = key.toString();
            ctxPropertiesMap.put(keyStr, props.getProperty(keyStr));
        });
    }

    public static String get(String name) {
        return ctxPropertiesMap.get(name);
    }

    public static boolean isDevelopMode() {
        String develop = BMSProperties.get("bms.system.develop");
        return "true".equals(develop);
    }
}
