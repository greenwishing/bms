package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.domain.article.Article;
import cn.greenwishing.bms.domain.article.ArticleAccess;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.ArticlePaging;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * User: Wu Fan
 */
public class ArticleQueryHelper extends AbstractQueryHelper<Article, ArticlePaging> {

    public ArticleQueryHelper(HibernateTemplate hibernateTemplate, ArticlePaging paging) {
        super(hibernateTemplate, paging);

        String userGuid = paging.getUserGuid();
        if (ValidationUtils.isNotEmpty(userGuid)) {
            addFilter(userFilter(userGuid));
        }

        String key = paging.getKey();
        if (ValidationUtils.isNotEmpty(key)) {
            addFilter(keyFilter(key));
        }

        ArticleAccess access = paging.getAccess();
        if (access != null) {
            addFilter(accessFilter(access));
        }
    }

    private Filter accessFilter(final ArticleAccess access) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("access", access);
            }

            @Override
            public String getSubHql() {
                return " and a.access=:access";
            }
        };
    }

    private Filter userFilter(final String userGuid) {
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                query.setParameter("userGuid", userGuid);
            }

            @Override
            public String getSubHql() {
                return " and a.user.guid=:userGuid";
            }
        };
    }

    private Filter keyFilter(final String key) {
        final List<String> keys = Arrays.asList(key.split(" "));
        return new ParameterFilter() {
            @Override
            public void setParameter(Query query) {
                for (int i = 0; i < keys.size(); i++) {
                    String k = keys.get(i);
                    query.setParameter("key" + i, "%" + k + "%");
                }
            }

            @Override
            public String getSubHql() {
                String subHql = " and (";
                for (int i = 0; i < keys.size(); i++) {
                    if (i != 0) {
                        subHql += " or";
                    }
                    subHql += " (a.title like :key" + i + " or a.content like :key" + i + ")";
                }
                return subHql + ")";
            }
        };
    }

    @Override
    public String getResultHql() {
        return "from Article a where 1=1 " + getSubHql() + " order by a.id desc";
    }

    @Override
    public String getTotalCountHql() {
        return "select count(*) from Article a where 1=1 " + getSubHql();
    }

    @Override
    public int getStartIndex() {
        return paging.getStartIndex();
    }

    @Override
    public int getPageSize() {
        return paging.getPageSize();
    }
}
