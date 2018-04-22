package cn.greenwishing.bms.dto.feedback;

import cn.greenwishing.bms.domain.feedback.Feedback;
import cn.greenwishing.bms.domain.feedback.FeedbackType;
import cn.greenwishing.bms.domain.user.User;
import cn.greenwishing.bms.dto.AbstractDTO;
import cn.greenwishing.bms.utils.JodaUtils;
import cn.greenwishing.bms.utils.OSSUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2018/4/22
 */
public class FeedbackDTO extends AbstractDTO {

    private String guid;
    private String creationTime;
    private FeedbackType type;
    private String content;
    private String image;
    private String username;

    /**
     * for reply
     */
    private String feedbackGuid;

    private List<FeedbackDTO> replies = new ArrayList<>();

    public FeedbackDTO() {
    }

    public FeedbackDTO(Feedback feedback) {
        this.guid = feedback.guid();
        this.creationTime = JodaUtils.dateTimeToString(feedback.creationTime());
        this.content = feedback.content();
        this.image = feedback.image();
        this.type = feedback.type();
        User user = feedback.user();
        this.userGuid = user.guid();
        this.username = user.username();
        List<Feedback> replies = feedback.replies();
        if (replies != null) {
            this.replies = toDTOs(replies);
        }
    }

    public static <T extends Feedback> List<FeedbackDTO> toDTOs(List<T> feedbackList) {
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        feedbackList.forEach(feedback -> feedbackDTOList.add(new FeedbackDTO(feedback)));
        return feedbackDTOList;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public FeedbackType getType() {
        return type;
    }

    public void setType(FeedbackType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public String getImageUrl() {
        return OSSUtils.generateImageUrl(image);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setFeedbackGuid(String feedbackGuid) {
        this.feedbackGuid = feedbackGuid;
    }

    public String getFeedbackGuid() {
        return feedbackGuid;
    }

    public List<FeedbackDTO> getReplies() {
        return replies;
    }
}
