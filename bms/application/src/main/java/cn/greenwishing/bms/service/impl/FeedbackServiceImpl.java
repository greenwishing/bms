package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.domain.feedback.Feedback;
import cn.greenwishing.bms.domain.feedback.FeedbackRepository;
import cn.greenwishing.bms.domain.feedback.FeedbackType;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.feedback.FeedbackDTO;
import cn.greenwishing.bms.dto.feedback.FeedbackPagingDTO;
import cn.greenwishing.bms.service.FeedbackService;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.paging.FeedbackPaging;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wufan
 * @date 2018/4/22
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackRepository feedbackRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public void saveFeedback(FeedbackDTO feedbackDTO) {
        String userGuid = feedbackDTO.getUserGuid();
        User user = userRepository.findByGuid(User.class, userGuid);
        FeedbackType type = feedbackDTO.getType();
        if (type == null) {
            type = FeedbackType.OTHER;
        }
        Feedback feedback = new Feedback(user, type, feedbackDTO.getContent());
        feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackPagingDTO loadFeedbackPaging(FeedbackPagingDTO pagingDTO) {
        if (SecurityHolder.isAdminAccount()) {
            // 系统帐号查询所有反馈记录
            pagingDTO.setUserGuid(null);
        }
        FeedbackPaging paging = feedbackRepository.findFeedbackByPaging(pagingDTO.toPaging());
        return pagingDTO.convertResult(paging);
    }

    @Override
    public void saveReply(FeedbackDTO replyDTO) {
        String feedbackGuid = replyDTO.getFeedbackGuid();
        Feedback feedback = feedbackRepository.findByGuid(Feedback.class, feedbackGuid);
        User user = userRepository.findByGuid(User.class, SecurityHolder.getUserGuid());
        Feedback reply = new Feedback(user, FeedbackType.OTHER, replyDTO.getContent());
        reply.updateParent(feedback);
        feedbackRepository.save(reply);
    }
}
