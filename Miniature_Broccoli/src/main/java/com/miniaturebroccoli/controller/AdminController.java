package com.miniaturebroccoli.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miniaturebroccoli.pojo.Admin;
import com.miniaturebroccoli.pojo.Dataprocessing;
import com.miniaturebroccoli.service.AdminService;
import com.miniaturebroccoli.service.dataProcessingService;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.SensitiveWordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author scc
 */
@Api(tags = "管理员接口")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final dataProcessingService dps;

    public AdminController(AdminService adminService, dataProcessingService dps) {
        this.adminService = adminService;
        this.dps = dps;
    }

    @GetMapping()
    @ApiOperation("登录判断(用户名密码不能为空)")
    public Object addArticle(String username, String password, HttpServletRequest request) {
        Boolean word = word(username);
        Boolean word1 = word(password);
        if (word = word1 = true) {
            Set<String> set = SensitiveWordUtil.getSensitiveWord(username);
            Set<String> set1 = SensitiveWordUtil.getSensitiveWord(password);
            return ResultData.customize(500, "参数中含有敏感词", set + "" + set1);
        } else {
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("admin_nickname", username);
            queryWrapper.eq("admin_password", password);
            List<Admin> list = adminService.list(queryWrapper);
            if (list.size() > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                return String.valueOf(session);
            } else {
                return "登录失败";
            }
        }
    }

    /**
     * 敏感词检测
     *
     * @param str
     */
    private Boolean word(String str) {
        List<Dataprocessing> dataprocessings = dps.list(null);
        List<String> lists = dataprocessings.stream().map(Dataprocessing::getSensitiveWords).collect(Collectors.toList());
        Set set = new HashSet(lists);
        SensitiveWordUtil.init(set);
        /**
         *   调用SensitiveWordUtil工具类.comtains方法查看请求参数是否含有敏感词
         */
        boolean result = SensitiveWordUtil.contains(str);
        return result;
    }
}