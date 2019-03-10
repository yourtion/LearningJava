package com.yourtion.dubbo.one.api.enums;

/**
 * @author yourtion
 */

public enum StatusCode {

    /**
     * 成功
     */
    Success(0, "成功"),
    /**
     * 失败
     */
    Fail(-1, "失败"),
    /**
     * 无效的参数
     */
    InvalidParams(200, "无效的参数");

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
