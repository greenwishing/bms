package cn.greenwishing.bms.persistence.hibernate.article;

import cn.greenwishing.bms.domain.article.ArticleCategory;
import cn.greenwishing.bms.domain.article.ArticleRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;

import java.util.List;

/**
 * @author Wu Fan
 */
public class ArticleRepositoryHibernate extends AbstractRepositoryHibernate implements ArticleRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<ArticleCategory> findArticleCategoryByUserGuid(String userGuid) {
        return getHibernateTemplate().find("from ArticleCategory ac where ac.owner.guid=?", userGuid);
    }
}
