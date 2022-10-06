package com.miniaturebroccoli.interceptor.exception;

import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author scc
 *
 * @RestControllerAdvice，RestController的增强类，可用于实现全局异常处理器
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    /**
     * 处理服务器内部异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        log.error("服务器内部错误" + e);
        return ResultData.fail(
                ReturnCode.RC500.getCode(),
                ReturnCode.RC500.getMsg()
                        + "错误信息:"
                        + e.getMessage());
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ResultData handleException(CustomException e) {
        // 打印异常信息
        log.error("自定义异常信息" + e.getResultData());
        return e.getResultData();
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResultData<Object> handleException(HttpRequestMethodNotSupportedException e) {
        return ResultData.fail(ReturnCode.RC405.getCode(), ReturnCode.RC405.getMsg());
    }
}
