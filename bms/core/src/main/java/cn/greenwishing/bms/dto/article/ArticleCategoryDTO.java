package cn.greenwishing.bms.dto.article;

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
