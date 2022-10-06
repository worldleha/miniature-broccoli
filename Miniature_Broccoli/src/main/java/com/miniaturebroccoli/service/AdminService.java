package com.miniaturebroccoli.service;

import com.miniaturebroccoli.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author scc
 */
public interface AdminService extends IService<Admin> {
    /**
     * 登录判断
     */
    Object loginJudgment(Admin admin);

    /**
     * 返回管理员信息(昵称+邮箱)
     */
    List<Admin> aminInformation();
}
