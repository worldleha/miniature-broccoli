package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.pojo.Admin;
import com.miniaturebroccoli.pojo.Audience;
import com.miniaturebroccoli.service.AdminService;
import com.miniaturebroccoli.utils.JwtTokenUtil;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.SensitiveWordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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

    @PostMapping("/admin")
    public Object Login_judgment(@RequestBody Admin admin, HttpServletRequest request) {
        log.info("数据为" + admin.toString());
        Object cofing = SensitiveWordUtil.cofing(admin.toString());
        if (cofing != null) {
            return ResultData.customize(500, "参数中含有敏感词", cofing);
        } else {
            List judgment = (List) adminService.Login_judgment(admin.getAdminNickname(), admin.getAdminPassword());
            if (judgment.size() > 0) {
                //全局唯一标识符
                String userId = UUID.randomUUID().toString();
                String role = "admin";
                // 创建token
                return JwtTokenUtil.createJWT(userId, admin.getAdminNickname(), "管理员", audience);
            } else {
                return "账号密码错误";
            }
        }
    }
    /**
     * 返回管理员信息(昵称+邮箱)
     */
    @JwtIgnore
    @GetMapping("/adminInfo")
    public Object amin_information() {
        return adminService.amin_information();
    }
}