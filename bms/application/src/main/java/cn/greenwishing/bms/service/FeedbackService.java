package cn.greenwishing.bms.service;

import cn.greenwishing.bms.dto.feedback.FeedbackDTO;
import cn.greenwishing.bms.dto.feedback.FeedbackPagingDTO;

/**
 * @author Wufan
 * @date 2018/4/22
 */
public interface FeedbackService {
    void saveFeedback(FeedbackDTO feedbackDTO);

    FeedbackPagingDTO loadFeedbackPaging(FeedbackPagingDTO pagingDTO);

    void saveReply(FeedbackDTO replyDTO);
}
