package com.sdcsoft.datamanage.utils;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 * @author doudou
 * @param <T>
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;


    public Result setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg=resultCode.getMsg();
        return this;
    }

    public Result setResultCode(int code,String msg) {
        this.code = code;
        this.msg=msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
