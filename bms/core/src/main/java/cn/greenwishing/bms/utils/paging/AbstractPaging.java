package cn.greenwishing.bms.utils.paging;

import cn.greenwishing.bms.utils.ValidationUtils;

import java.util.List;

/**
 * @author Frank wu
 */
public abstract class AbstractPaging<T> implements Paging {


    public static int DEFAULT_PAGE_SIZE = 10;

    /**
     * 每页的记录数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 当前页中存放的记录
     */
    private List<T> list;

    /**
     * 总记录数
     */
    private long totalCount;
    private String sortName;
    private String sortType;

    protected String userGuid;

    protected AbstractPaging() {
    }

    /**
     * 默认构造方法.
     * @param currentPage 当前页
     * @param pageSize 本页容量
     */

    protected AbstractPaging(int currentPage, int pageSize, String userGuid) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.userGuid = userGuid;
    }

    /**
     * 取总记录数.
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 取当前页中的记录.
     */
    @Override
    public List<T> getList() {
        return list;
    }

    /**
     * 取总页数.
     */
    @Override
    public int getPageCount() {
        int totalPage = (int) totalCount / pageSize;

        if (totalCount % pageSize == 0) {
            return totalPage;
        }
        return totalPage + 1;
    }

    /**
     * 取每页数据容量.
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 取该页当前页码,页码从1开始.
     */
    @Override
    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (this.getPageCount() < currentPage) {
            currentPage = this.getPageCount();
        }
        return currentPage;
    }

    public int getStartIndex() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * 该页是否有下一页.
     */
    @Override
    public boolean isHasNextPage() {
        return this.getCurrentPage() < this.getPageCount();
    }

    @Override
    public int getNextPage() {
        return this.getCurrentPage() + 1;
    }

    @Override
    public int getLastPage() {
        return this.getPageCount();
    }

    /**
     * 该页是否有上一页.
     */
    @Override
    public boolean isHasPreviousPage() {
        return this.getCurrentPage() > 1;
    }

    @Override
    public int getPreviousPage() {
        return getCurrentPage() - 1;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public boolean hasSort() {
        return ValidationUtils.isNotEmpty(getSortColumn());
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortColumn() {
        return null;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
