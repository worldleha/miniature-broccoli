package com.miniaturebroccoli.utils;

import lombok.Data;

/**
 * 统一数据返回格式
 *
 * @param <T>
 * @author scc
 */
@Data
public class ResultData<T> {

    /**
     * 结果状态码
     */

    private int code;

    /**
     * 响应信息
     */

    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 接口请求时间
     */

    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 请求成功返回格式
     *
     * @param <T>
     * @param data
     * @return
     */
    public static <T> ResultData success(T data) {
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCode.RC200.getCode());
        resultData.setMsg(ReturnCode.RC200.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 请求错误返回格式
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> fail(int code, String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 自定义返回格式
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> customize(int code, String msg, T data) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

}
