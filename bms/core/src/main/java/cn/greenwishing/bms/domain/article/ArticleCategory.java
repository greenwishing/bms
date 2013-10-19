package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import java.util.List;

/**
 * @author Wu Fan
 */
public class ArticleCategory extends AbstractDomain {

    private String name;
    private User owner;

    private static ArticleRepository repository;

    private static ArticleRepository getRepository() {
        if (repository == null) {
            repository = InstanceFactory.getInstance(ArticleRepository.class);
        }
        return repository;
    }

    public ArticleCategory() {
    }

    public ArticleCategory(User owner) {
        this.owner = owner;
    }

    public static List<ArticleCategory> loadByUserGuid(String userGuid) {
        return getRepository().findArticleCategoryByUserGuid(userGuid);
    }

    public static ArticleCategory findByGuid(String guid) {
        return getRepository().findByGuid(ArticleCategory.class, guid);
    }

    public void saveOrUpdate() {
        getRepository().saveOrUpdate(this);
    }

    public void update(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public User owner() {
        return owner;
    }
}
