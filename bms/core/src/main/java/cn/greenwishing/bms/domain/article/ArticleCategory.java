package cn.greenwishing.bms.domain.article;

import cn.greenwishing.bms.domain.AbstractDomain;
import cn.greenwishing.bms.domain.user.User;

import javax.persistence.*;

/**
 * @author Wu Fan
 */
@Entity
@Table(name = "article_category")
public class ArticleCategory extends AbstractDomain {

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    public ArticleCategory() {
    }

    public ArticleCategory(User owner) {
        this.owner = owner;
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
