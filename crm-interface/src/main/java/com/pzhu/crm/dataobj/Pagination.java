package com.pzhu.crm.dataobj;

import java.io.Serializable;

/**
 * @Author : Eason
 * 2018/11/24 18:59
 **/
public class Pagination implements Serializable {

    private static final long serialVersionUID = 5217382450007450580L;
    /**
     *  当前页码
     */
    private Integer pageNum = 1;
    /**
     * 每页的记录数
     */
    private Integer pageSize = 10;

    private Long tatol;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTatol() {
        return tatol;
    }

    public void setTatol(Long tatol) {
        this.tatol = tatol;
    }
}
