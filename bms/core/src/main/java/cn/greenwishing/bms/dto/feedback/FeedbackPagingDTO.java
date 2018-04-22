package cn.greenwishing.bms.dto.feedback;

import cn.greenwishing.bms.domain.feedback.Feedback;
import cn.greenwishing.bms.dto.AbstractPagingDTO;
import cn.greenwishing.bms.utils.paging.FeedbackPaging;

import java.util.List;

/**
 * @author Wufan
 * @date 2018/4/22
 */
public class FeedbackPagingDTO extends AbstractPagingDTO<FeedbackDTO, FeedbackPaging> {
    @Override
    public FeedbackPaging toPaging() {
        return new FeedbackPaging(currentPage, pageSize, userGuid);
    }

    @Override
    protected void convertList(FeedbackPaging paging) {
        List<Feedback> feedbackList = paging.getList();
        this.list = FeedbackDTO.toDTOs(feedbackList);
    }
}
