package cn.greenwishing.bms.web.controller;

import cn.greenwishing.bms.dto.BillingDTO;
import cn.greenwishing.bms.service.BillingService;
import org.apache.http.HttpRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Vector;

/**
 * @author Wu Fan
 */
public class CommonController extends MultiActionController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String methodName = getMethodNameResolver().getHandlerMethodName(request);
        return invokeNamedMethod(methodName, request, response);
    }

    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("login");
    }
}
