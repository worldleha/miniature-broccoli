package com.miniaturebroccoli.exception;

import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.awt.Color.YELLOW;

/**
 *  @author scc
 * 全局异常处理
 *
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    /**
     * "@ExceptionHandler",统一处理某一类异常，从而减少代码重复率和复杂度，比如要获取自定义异常可以 @ExceptionHandler(BusinessException.class)
     * @ResponseStatus指定客户端收到的http状态码
     */
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResultData<String> noHandleFoundException(NoHandlerFoundException e) {
//        log.error(GREEN + "全局异常信息 ex={}", e.getMessage(), e);
//        return ResultData.fail(ReturnCode.RC404.getCode(), "请检查请求路径或者类型是否正确||具体原因:" + e.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        log.error(YELLOW + "全局异常信息 ex={}", e.getMessage(), e);
        return ResultData.fail(ReturnCode.RC500.getCode(), "服务器内部错误||具体原因" + e.getMessage());
    }
}
