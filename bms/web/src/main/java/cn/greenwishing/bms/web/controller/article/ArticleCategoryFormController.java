package cn.greenwishing.bms.web.controller.article;

import cn.greenwishing.bms.dto.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.ArticleDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Fan
 */
public class ArticleCategoryFormController extends SimpleFormController {

    private ArticleService articleService;

    public ArticleCategoryFormController() {
        setCommandClass(ArticleCategoryDTO.class);
        setCommandName("articleCategoryDTO");
        setFormView("article/article_category_form");
        setSessionForm(true);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String guid = ServletRequestUtils.getStringParameter(request, "guid");
        if (ValidationUtils.isNotEmpty(guid)) {
            return articleService.loadArticleCategoryByGuid(guid);
        }
        return new ArticleCategoryDTO();
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        ArticleCategoryDTO categoryDTO = (ArticleCategoryDTO) command;
        articleService.saveOrUpdateArticleCategory(categoryDTO);
        return new ModelAndView("redirect:list");
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
