package com.miniaturebroccoli.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniaturebroccoli.utils.ResultData;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author scc
 *
 *  ResponseBodyAdvice的作用：**拦截Controller方法的返回值，统一处理返回值/响应体，一般用来统一返回格式，加解密，签名等等
 * 全局相应处理
 */
@RestControllerAdvice(basePackages = "com.miniaturebroccoli")

public class ResponseAdvice implements ResponseBodyAdvice {

    private final ObjectMapper objectMapper;

    public ResponseAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 如果Controller直接返回String的话，SpringBoot是直接返回，故我们需要手动转换成json。
        if (body instanceof String) {
            return objectMapper.writeValueAsString(ResultData.success(body));
        }
        // 如果返回的结果是ResultData对象，直接返回即可。
        if (body instanceof ResultData) {
            return body;
        }
        return ResultData.success(body);
    }
}
