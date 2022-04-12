package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.mapper.AdminMapper;
import com.miniaturebroccoli.pojo.Admin;
import com.miniaturebroccoli.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author scc
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 登录判断
     */
    @Override
    public Object Login_judgment(String username, String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_nickname", username);
        queryWrapper.eq("admin_password", password);
        return adminMapper.selectList(queryWrapper);
    }
    /**
     * 返回管理员信息(昵称+邮箱)
     */
    @Override
    public Object amin_information() {
        QueryWrapper <Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("admin_nickname","admin_email");
        return adminMapper.selectList(queryWrapper);
    }
}
