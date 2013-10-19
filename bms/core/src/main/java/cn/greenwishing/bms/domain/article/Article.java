package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.commons.spring.instance.InstanceFactory;
import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import java.util.List;

/**
 * @author Wu Fan
 */
public class Article extends AbstractDomain {

    private String title;
    private String content;
    private ArticleCategory category;
    private User author;

    private static ArticleRepository repository;

    private static ArticleRepository getRepository() {
        if (repository == null) {
            repository = InstanceFactory.getInstance(ArticleRepository.class);
        }
        return repository;
    }

    public Article(){}

    public Article(User author){
        this.author = author;
    }

    public void update(String title, String content, ArticleCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void saveOrUpdate() {
        getRepository().saveOrUpdate(this);
    }

    public static List<Article> loadAll() {
        return getRepository().findAll(Article.class);
    }

    public static Article findByGuid(String guid) {
        return getRepository().findByGuid(Article.class, guid);
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public ArticleCategory category() {
        return category;
    }

    public User author() {
        return author;
    }
}
