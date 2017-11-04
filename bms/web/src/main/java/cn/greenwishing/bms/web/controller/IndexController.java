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
    public String login() {
        return "login";
    }

    @RequestMapping("/system/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/weather")
    public ModelAndView weather() throws Exception {
        ModelMap model = new ModelMap();
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("/articles")
    public String index(ArticlePagingDTO pagingDTO, ModelMap model) {
        pagingDTO.setAccess(ArticleAccess.PUBLIC);
        pagingDTO = articleService.loadArticlePaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return "public/article_list";
    }

    @RequestMapping("/articles/{userGuid}")
    public String article(ArticlePagingDTO pagingDTO, ModelMap model, @PathVariable("userGuid") String userGuid) {
        pagingDTO.setAccess(ArticleAccess.PUBLIC);
        pagingDTO.setUserGuid(userGuid);
        pagingDTO = articleService.loadArticlePaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return "public/article_list";
    }

    @RequestMapping("/article/{articleGuid}")
    public String article(@PathVariable("articleGuid") String articleGuid, ModelMap model) {
        ArticleDTO article = articleService.loadArticleByGuid(articleGuid);
        model.put("article", article);
        model.put("loginUserGuid", SecurityHolder.getUserGuid());
        return "article/article_show";
    }
}
