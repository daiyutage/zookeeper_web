package com.daiyutage.bean;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by Administrator on 2017/7/23.
 */
public class HttpResponse {
    String message;
    Integer code;
    Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public HttpResponse(String message, Boolean success, Integer code) {
        this.message = message;
        this.success = success;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public HttpResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
