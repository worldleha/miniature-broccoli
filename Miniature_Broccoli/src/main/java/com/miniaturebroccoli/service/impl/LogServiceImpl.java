package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.constant.Constant;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.mapper.LogMapper;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.SysLog;
import com.miniaturebroccoli.service.LogService;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLog> implements LogService {
    private final LogMapper logMapper;

    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    /**
     * 添加日志信息
     */
    @Override
    public int addLog(SysLog sysLog) {
        return logMapper.insert(sysLog);
    }

    /**
     * 分页返回日志信息
     */
    @Override
    public Page<SysLog> getLogPage(int current) {
        int size = Constant.page_size;
        Page<SysLog> page = new Page<>(current, size);
        Page<SysLog> sysLogPage = logMapper.selectPage(page, null);
        if (sysLogPage.getRecords().size() > 0) {
            return sysLogPage;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        }
    }

    /**
     * 批量删除日志数据
     */
    @Override
    public String deleteLogInBulk(IdList idList) {
        List<Long> ids = idList.getIdList();
        if (!ids.isEmpty() && ids.size() == 0) {
            return "没有数据";
        } else {
            int delete = logMapper.deleteBatchIds(ids);
            log.error(String.valueOf(delete));
            return "删除成功";
        }
    }
}
