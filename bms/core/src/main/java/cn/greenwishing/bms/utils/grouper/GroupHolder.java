package cn.greenwishing.bms.utils.grouper;

import java.util.List;

/**
 * User: frank wu
 * Date: 2017/9/28
 */
public interface GroupHolder<H extends GroupHolder, E extends GroupItem> {

    H add(E element);
    H addAll(List<E> elements);
}
