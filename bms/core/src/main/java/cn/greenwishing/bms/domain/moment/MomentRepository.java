package cn.greenwishing.bms.domain.moment;

import cn.greenwishing.bms.domain.Repository;

import java.util.List;

/**
 * User: Wufan
 * Date: 2017/5/7
 */
public interface MomentRepository extends Repository {
    List<MomentType> findMomentTypes(Integer userId);

    MomentPaging findMomentPaging(MomentPaging paging);
}
