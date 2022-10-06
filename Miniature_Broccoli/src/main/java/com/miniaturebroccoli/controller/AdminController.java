package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.annotation.MyLog;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.pojo.Admin;
import com.miniaturebroccoli.pojo.Audience;
import com.miniaturebroccoli.service.AdminService;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import static com.miniaturebroccoli.utils.VerifyUtil.getVerification;

/**
 * @author scc
 */
@Slf4j
@RestController
public class AdminController {
    private final AdminService adminService;
    private final Audience audience;

    public AdminController(AdminService adminService, Audience audience) {
        this.adminService = adminService;
        this.audience = audience;
    }

    /**
     * 登录验证
     */
    @SuppressWarnings("rawtypes")
    @MyLog("登录")
    @PostMapping("/admin")
    public Object loginJudgment(@RequestBody Admin admin) {
        return adminService.loginJudgment(admin);
    }

    /**
     * 返回管理员信息(昵称+邮箱)
     */
    @MyLog("返回管理员信息")
    @GetMapping("/adminInfo")
    public List<Admin> aminInformation() {
        return adminService.aminInformation();
    }

    /**
     * 获取验证码
     */
    @JwtIgnore
    @MyLog("获取验证码")
    @GetMapping("/getVerificationCode")
    public String getVerificationCode(HttpServletRequest request) {
        HashMap<String, String> verification = getVerification();
        log.info("验证码" + verification.get("verification_code"));
        log.info("验证码图像base64:" + "\n" + verification.get("verification_code_image"));
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("verification_code", verification.get("verification_code"));
        httpSession.setMaxInactiveInterval(60);//设置session作用时间为60秒
        return verification.get("verification_code_image");
    }

    /**
     *判断验证码是否正确
     */
    @MyLog("判断验证码")
    @GetMapping("/getVerificationCode/{str}")
    public Object JudgmentVerificationCode(HttpServletRequest request,@PathVariable String str) {
        HttpSession httpSession = request.getSession();
        String verification_code = (String) httpSession.getAttribute("verification_code");
        if (verification_code.equals("")) {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "验证码为空"));
        }
        if (str.equals(verification_code)) {
            httpSession.invalidate();
            return true;
        }
        else {
            return false;
        }

    }
}