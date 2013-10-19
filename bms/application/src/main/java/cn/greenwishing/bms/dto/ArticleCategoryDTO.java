package cn.greenwishing.bms.dto;

import cn.greenwishing.bms.domain.article.ArticleCategory;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu Fan
 */
public class ArticleCategoryDTO {

    private String guid;
    private String name;

    public ArticleCategoryDTO() {
    }

    public ArticleCategoryDTO(ArticleCategory category) {
        this.guid = category.guid();
        this.name = category.name();
    }

    public static List<ArticleCategoryDTO> toDTOs(List<ArticleCategory> categories) {
        List<ArticleCategoryDTO> categoryDTOs = new ArrayList<>();
        for (ArticleCategory category : categories) {
            ArticleCategoryDTO categoryDTO = new ArticleCategoryDTO(category);
            categoryDTOs.add(categoryDTO);
        }
        return categoryDTOs;
    }

    public ArticleCategory toArticleCategory() {
        ArticleCategory category;
        if (ValidationUtils.isNotEmpty(guid)) {
            category = ArticleCategory.findByGuid(guid);
        } else {
            String userGuid = SecurityHolder.getUserGuid();
            User owner = User.findByGuid(userGuid);
            category = new ArticleCategory(owner);
        }
        category.update(name);
        return category;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
