package com.miniaturebroccoli.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.miniaturebroccoli.pojo.Dataprocessing;

/**
 * @author scc
 */
public interface dataProcessingService extends IService<Dataprocessing> {
    /**
     * 添加敏感词数据
     */
    Object adddata(Dataprocessing dp);

    /**
     * 修改敏感词数据
     */
    Object updatedata(Dataprocessing dp);

    /**
     * 根据id删除敏感词数据
     */
    Object deleteId(Long id);

    /**
     * 分页返回敏感词数据
     */
    Object getPage(int current);

    /**
     * 返回敏感词总数
     *
     * @return
     */
    Object total();
}
