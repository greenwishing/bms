package cn.greenwishing.bms.web.controller.article;

import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * User: Wu Fan
 */
@Controller
@RequestMapping({"/system/article/add_category", "/system/article/edit_category"})
@SessionAttributes("articleCategoryDTO")
public class ArticleCategoryFormController {

    public static final String FORM_VIEW = "article/article_category_form";
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String guid, ModelMap model) {
        ArticleCategoryDTO articleCategoryDTO;
        if (ValidationUtils.isEmpty(guid)) {
            articleCategoryDTO = new ArticleCategoryDTO();
        } else {
            articleCategoryDTO = articleService.loadArticleCategoryByGuid(guid);
        }
        model.put("articleCategoryDTO", articleCategoryDTO);
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(ArticleCategoryDTO categoryDTO, BindException errors) throws Exception {
        String name = categoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入分类名称");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            articleService.saveOrUpdateArticleCategory(categoryDTO);
            model.put("success", true);
            model.put("redirectUrl", "list");
        }
        return new ModelAndView(new MappingJacksonJsonView(), model);
    }
}
