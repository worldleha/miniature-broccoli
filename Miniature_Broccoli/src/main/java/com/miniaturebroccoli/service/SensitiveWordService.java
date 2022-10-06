package com.miniaturebroccoli.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.SensitiveWord;

/**
 * @author scc
 */
public interface SensitiveWordService extends IService<SensitiveWord> {
    /**
     * 添加敏感词数据
     */
    Object addData(SensitiveWord dp);

    /**
     * 修改敏感词数据
     */
    Object updateData(SensitiveWord dp);

    /**
     * 根据id删除敏感词数据
     */
    Object deleteId(Long id);

    /**
     * 分页返回敏感词数据
     */
    Object getPage(int current);

    /**
     * 获取全部敏感词数据
     * @return
     */
    Object getSensitiveWord();

    /**
     * 返回敏感词总数
     *
     */
    Object total();
    /**
     * 批量删除敏感词数据
     */
    String deleteSensitiveWordInBulk(IdList idList);

}
