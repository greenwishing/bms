package cn.greenwishing.bms.service.impl;

import cn.greenwishing.bms.api.weixin.weapp.Template;
import cn.greenwishing.bms.api.weixin.weapp.TemplateMessageSendRequest;
import cn.greenwishing.bms.domain.feedback.Feedback;
import cn.greenwishing.bms.domain.feedback.FeedbackRepository;
import cn.greenwishing.bms.domain.feedback.FeedbackType;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.domain.user.UserRepository;
import cn.greenwishing.bms.dto.feedback.FeedbackDTO;
import cn.greenwishing.bms.dto.feedback.FeedbackPagingDTO;
import cn.greenwishing.bms.service.FeedbackService;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.SecurityHolder;
import cn.greenwishing.bms.utils.ValidationUtils;
import cn.greenwishing.bms.utils.paging.FeedbackPaging;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
        try {
            sendFeedbackNotify(feedback, feedbackDTO.getOpenId(), feedbackDTO.getFormId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反馈时间 {{keyword1.DATA}}
     * 反馈内容 {{keyword2.DATA}}
     */
    private void sendFeedbackNotify(Feedback feedback, String openId, String formId) {
        if (ValidationUtils.isEmpty(openId)) {
            return;
        }
        if (ValidationUtils.isEmpty(formId)) {
            return;
        }
        Map<String, String> fields = new HashMap<>();
        fields.put("keyword1", JodaUtils.dateTimeToString(feedback.creationTime()));
        fields.put("keyword2", feedback.content());
        new TemplateMessageSendRequest(openId, Template.FEEDBACK, fields, formId)
                .execute();
    }

    /**
     * 提交时间 {{keyword1.DATA}}
     * 反馈内容 {{keyword2.DATA}}
     * 回复内容 {{keyword3.DATA}}
     */
    private void sendFeedbackReplyNotify(Feedback feedback, String openId, String formId) {
        Feedback parent = feedback.parent();
        if (parent == null) {
            return;
        }
        if (ValidationUtils.isEmpty(openId)) {
            return;
        }
        if (ValidationUtils.isEmpty(formId)) {
            return;
        }
        Map<String, String> fields = new HashMap<>();
        fields.put("keyword1", JodaUtils.dateTimeToString(feedback.creationTime()));
        fields.put("keyword2", parent.content());
        fields.put("keyword3", feedback.content());
        new TemplateMessageSendRequest(openId, Template.FEEDBACK_REPLY, fields, formId)
        .execute();
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
        try {
            sendFeedbackReplyNotify(reply, replyDTO.getOpenId(), replyDTO.getFormId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
