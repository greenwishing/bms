package cn.greenwishing.bms.persistence.hibernate.help;

import cn.greenwishing.bms.domain.feedback.FeedbackRepository;
import cn.greenwishing.bms.persistence.hibernate.AbstractRepositoryHibernate;
import cn.greenwishing.bms.utils.paging.FeedbackPaging;
import cn.greenwishing.bms.utils.query.helper.FeedbackQueryHelper;
import org.springframework.stereotype.Repository;

/**
 * @author Wufan
 * @date 2018/4/22
 */
@Repository("feedbackRepository")
public class FeedbackRepositoryHibernate extends AbstractRepositoryHibernate implements FeedbackRepository {
    @Override
    public FeedbackPaging findFeedbackByPaging(FeedbackPaging paging) {
        FeedbackQueryHelper helper = new FeedbackQueryHelper(getHibernateTemplate(), paging);
        return helper.queryResult();
    }
}
