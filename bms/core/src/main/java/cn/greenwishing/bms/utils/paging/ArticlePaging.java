package cn.greenwishing.bms.utils.paging;

import cn.greenwishing.bms.domain.article.Article;
import cn.greenwishing.bms.domain.article.ArticleAccess;
import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.StringDecoder;

/**
 * @author Frank wu
 */
public class ArticlePaging extends AbstractPaging<Article> {

    private String key;
    private String userGuid;
    private ArticleAccess access;

    public ArticlePaging(int currentPage, int pageSize, String key, String userGuid, ArticleAccess access) {
        super(currentPage, pageSize, userGuid);
        this.key = key;
        this.userGuid = userGuid;
        this.access = access;
    }

    public String getKey() {
        return key;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public ArticleAccess getAccess() {
        return access;
    }
}
