package cn.greenwishing.bms.utils.paging;

import java.util.List;

/**
 * 分页接口
 * @author Wu Fan
 */
public interface Paging<T> {

    public int getPageSize();

    public int getCurrentPage();

    public long getTotalCount();

    public int getPageCount();

    public List<T> getList();

    public boolean isHasPreviousPage();

    public boolean isHasNextPage();

    public int getPreviousPage();

    public int getNextPage();

    public int getLastPage();
}
