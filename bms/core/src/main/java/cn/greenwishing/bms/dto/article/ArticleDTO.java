package cn.greenwishing.bms.dto.article;

import cn.greenwishing.bms.domain.article.Article;
import cn.greenwishing.bms.domain.article.ArticleAccess;
import cn.greenwishing.bms.domain.article.ArticleCategory;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.AbstractDTO;
import cn.greenwishing.bms.dto.OSSKey;
import cn.greenwishing.bms.dto.user.UserDTO;
import cn.greenwishing.bms.utils.HtmlFilter;
import cn.greenwishing.bms.utils.JodaUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 */
public class ArticleDTO extends AbstractDTO {

    private String guid;
    private String title;
    private OSSKey cover = new OSSKey();
    private String content;
    private String categoryGuid;
    private String categoryName;
    private String creationTime;
    private ArticleAccess access = ArticleAccess.PRIVATE;

    private UserDTO user = new UserDTO();

    public ArticleDTO() {
    }

    public ArticleDTO(Article article) {
        this.guid = article.guid();
        this.title = article.title();
        this.cover = new OSSKey(article.cover());
        this.content = article.content();
        ArticleCategory category = article.category();
        if (category != null) {
            this.categoryGuid = category.guid();
            this.categoryName = category.name();
        }
        User user = article.user();
        if (user != null) {
            this.user = new UserDTO(user);
        }
        this.creationTime = article.creationTime().toString(JodaUtils.DATE_TIME_FORMAT);
        this.access = article.access();
    }

    public static List<ArticleDTO> toDTOs(List<Article> articles) {
        List<ArticleDTO> articleDTOs = new ArrayList<>();
        articles.forEach(article -> articleDTOs.add(new ArticleDTO(article)));
        return articleDTOs;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OSSKey getCover() {
        return cover;
    }

    public void setCover(OSSKey cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public String getContentText() {
        return HtmlFilter.doFilter(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public ArticleAccess getAccess() {
        return access;
    }

    public void setAccess(ArticleAccess access) {
        this.access = access;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
