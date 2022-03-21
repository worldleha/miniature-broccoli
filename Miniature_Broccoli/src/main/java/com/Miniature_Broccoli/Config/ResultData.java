package com.Miniature_Broccoli.Config;

import lombok.Data;

<<<<<<< Updated upstream
=======
/**
 * 统一数据返回格式
 *
 * @param <T>
 */
>>>>>>> Stashed changes
@Data
public class ResultData<T> {

    // 结果状态码
    private int code;

    // 响应信息
    private String msg;

    // 响应数据
    private T data;

    // 接口请求时间
<<<<<<< Updated upstream
    private long timestamp ;

    public ResultData (){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
=======
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
>>>>>>> Stashed changes
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCode.RC200.getCode());
        resultData.setMsg(ReturnCode.RC200.getMsg());
        resultData.setData(data);
<<<<<<< Updated upstream
        return  resultData;
    }

    public static <T> ResultData<T> fail(int code, String msg){
=======
        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String msg) {
>>>>>>> Stashed changes
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        return resultData;
    }
}
