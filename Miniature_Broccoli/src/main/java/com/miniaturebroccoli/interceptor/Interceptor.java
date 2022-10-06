package com.miniaturebroccoli.interceptor;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.pojo.Audience;
import com.miniaturebroccoli.utils.JwtTokenUtil;
import com.miniaturebroccoli.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */

@Slf4j
public class Interceptor implements HandlerInterceptor {
    @Autowired
    public Audience audience;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /* 忽略带JwtIgnore注解的请求, 不做后续token认证校验*/
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                log.error("JwtIgnore注解的请求, 不做后续token认证校验");
                return true;
            }
        }
        /*如果不是映射到方法就直接通过*/
        if (!(handler instanceof HandlerMethod)) {
            log.error("接口地址不存在,404错误,放行由启动类拦截404错误，跳转到404页面");
            return true;
        }
        if ((HttpMethod.OPTIONS).toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        /*获取请求头信息authorization信息*/
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("authHeader= {}", authHeader);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.error("Token不存在");
            throw new CustomException(ResultData.fail(200, "用户未登录,请先登录"));
        }
        /*获取token*/
        final String token = authHeader.substring(7);
        if (audience == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }
        /*验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息*/
        JwtTokenUtil.parseJWT(token, audience.getBase64Secret());
        return true;

    }
}