package com.miniaturebroccoli.aop;

import com.miniaturebroccoli.annotation.MyLog;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.pojo.Audience;
import com.miniaturebroccoli.pojo.SysLog;
import com.miniaturebroccoli.service.LogService;
import com.miniaturebroccoli.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 切面处理类记录接口操作日志
 * @author scc
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    private final LogService logService;
    private final Audience audience;

    public LogAspect(LogService logService, Audience audience) {
        this.logService = logService;
        this.audience = audience;
    }

    /**
     * 定义切点 @Pointcut
     * 在注解的位置切入代码
     **/
    @Pointcut("@annotation( com.miniaturebroccoli.annotation.MyLog)")
    public void logPointCut() {
    }

    /**
     * 切面 配置通知
     **/
    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        /*获取请求对象**/
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        /*创建日志对象*/
        SysLog sysLog = new SysLog();
        /*获取请求方式*/
        String logRequestMode = request.getMethod();
        /*添加请求方式 */
        sysLog.setLogRequestMode(logRequestMode);
        System.err.println(logRequestMode);
        /*获取请求参数*/
        String params = Arrays.toString(joinPoint.getArgs());
        /*设置请求参数*/
        sysLog.setLogParams(params);
        /*从切面织入点处通过反射机制获取织入点处的方法*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        sysLog.setLogUsername("");
        if (myLog != null) {
            String value = myLog.value();
            //保存获取的操作
            sysLog.setLogOperation(value);
            /*根据实际项目 所有get请求不做敏感词拦截
             */
            if (!logRequestMode.equalsIgnoreCase("GET")) {
                String requestURI = request.getRequestURI();
                /* 请求的参数 */
                Object coding = SensitiveWordUtil.cofing(params);
                if (coding != null) {
                    throw new CustomException(ResultData.customize(ReturnCode.RC500.getCode(), "请求参数中含有敏感词", coding));
                }
                sysLog.setLogParams(params);
                if (!requestURI.equals("/admin")) {
                    String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
                    String token = authHeader.substring(7);
                    System.err.println(authHeader + "\n" + token);
                    /*获取管理员昵称*/
                    String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
                    sysLog.setLogUsername(username);
                }
            }
        }
        /*获取请求的类名*/
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setLogMethod(className + "." + methodName);
        /* 添加请求时间 */
        sysLog.setLogDate(DateUtils.getSystemTime());
        /* 获取用户ip地址 */
        sysLog.setLogIp(IpUtil.getIpAdder(request));
        /* 调用service保存SysLog实体类到数据库 */
        int i = logService.addLog(sysLog);
        log.info(""+i);
        if (i == 1) {
            log.info("记录操作结果成功");
        }
    }
}
