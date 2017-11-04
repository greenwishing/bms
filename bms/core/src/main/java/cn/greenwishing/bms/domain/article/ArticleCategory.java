package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;

/**
 * @author Frank wu
 */
@Entity
@Table(name = "article_category")
public class ArticleCategory extends AbstractDomain {

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public ArticleCategory() {
    }

    public ArticleCategory(User user) {
        this.user = user;
    }

    public void update(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public User user() {
        return user;
    }
}
