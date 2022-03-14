package com.Miniature_Broccoli.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     *
     *  @ExceptionHandler,统一处理某一类异常，从而减少代码重复率和复杂度，比如要获取自定义异常可以 @ExceptionHandler(BusinessException.class)
     *  @ResponseStatus指定客户端收到的http状态码
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        log.error("\n" + ConsoleColors.RED_BOLD + "全局异常信息 ex={}",e.getMessage(), e);
        return ResultData.fail(ReturnCode.RC500.getCode(),e.getMessage());
    }
}
