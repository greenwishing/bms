package cn.greenwishing.bms.domain.feedback;

import cn.greenwishing.bms.domain.Repository;
import cn.greenwishing.bms.utils.paging.FeedbackPaging;

/**
 * @author Wufan
 * @date 2018/4/22
 */
public interface FeedbackRepository extends Repository {
    FeedbackPaging findFeedbackByPaging(FeedbackPaging paging);
}
