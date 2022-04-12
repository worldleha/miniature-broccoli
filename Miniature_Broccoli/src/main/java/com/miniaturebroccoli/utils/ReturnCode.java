package com.miniaturebroccoli.utils;

/**
 * @author scc
 * 返回状态码设置
 */
public enum ReturnCode {
    /**
     * 常用状态码
     */
    RC200(200, "操作成功"),
    RC500(500, "操作失败"),
    RC405(405,"请求方式不支持");

    /**
     * 自定义状态码
     */

    private final int code;

    /**
     * 自定义描述
     */
    private final String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
