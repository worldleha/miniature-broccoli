package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.mapper.CommentMapper;
import com.miniaturebroccoli.pojo.Comment;
import com.miniaturebroccoli.service.CommentService;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author scc
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 根据文章id返回所有评论
     */
    @Override
    public List<Comment> returnByArticleId(Long id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", id);
        List<Comment> comments = commentMapper.selectList(wrapper);
        if (comments.size() > 0) {
            return comments;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "id为" + id + "评论数据为空"));
        }
    }

    /**
     * 添加评论信息
     */
    @Override
    public String addComment(Comment comment) {
        int insert = commentMapper.insert(comment);
        if (insert == 1) {
            return "添加成功";
        }
        throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "添加失败"));
    }

    /**
     * 根据id删除评论
     */
    @Override
    public int deleteId(Long id) {
        return commentMapper.deleteById(id);
    }

    /**
     * 返回评论条数
     */
    @Override
    public Long getCommentTotal() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        return commentMapper.selectCount(queryWrapper);

    }

    /**
     * 根据id返回对应文章评论条数
     */
    @Override
    public String getArticleTotal(Long id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", id);
        Long aLong = commentMapper.selectCount(queryWrapper);
        if (aLong == 1) {
            return "删除成功";
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "没有id为" + id + "的评论数据"));
        }
    }
}
