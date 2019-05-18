package cn.greenwishing.bms.web.controller;

import cn.greenwishing.bms.domain.article.ArticleAccess;
import cn.greenwishing.bms.dto.activity.ActivityDTO;
import cn.greenwishing.bms.dto.activity.ActivityPlanDTO;
import cn.greenwishing.bms.dto.article.ArticleDTO;
import cn.greenwishing.bms.dto.article.ArticlePagingDTO;
import cn.greenwishing.bms.service.ActivityService;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.SecurityHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Frank wu
 * @date 2017/4/17
 */
@Controller
public class IndexController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ActivityService activityService;

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

    @RequestMapping("/activity/{activityGuid}")
    public ModelAndView activity(@PathVariable("activityGuid") String activityGuid, ModelMap model) {
        ActivityDTO activity = activityService.loadActivityByGuid(activityGuid);
        model.put("activity", activity);
        model.put("dataAction", "plans");
        return new ModelAndView("activity/activity_view");
    }

    @RequestMapping("/activity/plans")
    public ModelAndView activityPlans(@RequestParam("activityGuid") String activityGuid, ModelMap model) {
        List<ActivityPlanDTO> plans = activityService.loadActivityPlans(activityGuid);
        model.put("success", true);
        model.put("plans", plans);
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }
}
