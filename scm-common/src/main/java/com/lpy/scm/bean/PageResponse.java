package com.lpy.scm.bean;

import java.util.List;

/**
 * @author lpy
 * @date 2019/1/25 10:18
 */
public class PageResponse<T> extends ApiResponse<List<T>> {
    private Long total;
    private Integer page;
    private Integer rows;



    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
