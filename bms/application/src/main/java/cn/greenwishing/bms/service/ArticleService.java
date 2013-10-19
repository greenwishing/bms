package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.ArticleDTO;

import java.util.List;

/**
 * @author Wu Fan
 */
public interface ArticleService {
    List<ArticleDTO> loadArticles();

    List<ArticleCategoryDTO> loadArticleCategories();

    void saveOrUpdateArticle(ArticleDTO articleDTO);

    ArticleDTO loadArticleByGuid(String guid);

    ArticleCategoryDTO loadArticleCategoryByGuid(String guid);

    void saveOrUpdateArticleCategory(ArticleCategoryDTO categoryDTO);
}
