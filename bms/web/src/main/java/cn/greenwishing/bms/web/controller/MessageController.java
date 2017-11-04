package cn.greenwishing.bms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @RequestMapping("receive")
    public ModelAndView receive(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String type = ServletRequestUtils.getStringParameter(request, "type");
        if ("wx_bms".equals(type)) {
            String signature = ServletRequestUtils.getStringParameter(request, "signature");
            String timestamp = ServletRequestUtils.getStringParameter(request, "timestamp");
            String nonce = ServletRequestUtils.getStringParameter(request, "nonce");
            String echostr = ServletRequestUtils.getStringParameter(request, "echostr");
            if (echostr != null) { // 假装通过
                response.getOutputStream().write(echostr.getBytes());
                return null;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }
}
