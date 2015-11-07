package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.article.Article;
import cn.greenwishing.bms.domain.article.ArticleCategory;
import cn.greenwishing.bms.domain.article.ArticleRepository;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.article.ArticleCategoryDTO;
import cn.greenwishing.bms.dto.article.ArticleDTO;
import cn.greenwishing.bms.dto.article.ArticlePagingDTO;
import cn.greenwishing.bms.service.ArticleService;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.ArticlePaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wu Fan
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ArticlePagingDTO loadArticlePaging(ArticlePagingDTO articlePagingDTO) {
        ArticlePaging paging = articleRepository.findArticlePaging(articlePagingDTO.toPaging());
        return articlePagingDTO.convertResult(paging);
    }

    @Override
    public List<ArticleCategoryDTO> loadArticleCategories() {
        String userGuid = SecurityHolder.getUserGuid();
        List<ArticleCategory> categories = articleRepository.findArticleCategoryByUserGuid(userGuid);
        return ArticleCategoryDTO.toDTOs(categories);
    }

    @Override
    public void saveOrUpdateArticle(ArticleDTO articleDTO) {
        Article article;
        String guid = articleDTO.getGuid();
        if (guid != null) {
            article = articleRepository.findByGuid(Article.class, guid);
        } else {
            String userGuid = SecurityHolder.getUserGuid();
            User author = userRepository.findByGuid(User.class, userGuid);
            article = new Article(author);
        }
        ArticleCategory category = null;
        String categoryGuid = articleDTO.getCategoryGuid();
        if (ValidationUtils.isNotEmpty(categoryGuid)) {
            category = articleRepository.findByGuid(ArticleCategory.class, categoryGuid);
        }
        String title = articleDTO.getTitle();
        String content = articleDTO.getContent();
        if (ValidationUtils.isEmpty(title)) {
            title = JodaUtils.today().toString(JodaUtils.DATE_FORMAT);
        }
        article.update(title, content, category);
        articleRepository.saveOrUpdate(article);
    }

    @Override
    public ArticleDTO loadArticleByGuid(String guid) {
        Article article = articleRepository.findByGuid(Article.class, guid);
        return new ArticleDTO(article);
    }

    @Override
    public ArticleCategoryDTO loadArticleCategoryByGuid(String guid) {
        ArticleCategory category = articleRepository.findByGuid(ArticleCategory.class, guid);
        return new ArticleCategoryDTO(category);
    }

    @Override
    public void saveOrUpdateArticleCategory(ArticleCategoryDTO categoryDTO) {
        ArticleCategory category;
        String guid = categoryDTO.getGuid();
        if (ValidationUtils.isNotEmpty(guid)) {
            category = articleRepository.findByGuid(ArticleCategory.class, guid);
        } else {
            String userGuid = SecurityHolder.getUserGuid();
            User owner = userRepository.findByGuid(User.class, userGuid);
            category = new ArticleCategory(owner);
        }
        category.update(categoryDTO.getName());
        articleRepository.saveOrUpdate(category);
    }
}
