package com.yourtion.dubbo.one.api.response;

import com.yourtion.dubbo.one.api.enums.StatusCode;

/**
 * @author yourtion
 */
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public BaseResponse() {

    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(StatusCode status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
