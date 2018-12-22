package cn.greenwishing.bms.web.controller.article;

import cn.greenwishing.bms.domain.article.ArticleAccess;
import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.article.ArticleDTO;
import cn.greenwishing.bms.dto.article.ArticlePagingDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Frank wu
 */
@Controller
@RequestMapping("/system/article/")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("list")
    public ModelAndView list(ArticlePagingDTO pagingDTO, ModelMap model) {
        pagingDTO.setUserGuid(SecurityHolder.getUserGuid());
        pagingDTO = articleService.loadArticlePaging(pagingDTO);
        model.put("pagingDTO", pagingDTO);
        return new ModelAndView("article/article_list");
    }

    @GetMapping({"add", "edit"})
    @ModelAttribute("articleDTO")
    public ModelAndView articleForm(String guid, ModelMap model) {
        String userGuid = SecurityHolder.getUserGuid();
        List<ArticleCategoryDTO> categoryDTOs = articleService.loadArticleCategories(userGuid);
        model.put("categoryDTOs", categoryDTOs);
        model.put("accessTypes", ArticleAccess.values());
        ArticleDTO articleDTO;
        if (ValidationUtils.isEmpty(guid)) {
            articleDTO = new ArticleDTO();
        } else {
            articleDTO = articleService.loadArticleByGuid(guid);
        }
        model.put("articleDTO", articleDTO);
        return new ModelAndView("article/article_form");
    }

    @PostMapping({"add", "edit"})
    public ModelAndView saveArticle(@ModelAttribute("articleDTO") ArticleDTO articleDTO, BindingResult errors) {
        String content = articleDTO.getContent();
        if (ValidationUtils.isEmpty(content)) {
            errors.rejectValue("content", "content", "请输入文章内容");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            articleDTO.setUserGuid(SecurityHolder.getUserGuid());
            articleService.saveOrUpdateArticle(articleDTO);
            model.put("success", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @GetMapping({"add_category", "edit_category"})
    @ModelAttribute("articleCategoryDTO")
    public ModelAndView categoryForm(String guid, ModelMap model) {
        ArticleCategoryDTO articleCategoryDTO;
        if (ValidationUtils.isEmpty(guid)) {
            articleCategoryDTO = new ArticleCategoryDTO();
        } else {
            articleCategoryDTO = articleService.loadArticleCategoryByGuid(guid);
        }
        model.put("articleCategoryDTO", articleCategoryDTO);
        return new ModelAndView("article/article_category_form");
    }

    @PostMapping({"add_category", "edit_category"})
    public ModelAndView saveCategory(@ModelAttribute("articleCategoryDTO") ArticleCategoryDTO articleCategoryDTO, BindingResult errors) {
        String name = articleCategoryDTO.getName();
        if (ValidationUtils.isEmpty(name)) {
            errors.rejectValue("name", "name", "请输入分类名称");
        }
        ModelMap model = new ModelMap();
        if (errors.hasErrors()) {
            model.put("success", false);
            model.put("message", errors.getFieldError().getDefaultMessage());
        } else {
            articleCategoryDTO.setUserGuid(SecurityHolder.getUserGuid());
            articleService.saveOrUpdateArticleCategory(articleCategoryDTO);
            model.put("success", true);
        }
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping("preview")
    public ModelAndView preview(String guid, ModelMap model) {
        ArticleDTO article = articleService.loadArticleByGuid(guid);
        model.put("article", article);
        return new ModelAndView("article/article_preview");
    }

    @RequestMapping("show")
    public ModelAndView show(String guid, ModelMap model) {
        ArticleDTO article = articleService.loadArticleByGuid(guid);
        model.put("article", article);
        model.put("login", SecurityHolder.getUserId() != null);
        return new ModelAndView("article/article_show");
    }

    @RequestMapping("categories")
    public ModelAndView categories(ModelMap model) {
        String userGuid = SecurityHolder.getUserGuid();
        List<ArticleCategoryDTO> categoryDTOs = articleService.loadArticleCategories(userGuid);
        model.put("categoryDTOs", categoryDTOs);
        return new ModelAndView("article/article_category_list");
    }

    @RequestMapping("gen")
    public ModelAndView gen() {
        String userGuid = SecurityHolder.getUserGuid();
        articleService.generateDefaultCategory(userGuid);
        return null;
    }
}
