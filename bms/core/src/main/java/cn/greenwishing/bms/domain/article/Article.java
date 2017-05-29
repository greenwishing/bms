package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;

/**
 * User: Wu Fan
 */
@Entity
@Table(name = "article")
public class Article extends AbstractDomain {

    @Column(name = "title")
    private String title;

    @Column(name = "cover")
    private String cover;

    @Column(name = "content")
    private String content;

    @JoinColumn(name = "category_id")
    @ManyToOne(targetEntity = ArticleCategory.class)
    private ArticleCategory category;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "access")
    @Enumerated(value = EnumType.STRING)
    private ArticleAccess access;

    public Article(){}

    public Article(User user){
        this.user = user;
    }

    public void update(String title, String cover, String content, ArticleCategory category) {
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.category = category;
    }

    public void updateAccess(ArticleAccess access) {
        this.access = access;
    }

    public String title() {
        return title;
    }

    public String cover() {
        return cover;
    }

    public String content() {
        return content;
    }

    public ArticleCategory category() {
        return category;
    }

    public User user() {
        return user;
    }

    public ArticleAccess access() {
        return access;
    }
}
