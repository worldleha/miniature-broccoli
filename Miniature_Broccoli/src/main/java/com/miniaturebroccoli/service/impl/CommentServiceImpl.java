package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miniaturebroccoli.mapper.CommentMapper;
import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.pojo.Comment;
import com.miniaturebroccoli.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public Object Returnby_articleid(Long id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", id);
        List<Comment> list = commentMapper.selectList(wrapper);
        if (list.size() > 0) {
            return list;
        } else {
            return "数据为空";
        }
    }

    /**
     * 添加评论信息
     */
    @Override
    public Object addcomment(Comment comment) {
        int b = commentMapper.insert(comment);
        if (b > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    /**
     * 根据id删除评论
     */
    @Override
    public Object deleteId(Long id) {
        int b = commentMapper.deleteById(id);
        if (b > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    /**
     * 返回评论条数
     */
    @Override
    public Object total() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        Long count = commentMapper.selectCount(queryWrapper);
        return count;
    }

    /**
     * 根据id返回对应文章评论条数
     */
    @Override
    public Object article_total(Long id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id",id);
        Long count = commentMapper.selectCount(queryWrapper);
        return count;
    }
}
