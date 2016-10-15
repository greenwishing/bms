package cn.greenwishing.bms.utils.paging;

import cn.greenwishing.bms.domain.article.Article;
import cn.greenwishing.bms.domain.billing.Billing;
import cn.greenwishing.bms.domain.billing.BillingType;
import cn.greenwishing.bms.utils.StringDecoder;

/**
 * User: Wu Fan
 */
public class ArticlePaging extends AbstractPaging<Article> {

    private String key;

    public ArticlePaging(int currentPage, int pageSize, String key) {
        super(currentPage, pageSize);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
