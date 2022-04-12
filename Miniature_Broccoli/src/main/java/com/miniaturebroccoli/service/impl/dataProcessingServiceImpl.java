package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.constant.constant;
import com.miniaturebroccoli.mapper.dataProcessingMapper;
import com.miniaturebroccoli.pojo.Dataprocessing;
import com.miniaturebroccoli.service.dataProcessingService;
import org.springframework.stereotype.Service;

@Service
public class dataProcessingServiceImpl extends ServiceImpl<dataProcessingMapper, Dataprocessing> implements dataProcessingService {
    private final dataProcessingMapper dpm;

    public dataProcessingServiceImpl(dataProcessingMapper dpm) {
        this.dpm = dpm;
    }

    /**
     * 添加敏感词数据
     */
    @Override
    public Object adddata(Dataprocessing dp) {
        int insert = dpm.insert(dp);
        if (insert > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    /**
     * 修改敏感词数据
     */
    @Override
    public Object updatedata(Dataprocessing dp) {
        int i = dpm.updateById(dp);
        if (i > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    /**
     * 根据id删除敏感词数据
     */
    @Override
    public Object deleteId(Long id) {
        int i = dpm.deleteById(id);
        if (i > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    /**
     * 根据分页返回敏感词数据
     */

    @Override
    public Object getPage(int current) {
        int size = constant.page_size;
        Page<Dataprocessing> dpage = new Page<>(current, size);
        Page<Dataprocessing> orderTblPage = dpm.selectPage(dpage, null);
        if (orderTblPage.getRecords().size() > 0) {
            return orderTblPage;
        } else {
            return "数据为空";
        }
    }

    /**
     * 返回敏感词条数
     */
    @Override
    public Object total() {
        QueryWrapper<Dataprocessing> queryWrapper = new QueryWrapper<>();
        Long count = dpm.selectCount(queryWrapper);
        return count;
    }
}
