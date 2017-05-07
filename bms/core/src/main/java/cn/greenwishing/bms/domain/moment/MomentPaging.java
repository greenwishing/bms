package cn.greenwishing.bms.domain.moment;

import cn.greenwishing.bms.utils.paging.AbstractPaging;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
public class MomentPaging extends AbstractPaging<Moment> {

    private String key;
    private String typeGuid;

    public MomentPaging(int currentPage, int pageSize, String key, String typeGuid) {
        super(currentPage, pageSize);
        this.key = key;
        this.typeGuid = typeGuid;
    }

    public String getKey() {
        return key;
    }

    public String getTypeGuid() {
        return typeGuid;
    }
}
