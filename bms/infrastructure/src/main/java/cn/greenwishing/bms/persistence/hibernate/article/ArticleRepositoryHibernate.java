package cn.greenwishing.bms.persistence.hibernate.article;

import cn.greenwishing.bms.domain.article.ArticleCategory;
import cn.greenwishing.bms.domain.article.ArticleRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.paging.ArticlePaging;
import cn.greenwishing.bms.utils.query.helper.ArticleQueryHelper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Wu Fan
 */
@Repository("articleRepository")
public class ArticleRepositoryHibernate extends AbstractRepositoryHibernate implements ArticleRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<ArticleCategory> findArticleCategoryByUserGuid(String userGuid) {
        return getHibernateTemplate().find("from ArticleCategory ac where ac.user.guid=?", userGuid);
    }

    @Override
    public ArticlePaging findArticlePaging(ArticlePaging articlePaging) {
        ArticleQueryHelper helper = new ArticleQueryHelper(getHibernateTemplate(), articlePaging);
        return helper.queryResult();
    }
}
