package com.pzhu.crm.dataobj;

import java.io.Serializable;

/**
 * 基础返回结果
 *
 * @author Eason
 * @date 2018/11/24
 **/
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = -4936913867196737015L;
    /**
     * 处理状态
     */
    private boolean success = true;
    /**
     * 数据
     */
    private T data;
    /**
     * 错误信息
     */
    private String msg;

    /**
     * 分页信息
     */
    private Pagination pagination;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
