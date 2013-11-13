package cn.greenwishing.bms.web.controller.article;

import cn.greenwishing.bms.dto.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.ArticleDTO;
import cn.greenwishing.bms.dto.ArticlePagingDTO;
import cn.greenwishing.bms.service.ArticleService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Wu Fan
 */
public class ArticleController extends MultiActionController {

    private ArticleService articleService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String methodName = getMethodNameResolver().getHandlerMethodName(request);
        return invokeNamedMethod(methodName, request, response);
    }

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArticlePagingDTO articlePagingDTO = new ArticlePagingDTO();
        bind(request, articlePagingDTO);
        ArticlePagingDTO pagingDTO = articleService.loadArticlePaging(articlePagingDTO);
        return new ModelAndView("article/article_list", "pagingDTO", pagingDTO);
    }

    public ModelAndView category(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ArticleCategoryDTO> categoryDTOs = articleService.loadArticleCategories();
        return new ModelAndView("article/article_category_list", "categoryDTOs", categoryDTOs);
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
