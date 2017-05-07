package cn.greenwishing.bms.dto.moment;

import cn.greenwishing.bms.domain.moment.Moment;
import cn.greenwishing.bms.domain.moment.MomentPaging;
import cn.greenwishing.bms.dto.AbstractPagingDTO;

import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
public class MomentPagingDTO extends AbstractPagingDTO<MomentDTO, MomentPaging> {

    private String key;
    private String typeGuid;

    @Override
    public MomentPaging toPaging() {
        return new MomentPaging(currentPage, pageSize, key, typeGuid);
    }

    @Override
    protected void convertList(MomentPaging paging) {
        List<Moment> moments = paging.getList();
        this.list = MomentDTO.toDTOs(moments);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTypeGuid() {
        return typeGuid;
    }

    public void setTypeGuid(String typeGuid) {
        this.typeGuid = typeGuid;
    }
}
