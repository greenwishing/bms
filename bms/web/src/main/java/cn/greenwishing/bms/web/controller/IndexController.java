package cn.greenwishing.bms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Wufan
 * Date: 2017/4/17
 */
@Controller
public class IndexController {

    @RequestMapping("/system/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/system/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
