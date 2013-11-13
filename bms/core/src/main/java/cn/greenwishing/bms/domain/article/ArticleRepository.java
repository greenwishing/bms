package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.utils.paging.ArticlePaging;

import java.util.List;

/**
 * @author Wu Fan
 */
public interface ArticleRepository extends Repository {
    List<ArticleCategory> findArticleCategoryByUserGuid(String userGuid);

    ArticlePaging findArticlePaging(ArticlePaging articlePaging);
}
