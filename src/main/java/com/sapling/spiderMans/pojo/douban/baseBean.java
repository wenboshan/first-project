package com.sapling.spiderMans.pojo.douban;

/**
 * @author xulijie
 */
public class baseBean {

    private long limit = 50;
    private long page = 1;
    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getPage() {
        return (page-1)*limit;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
