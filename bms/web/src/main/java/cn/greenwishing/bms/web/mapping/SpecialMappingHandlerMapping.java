package cn.greenwishing.bms.web.mapping;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wufan
 * @date 2018/1/6
 */
public class SpecialMappingHandlerMapping extends RequestMappingHandlerMapping {

    private static final Map<String, HandlerMethod> HANDLER_METHOD_MAP = new HashMap<>();

    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        HandlerMethod handlerMethod = HANDLER_METHOD_MAP.get(lookupPath);
        if (null != handlerMethod) {
            return handlerMethod;
        }
        return super.lookupHandlerMethod(lookupPath, request);
    }

    public static void register(String uri, Object handler, Class targetClass) throws NoSuchMethodException {
        if (!uri.startsWith("/")) {
            uri = "/" + uri;
        }
        HandlerMethod handlerMethod = lookupMethodHandler(handler, targetClass);
        HANDLER_METHOD_MAP.put(uri, handlerMethod);
    }

    private static HandlerMethod lookupMethodHandler(Object handler, Class targetClass) throws NoSuchMethodException {
        Class handlerClass = handler.getClass();
        Method[] methods = handlerClass.getMethods();
        if (methods != null) {
            for (Method method : methods) {
                Annotation annotation = method.getAnnotation(targetClass);
                if (annotation != null) {
                    return new HandlerMethod(handler, method);
                }
            }
        }
        throw new NoSuchMethodException("No handler with type '" + targetClass.getName() + "' found!");
    }
}
