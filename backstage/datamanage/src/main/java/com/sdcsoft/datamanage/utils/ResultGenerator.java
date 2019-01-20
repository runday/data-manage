package com.sdcsoft.datamanage.utils;

/**
 * 相应结果生成工具
 * @author doudou
 */
public class ResultGenerator {

    public static <T> Result<T> genSuccessResult() {
        return new Result<T>().setResultCode(ResultCode.SUCCESS);
    }

    public static <T> Result<T> genSuccessResult(String msg) {
        return new Result<T>().setResultCode(ResultCode.SUCCESS).setMsg(msg);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result<T>().setResultCode(ResultCode.SUCCESS).setData(data);
    }

    public static <T> Result<T> genSuccessResult(String msg,T data) {
        return new Result<T>().setResultCode(ResultCode.SUCCESS).setMsg(msg).setData(data);
    }

    public static <T> Result<T> genSuccessResult(ResultCode resultCode, String msg,T data) {
        return new Result<T>().setResultCode(resultCode).setMsg(msg).setData(data);
    }

    public static <T> Result<T> genSuccessResult(int code,String msg,T data) {
        return new Result<T>().setResultCode(code,msg).setData(data);
    }

    public static <T> Result<T> genFailResult() {
        return new Result<T>().setResultCode(ResultCode.FAIL);
    }

    public static <T> Result<T> genFailResult(String msg) {
        return new Result<T>().setResultCode(ResultCode.FAIL).setMsg(msg);
    }

    public static <T> Result<T> genFailResult(String msg,T data) {
        return new Result<T>().setResultCode(ResultCode.FAIL).setMsg(msg).setData(data);
    }
    public static <T> Result<T> genFailResult(ResultCode resultCode) {
        return new Result<T>().setResultCode(resultCode);
    }
    public static <T> Result<T> genFailResult(ResultCode resultCode, String msg) {
        return new Result<T>().setResultCode(resultCode).setMsg(msg);
    }

    public static <T> Result<T> genFailResult(ResultCode resultCode, String msg,T data) {
        return new Result<T>().setResultCode(resultCode).setMsg(msg).setData(data);
    }

    public static <T> Result<T> genFailResult(int code,String msg,T data) {
        return new Result<T>().setResultCode(code,msg).setData(data);
    }
    public static <T> Result<T> genFailResult(int code,String msg) {
        return new Result<T>().setResultCode(code,msg);
    }

}
