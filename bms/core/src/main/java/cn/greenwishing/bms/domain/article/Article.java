package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;

/**
 * @author Wu Fan
 */
@Entity
@Table(name = "article")
public class Article extends AbstractDomain {

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @JoinColumn(name = "category_id")
    @ManyToOne(targetEntity = ArticleCategory.class)
    private ArticleCategory category;

    @JoinColumn(name = "author_id")
    @ManyToOne(targetEntity = User.class)
    private User author;

    public Article(){}

    public Article(User author){
        this.author = author;
    }

    public void update(String title, String content, ArticleCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
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
