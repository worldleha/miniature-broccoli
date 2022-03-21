package com.Miniature_Broccoli.Controller;

import com.Miniature_Broccoli.PoJo.Admin;
import com.Miniature_Broccoli.service.adminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "管理员接口")
@RestController
@RequestMapping("/admin")
public class adminController {
    private final adminService adminService;

    public adminController(adminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping()
    @ApiOperation("登录判断(用户名密码不能为空)")
    public Object addArticle(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_nickname", username);
        queryWrapper.eq("admin_password", password);
        List<Admin> list = adminService.list(queryWrapper);
        if (list.size() > 0) {
            return "登录成功";
        } else {
            return "登录失败";
        }
    }
}