package me.zhyx.common.base.entity;

/**
 * Author: zhyx
 * Date:2018/5/7
 * Time:11:32
 */
public class PagePlugin extends BaseEntity{
    private Integer currPage=1;
    private Integer pageSize=10;

    public PagePlugin() {
    }

    public PagePlugin(Integer currPage, Integer pageSize) {
        this.currPage = currPage;
        this.pageSize = pageSize;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
