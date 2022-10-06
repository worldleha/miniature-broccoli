package com.miniaturebroccoli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.IpBlacklist;


public interface IpBlacklistsService extends IService<IpBlacklist> {
    /**
     * 判断是否为禁止访问ip
     */
     int isIpBlacklist(String ipAddress);
    /**
     * 添加ip黑名单
     */
    String addIpBlacklist(IpBlacklist ipBlacklist);

    /**
     * 删除ip黑名单
     */
    String deleteIpBlacklist(Long id);

    /**
     * 修改ip黑名单
     */
    String updateIpBlacklist(IpBlacklist ipBlacklist);

    /**
     * 分页返回ip黑名单数据
     */
    Object getIpBlacklistPage(int current);

    /**
     * 批量删除ip黑名单数据
     */
    String deleteIpBlacklistInBulk(IdList idList);
}
