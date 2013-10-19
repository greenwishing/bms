package cn.greenwishing.bms.commons.spring;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.commons.spring.instance.InstanceProvider;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @author Wu Fan
 */
public class BMSContextLoadListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        InstanceProvider provider = new SpringProvider(context);
        InstanceFactory.setInstanceProvider(provider);
    }
}
