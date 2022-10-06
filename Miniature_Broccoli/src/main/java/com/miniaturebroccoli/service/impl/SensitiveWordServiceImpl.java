package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.constant.Constant;
import com.miniaturebroccoli.mapper.SensitiveWordMapper;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.SensitiveWord;
import com.miniaturebroccoli.service.SensitiveWordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveWordServiceImpl extends ServiceImpl<SensitiveWordMapper, SensitiveWord> implements SensitiveWordService {
    private final SensitiveWordMapper dpm;

    public SensitiveWordServiceImpl(SensitiveWordMapper dpm) {
        this.dpm = dpm;
    }

    /**
     * 添加敏感词数据
     */
    @Override
    public String addData(SensitiveWord dp) {
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
    public String updateData(SensitiveWord dp) {
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
    public String deleteId(Long id) {
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
        int size = Constant.page_size;
        Page<SensitiveWord> page = new Page<>(current, size);
        Page<SensitiveWord> orderTblPage = dpm.selectPage(page, null);
        if (orderTblPage.getRecords().size() > 0) {
            return orderTblPage;
        } else {
            return "数据为空";
        }
    }

    /**
     * 获取全部敏感词数据
     */
    @Override
    public Object getSensitiveWord() {
        List<SensitiveWord> sensitiveWords = dpm.selectList(null);
        if (sensitiveWords.size() > 0) {
            return sensitiveWords;
        } else {
            return "数据为空";
        }
    }

    /**
     * 返回敏感词条数
     */
    @Override
    public Object total() {
        QueryWrapper<SensitiveWord> queryWrapper = new QueryWrapper<>();
        Long count = dpm.selectCount(queryWrapper);
        if (count != null) {
            return count;
        }
        return "数据为空";
    }

    /**
     * 批量删除敏感词数据
     */
    @Override
    public String deleteSensitiveWordInBulk(IdList idList) {
        List<Long> ids = idList.getIdList();
        if (!ids.isEmpty() && ids.size() == 0) {
            return "没有数据";
        } else {
            int delete = dpm.deleteBatchIds(ids);
            log.error(String.valueOf(delete));
            return "删除成功";
        }
    }
}
