package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.moment.MomentDTO;
import cn.greenwishing.bms.dto.moment.MomentPagingDTO;
import cn.greenwishing.bms.dto.moment.MomentTypeDTO;

import java.util.List;

/**
 * @author Frank wu
 * @date 2017/5/7
 */
public interface MomentService {
    List<MomentTypeDTO> loadMomentTypes();

    MomentTypeDTO loadMomentTypeByGuid(String guid);

    void saveOrUpdateMomentType(MomentTypeDTO momentTypeDTO);

    MomentPagingDTO loadMomentPaging(MomentPagingDTO pagingDTO);

    MomentDTO loadMomentByGuid(String guid);

    void saveOrUpdateMoment(MomentDTO momentDTO);
}
