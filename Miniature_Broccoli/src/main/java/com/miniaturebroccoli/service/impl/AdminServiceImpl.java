package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.mapper.AdminMapper;
import com.miniaturebroccoli.pojo.Admin;
import com.miniaturebroccoli.pojo.Audience;
import com.miniaturebroccoli.service.AdminService;
import com.miniaturebroccoli.utils.JwtTokenUtil;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.miniaturebroccoli.constant.Constant.role;

/**
 * @author scc
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    private final AdminMapper adminMapper;
    private final Audience audience;

    public AdminServiceImpl(AdminMapper adminMapper, Audience audience) {
        this.adminMapper = adminMapper;
        this.audience = audience;
    }

    /**
     * 登录判断
     */
    @Override
    public Object loginJudgment(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_nickname", admin.getAdminNickname());
        queryWrapper.eq("admin_password", admin.getAdminPassword());
        List<Admin> admins = adminMapper.selectList(queryWrapper);
        if (admins.size() > 0) {
            /*全局唯一标识符*/
            String userId = UUID.randomUUID().toString();
            /* 创建token*/
            return JwtTokenUtil.createJWT(userId, admin.getAdminNickname(), role, audience);
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "账号密码错误"));
        }
    }

    /**
     * 返回管理员信息(昵称+邮箱)
     */
    @Override
    public List<Admin> aminInformation() {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("admin_nickname", "admin_email");
        return adminMapper.selectList(queryWrapper);
    }
}
