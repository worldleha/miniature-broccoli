package com.miniaturebroccoli.service;

import com.miniaturebroccoli.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author scc
 */
public interface AdminService extends IService<Admin> {
    /**
     * 登录判断
     *
     * @param username
     * @param password
     * @return
     */
    Object Login_judgment(String username, String password);

    /**
     * 返回管理员信息(昵称+邮箱)
     */
    Object amin_information();
}
