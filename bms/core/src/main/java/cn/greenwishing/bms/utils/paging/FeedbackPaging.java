package cn.greenwishing.bms.utils.paging;

import cn.greenwishing.bms.domain.feedback.Feedback;

/**
 * @author Wufan
 * @date 2018/4/22
 */
public class FeedbackPaging extends AbstractPaging<Feedback> {

    public FeedbackPaging() {
    }

    public FeedbackPaging(int currentPage, int pageSize, String userGuid) {
        super(currentPage, pageSize, userGuid);
    }
}
