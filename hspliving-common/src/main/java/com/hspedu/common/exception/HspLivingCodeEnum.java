package com.hspedu.common.exception;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-04 8:26
 */
public enum HspLivingCodeEnum {
    UNKNOWN_EXCEPTION(40000, "未知错误"),
    INVALID_EXCEPTION(40001,"参数错误"),;


    private Integer code;
    private String msg;

    HspLivingCodeEnum(Integer code, String msg) {
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

    HspLivingCodeEnum() {
    }
}
