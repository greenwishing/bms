package cn.greenwishing.bms.domain.article;

/**
 * User: Wufan
 * Date: 2017/5/29
 */
public enum ArticleAccess {
    PRIVATE("仅自己可见"),
    PUBLIC("公开"),
    ;

    private String label;

    ArticleAccess(String label) {
        this.label = label;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }
}
