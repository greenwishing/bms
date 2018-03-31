package cn.greenwishing.bms.dto.article;

import cn.greenwishing.bms.domain.article.ArticleCategory;
import cn.greenwishing.bms.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 */
public class ArticleCategoryDTO extends AbstractDTO {

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
        categories.forEach(category -> categoryDTOs.add(new ArticleCategoryDTO(category)));
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
