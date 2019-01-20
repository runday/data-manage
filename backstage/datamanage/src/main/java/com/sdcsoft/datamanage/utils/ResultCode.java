package com.sdcsoft.datamanage.utils;

/**
 * 响应码枚举，参考HTTP状态码的语义
 * @author doudou
 */
public enum ResultCode {

    SUCCESS(200,"success"),
    FAIL(400,"fail");
    private final int code;
    private final String msg;

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
