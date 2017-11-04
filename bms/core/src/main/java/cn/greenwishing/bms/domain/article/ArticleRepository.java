package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.utils.paging.ArticlePaging;

import java.util.List;

/**
 * @author Frank wu
 */
public interface ArticleRepository extends Repository {
    List<ArticleCategory> findArticleCategoryByUserGuid(String userGuid);

    ArticlePaging findArticlePaging(ArticlePaging articlePaging);
}
