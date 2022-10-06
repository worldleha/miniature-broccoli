package com.miniaturebroccoli.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.SysLog;

public interface LogService extends IService<SysLog> {

    /**
     * 添加日志信息
     */
    int addLog(SysLog sysLog);

    /**
     * 分页返回日志信息
     */
    Page<SysLog> getLogPage(int current);

    /**
     * 批量删除日志数据
     */
    String deleteLogInBulk(IdList idList);
}
