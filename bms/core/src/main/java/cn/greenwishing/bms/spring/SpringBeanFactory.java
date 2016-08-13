package cn.greenwishing.bms.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Wufan
 * @date 2016/8/13
 */
public class SpringBeanFactory implements ApplicationContextAware {

    private static SpringBeanWrapper wrapper = new SpringBeanWrapper();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        wrapper.setContext(applicationContext);
    }

    public static <T> T getBean(Class<T> clazz) {
        return wrapper.getContext().getBean(clazz);
    }

    private static class SpringBeanWrapper {
        private ApplicationContext context;

        public ApplicationContext getContext() {
            return context;
        }

        public void setContext(ApplicationContext context) {
            this.context = context;
        }
    }
}