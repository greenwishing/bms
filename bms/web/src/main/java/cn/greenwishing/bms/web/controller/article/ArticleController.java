package cn.greenwishing.bms.web.controller.article;

import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.article.ArticleDTO;
import cn.greenwishing.bms.dto.article.ArticlePagingDTO;
import cn.greenwishing.bms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Wu Fan
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
        return "article/article_show";
    }

    @RequestMapping("categories")
    public String categories(ModelMap model) {
        List<ArticleCategoryDTO> categoryDTOs = articleService.loadArticleCategories();
        model.put("categoryDTOs", categoryDTOs);
        return "article/article_category_list";
    }
}
