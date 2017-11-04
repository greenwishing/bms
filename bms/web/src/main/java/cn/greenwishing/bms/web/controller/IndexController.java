package cn.greenwishing.bms.web.controller;

import cn.greenwishing.bms.domain.article.ArticleAccess;
import cn.greenwishing.bms.dto.article.ArticleDTO;
import cn.greenwishing.bms.dto.article.ArticlePagingDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.service.UserService;
import cn.greenwishing.bms.utils.SecurityHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;

/**
 * @author Frank wu
 * @date 2017/4/17
 */
@Controller
public class IndexController {

    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;

    @RequestMapping("/system/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/system/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/weather")
    public ModelAndView weather() throws Exception {
        ModelMap model = new ModelMap();
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("/articles")
    public ModelAndView index(ArticlePagingDTO pagingDTO, ModelMap model) {
        pagingDTO.setAccess(ArticleAccess.PUBLIC);
        pagingDTO = articleService.loadArticlePaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return new ModelAndView("public/article_list");
    }

    @RequestMapping("/articles/{userGuid}")
    public ModelAndView article(ArticlePagingDTO pagingDTO, ModelMap model, @PathVariable("userGuid") String userGuid) {
        pagingDTO.setAccess(ArticleAccess.PUBLIC);
        pagingDTO.setUserGuid(userGuid);
        pagingDTO = articleService.loadArticlePaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return new ModelAndView("public/article_list");
    }

    @RequestMapping("/article/{articleGuid}")
    public ModelAndView article(@PathVariable("articleGuid") String articleGuid, ModelMap model) {
        ArticleDTO article = articleService.loadArticleByGuid(articleGuid);
        model.put("article", article);
        model.put("loginUserGuid", SecurityHolder.getUserGuid());
        return new ModelAndView("article/article_show");
    }
}
