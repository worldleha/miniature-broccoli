package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.constant.Constant;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.mapper.IpBlacklistMapper;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.IpBlacklist;
import com.miniaturebroccoli.service.IpBlacklistsService;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IpBlacklistServiceImpl extends ServiceImpl<IpBlacklistMapper, IpBlacklist> implements IpBlacklistsService {
    private final IpBlacklistMapper ipBlacklistMapper;

    public IpBlacklistServiceImpl(IpBlacklistMapper ipBlacklistMapper) {
        this.ipBlacklistMapper = ipBlacklistMapper;
    }

    /**
     * 判断是否为禁止访问ip
     */
    @Override
    public int isIpBlacklist(String ipAddress) {
        QueryWrapper<IpBlacklist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ip_address", ipAddress);
        List<IpBlacklist> ipBlacklists = ipBlacklistMapper.selectList(queryWrapper);
        if (ipBlacklists.size() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 添加ip黑名单
     */
    @Override
    public String addIpBlacklist(IpBlacklist ipBlacklist) {
        int insert = ipBlacklistMapper.insert(ipBlacklist);
        if (insert > 0) {
            return "添加成功";
        }
        else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "添加失败"));
        }
    }

    /**
     * 根据id删除ip黑名单
     */
    @Override
    public String deleteIpBlacklist(Long id) {
        int i = ipBlacklistMapper.deleteById(id);
        if (i > 0) {
            return "删除成功";
        }else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "删除成功"));
        }

    }

    /**
     * 修改ip黑名单
     */
    @Override
    public String updateIpBlacklist(IpBlacklist ipBlacklist) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("ip_id", ipBlacklist.getIpId());
        updateWrapper.set("ip_address", ipBlacklist.getIpAddress());
        int update = baseMapper.update(null, updateWrapper);
        if (update > 0) {
            return "修改成功";
        }
        else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "修改失败"));
        }

    }

    /**
     * 分页返回ip黑名单数据
     */
    @Override
    public Page<IpBlacklist> getIpBlacklistPage(int current) {
        int size = Constant.page_size;
        QueryWrapper<IpBlacklist> wrapper = new QueryWrapper<>();
        wrapper.select();
        Page<IpBlacklist> articlePage = new Page<>(current, size);
        return ipBlacklistMapper.selectPage(articlePage, wrapper);
    }


    /**
     * 批量删除ip黑名单数据
     */

    @Override
    public String deleteIpBlacklistInBulk(IdList idList) {
        List<Long> ids = idList.getIdList();
        if (!ids .isEmpty()&&ids.size()==0){
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        } else {
            int delete = ipBlacklistMapper.deleteBatchIds(ids);
            log.error(String.valueOf(delete));
            return "删除成功";
        }
    }
}
