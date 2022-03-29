package com.miniaturebroccoli.supper;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author scc
 */
public class SessionInterceptor implements HandlerInterceptor {

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            return true;
        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{code:0,message:\"not login!\"}");
            return false;
        }
    }


}