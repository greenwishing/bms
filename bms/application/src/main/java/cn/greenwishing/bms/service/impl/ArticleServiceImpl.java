package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.article.Article;
import cn.greenwishing.bms.domain.article.ArticleCategory;
import cn.greenwishing.bms.dto.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.ArticleDTO;
import cn.greenwishing.bms.dto.ArticlePagingDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.paging.ArticlePaging;

import java.util.List;

/**
 * @author Wu Fan
 */
public class ArticleServiceImpl implements ArticleService {
    @Override
    public ArticlePagingDTO loadArticlePaging(ArticlePagingDTO articlePagingDTO) {
        ArticlePaging paging = Article.loadByPaging(articlePagingDTO.toPaging());
        return articlePagingDTO.convertResult(paging);
    }

    @Override
    public List<ArticleCategoryDTO> loadArticleCategories() {
        String userGuid = SecurityHolder.getUserGuid();
        List<ArticleCategory> categories = ArticleCategory.loadByUserGuid(userGuid);
        return ArticleCategoryDTO.toDTOs(categories);
    }

    @Override
    public void saveOrUpdateArticle(ArticleDTO articleDTO) {
        Article article = articleDTO.toArticle();
        article.saveOrUpdate();
    }

    @Override
    public ArticleDTO loadArticleByGuid(String guid) {
        Article article = Article.findByGuid(guid);
        return new ArticleDTO(article);
    }

    @Override
    public ArticleCategoryDTO loadArticleCategoryByGuid(String guid) {
        ArticleCategory category = ArticleCategory.findByGuid(guid);
        return new ArticleCategoryDTO(category);
    }

    @Override
    public void saveOrUpdateArticleCategory(ArticleCategoryDTO categoryDTO) {
        ArticleCategory category = categoryDTO.toArticleCategory();
        category.saveOrUpdate();
    }
}
