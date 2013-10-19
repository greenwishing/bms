package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.domain.Repository;

import java.util.List;

/**
 * @author Wu Fan
 */
public interface ArticleRepository extends Repository {
    List<ArticleCategory> findArticleCategoryByUserGuid(String userGuid);
}
