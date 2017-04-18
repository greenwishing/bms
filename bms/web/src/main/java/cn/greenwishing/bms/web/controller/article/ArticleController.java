package cn.greenwishing.bms.web.controller.article;

import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.article.ArticleDTO;
import cn.greenwishing.bms.dto.article.ArticlePagingDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.SecurityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: Wu Fan
 */
@Controller
@RequestMapping("/system/article/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("list")
    public String list(ArticlePagingDTO pagingDTO, ModelMap model) {
        pagingDTO = articleService.loadArticlePaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return "article/article_list";
    }

    @RequestMapping("show")
    public String show(String guid, ModelMap model) {
        ArticleDTO article = articleService.loadArticleByGuid(guid);
        model.put("article", article);
        model.put("login", SecurityHolder.getUserId() != null);
        return "article/article_show";
    }

    @RequestMapping("categories")
    public String categories(ModelMap model) {
        List<ArticleCategoryDTO> categoryDTOs = articleService.loadArticleCategories();
        model.put("categoryDTOs", categoryDTOs);
        return "article/article_category_list";
    }

    @RequestMapping("gen")
    public ModelAndView gen() {
        articleService.generateDefaultCategory();
        return null;
    }
}
