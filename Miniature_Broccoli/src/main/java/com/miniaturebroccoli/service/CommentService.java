package com.miniaturebroccoli.service;

import com.miniaturebroccoli.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author scc
 */
public interface CommentService extends IService<Comment> {
    /**
     * 根据文章id返回所有评论
     */
    Object Returnby_articleid(Long id);

    /**
     * 添加评论信息
     */
    Object addcomment(Comment comment);

    /**
     * 根据id删除评论
     */
    Object deleteId(Long id);

    /**
     * 返回总评论数
     *
     * @return
     */
    Object total();

    /**
     * 根据文章id返回对应文章评论条数
     */
    Object article_total(Long id);
}
