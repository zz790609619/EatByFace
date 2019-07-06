package com.hiqiblog.ViewModel;

import java.util.List;

public class ResponseMessage<T> {

    private String code;

    private String msg;

    public T entity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }


}