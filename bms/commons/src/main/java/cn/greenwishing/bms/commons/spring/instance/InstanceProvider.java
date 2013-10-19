package cn.greenwishing.bms.commons.spring.instance;

/**
 * @author Wu Fan
 */
public interface InstanceProvider {

    <T> T getInstance(Class<T> beanClass);

    <T> T getInstance(Class<T> beanClass, String beanName);

}
