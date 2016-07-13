package cn.greenwishing.bms.utils.paging;

import java.util.List;

/**
 * 分页接口
 * @author Wu Fan
 */
public interface Paging<T> {

    int getPageSize();

    int getCurrentPage();

    long getTotalCount();

    int getPageCount();

    List<T> getList();

    boolean isHasPreviousPage();

    boolean isHasNextPage();

    int getPreviousPage();

    int getNextPage();

    int getLastPage();
}
