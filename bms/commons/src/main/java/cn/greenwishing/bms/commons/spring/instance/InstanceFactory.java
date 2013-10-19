package cn.greenwishing.bms.commons.spring.instance;

/**
 * @author Wu Fan
 */
public class InstanceFactory {

    private static InstanceProvider instanceProvider;

    public static <T> T getInstance(Class<T> beanClass) {
        return getInstanceProvider().getInstance(beanClass);
    }

    public static <T> T getInstance(Class<T> beanClass, String beanName) {
        return getInstanceProvider().getInstance(beanClass, beanName);
    }

    public static InstanceProvider getInstanceProvider() {
        return instanceProvider;
    }

    public static void setInstanceProvider(InstanceProvider instanceProvider) {
        InstanceFactory.instanceProvider = instanceProvider;
    }
}
