package com.zoc.furns.entity;

import java.util.List;

public class Page<T> {

    public static final Integer PAGE_SIZE = 3;

    private Integer pageNo;
    private Integer pageSize = PAGE_SIZE;

    // 一共多少页
    private Integer pageTotalCount;

    // 表示当前显示的数据
    private List<T> items;

    // 表示数据库有多少条记录
    private Integer totalRow;

    // 分页导航的字符串
    private String url;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
