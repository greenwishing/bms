package cn.greenwishing.bms.dto.activity;

import cn.greenwishing.bms.domain.activity.Activity;
import cn.greenwishing.bms.domain.activity.ActivityPaging;
import cn.greenwishing.bms.domain.article.Article;
import cn.greenwishing.bms.dto.AbstractPagingDTO;
import cn.greenwishing.bms.dto.article.ArticleDTO;

import java.util.List;

/**
 * @author Wufan
 * @date 2019/5/4
 */
public class ActivityPagingDTO extends AbstractPagingDTO<ActivityDTO, ActivityPaging> {
    @Override
    public ActivityPaging toPaging() {
        return new ActivityPaging();
    }

    @Override
    protected void convertList(ActivityPaging paging) {
        List<Activity> list = paging.getList();
        this.list = ActivityDTO.toDTOs(list);
    }
}
