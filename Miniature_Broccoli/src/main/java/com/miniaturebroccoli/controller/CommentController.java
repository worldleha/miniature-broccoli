package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.pojo.Comment;
import com.miniaturebroccoli.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author scc
 */
@Api(tags = "评论接口")
@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation("根据文章id返回所有评论信息")
    @GetMapping("/{id}")
    public Object save(@ApiParam(name = "id", value = "文章id", required = true) @PathVariable Long id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", id);
        List<Comment> list = commentService.list(wrapper);
        if (list.size() > 0) {
            return list;
        } else {
            return "数据为空";
        }
    }

    @ApiOperation("添加评论")
    @PostMapping
    public String add(@RequestBody Comment comment) {
        boolean b = commentService.save(comment);
        if (b) {
            return "添加成功";
        }
        return "添加失败";
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Object deleteId(@ApiParam(name = "id", value = "文章id", required = true) @PathVariable Long id) {
        boolean b = commentService.removeById(id);
        if (b) {
            return "删除成功";
        } else {
            return "没有查询到id=" + id + "的数据";
        }
    }
}