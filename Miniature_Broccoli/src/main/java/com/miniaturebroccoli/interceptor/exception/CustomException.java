package com.miniaturebroccoli.interceptor.exception;


import com.miniaturebroccoli.utils.ResultData;


/**
 * 自定义异常类型
 *
 * @author pyy
 **/
public class CustomException extends RuntimeException {


    ResultData resultData;

    public CustomException(ResultData resultData) {
        this.resultData = resultData;
    }

    public ResultData getResultData(){
        return resultData;
    }

}
