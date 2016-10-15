package cn.greenwishing.bms.web.filter;

import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;

/**
 * User: Wufan
 * Date: 2016/8/13
 */
public class PublicFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LocaleContextHolder.setLocale(Locale.CHINA); // 国际化
    }

    @Override
    public void destroy() {
    }
}
