package cn.greenwishing.bms.dto;

import cn.greenwishing.bms.utils.paging.AbstractPaging;
import cn.greenwishing.bms.utils.paging.Paging;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank wu
 */
public abstract class AbstractPagingDTO<T, P extends AbstractPaging> extends AbstractDTO implements Paging<T> {
    protected int pageSize = 10;
    protected int currentPage = 1;
    private int pageCount;
    private long totalCount;
    private boolean hasPrevious;
    private boolean hasNext;
    private int previousPage;
    private int nextPage;
    private int lastPage;

    protected String sortName;
    protected String sortType;

    protected List<T> list = new ArrayList<>();

    protected AbstractPagingDTO() {
    }

    public abstract P toPaging();

    @SuppressWarnings("unchecked")
    public <D extends AbstractPagingDTO> D convertResult(P paging) {
        this.pageSize = paging.getPageSize();
        this.currentPage = paging.getCurrentPage();
        this.previousPage = paging.getPreviousPage();
        this.lastPage = paging.getLastPage();
        this.totalCount = paging.getTotalCount();
        this.nextPage = paging.getNextPage();
        this.pageCount = paging.getPageCount();
        this.currentPage = paging.getCurrentPage();
        this.hasPrevious = paging.isHasPreviousPage();
        this.hasNext = paging.isHasNextPage();
        convertList(paging);
        return (D) this;
    }

    protected abstract void convertList(P paging);

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public boolean isHasPreviousPage() {
        return hasPrevious;
    }

    @Override
    public boolean isHasNextPage() {
        return hasNext;
    }

    @Override
    public int getPreviousPage() {
        return previousPage;
    }

    @Override
    public int getNextPage() {
        return nextPage;
    }

    @Override
    public int getLastPage() {
        return lastPage;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
