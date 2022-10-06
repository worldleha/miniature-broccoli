package com.miniaturebroccoli.service;

import com.miniaturebroccoli.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author scc
 */
public interface CommentService extends IService<Comment> {
    /**
     * 根据文章id返回所有评论
     */
    List<Comment> returnByArticleId(Long id);

    /**
     * 添加评论信息
     */
    String addComment(Comment comment);

    /**
     * 根据id删除评论
     */
    int deleteId(Long id);

    /**
     * 返回总评论数
     */
    Long getCommentTotal();

    /**
     * 根据文章id返回对应文章评论条数
     */
    String getArticleTotal(Long id);
}
