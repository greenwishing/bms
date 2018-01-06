package cn.greenwishing.bms.web.exception;

import cn.greenwishing.bms.web.controller.api.ApiResult;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public class SimpleHandlerExceptionResolver extends SimpleMappingExceptionResolver {

    @Nullable
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api")) {
            ApiResult result = ApiResult.fail(-1, ex.getMessage());
            return new ModelAndView(new MappingJackson2JsonView(), result.toModelMap());
        }
        return super.doResolveException(request, response, handler, ex);
    }
}
