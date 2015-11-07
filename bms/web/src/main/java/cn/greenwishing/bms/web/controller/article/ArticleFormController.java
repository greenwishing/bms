package cn.greenwishing.bms.web.controller.article;

import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.article.ArticleDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Wu Fan
 */
@Controller
@RequestMapping({"/system/article/add", "/system/article/edit"})
@SessionAttributes("articleDTO")
public class ArticleFormController {

    public static final String FORM_VIEW = "article/article_form";
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String form(String guid, ModelMap model) {
        List<ArticleCategoryDTO> categoryDTOs = articleService.loadArticleCategories();
        model.put("categoryDTOs", categoryDTOs);
        ArticleDTO articleDTO;
        if (ValidationUtils.isEmpty(guid)) {
            articleDTO = new ArticleDTO();
        } else {
            articleDTO = articleService.loadArticleByGuid(guid);
        }
        model.put("articleDTO", articleDTO);
        return FORM_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(ArticleDTO articleDTO, BindingResult errors) {
        String content = articleDTO.getContent();
        if (ValidationUtils.isEmpty(content)) {
            errors.rejectValue("content", "content", "请输入文章内容");
        }
        if (errors.hasErrors()) {
            return new ModelAndView(FORM_VIEW);
        }
        articleService.saveOrUpdateArticle(articleDTO);
        return new ModelAndView("redirect:list");
    }
}
