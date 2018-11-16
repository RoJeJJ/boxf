package com.roje.boxf.constant.response;

public enum  RespData {
    SUCCESS(0,"成功"),
    LOGIN___ERROR_ACCOUNT_OR_PASSWORD(101,"用户名或密码错误"),
    LOGIN___ACCOUNT_FORBIDDEN(102,"账号被禁止登录,请联系管理员"),
    LOGIN___GATE_NOT_FOUND(103,"网关服务未找到"),

    REGISTER_ACCOUNT_EXISTS(201,"用户名已经被使用了");

    private int code;

    private String msg;

    RespData(int i, String msg) {
        this.code = i;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
