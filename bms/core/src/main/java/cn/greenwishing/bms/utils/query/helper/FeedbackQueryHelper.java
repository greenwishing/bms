package cn.greenwishing.bms.utils.query.helper;

import cn.greenwishing.bms.domain.feedback.Feedback;
import cn.greenwishing.bms.utils.paging.FeedbackPaging;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * @author Wufan
 * @date 2018/4/22
 */
public class FeedbackQueryHelper extends AbstractQueryHelper<Feedback, FeedbackPaging> {

    public FeedbackQueryHelper(HibernateTemplate hibernateTemplate, FeedbackPaging paging) {
        super(hibernateTemplate, paging);
    }

    @Override
    public String getSortHql() {
        return " order by id desc";
    }

    @Override
    public String getTotalCountHql() {
        return "select count(*) from Feedback f where f.parent is null";
    }

    @Override
    public String getResultHql() {
        return "from Feedback f where f.parent is null";
    }
}
