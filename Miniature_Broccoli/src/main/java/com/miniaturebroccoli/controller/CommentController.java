package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.pojo.Comment;
import com.miniaturebroccoli.service.CommentService;
import com.miniaturebroccoli.utils.IpUtil;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.SensitiveWordUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author scc
 */
@RestController
@CrossOrigin
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    /**
     * 根据文章id返回所有评论
     */
    @JwtIgnore
    @GetMapping("/comment/{id}")
    public Object Returnby_articleid( @PathVariable Long id) {
        return commentService.Returnby_articleid(id);
    }
    /**
     * 根据文章id返回对应文章评论条数
     */
    @JwtIgnore
    @GetMapping("/comment_total/{id}")
    public Object article_total( @PathVariable Long id) {
        return commentService.article_total(id);
    }
    /**
     * 返回总评论数
     */
    @JwtIgnore
    @GetMapping("comment/total")
    private Object total() {
        return commentService.total();
    }

    /**
     * 添加评论
     */
    @PostMapping("comment")
    public Object addcomment(@RequestBody Comment comment, HttpServletRequest request) {
        Object cofing = SensitiveWordUtil.cofing(String.valueOf(comment));
        if (cofing != null) {
            return ResultData.customize(500, "参数中含有敏感词", cofing);
        } else {
            /* 获取评论者ip地址**/
            String ipAddr = IpUtil.getIpAddr(request);
            /*获取评论者ip和评论时间**/
            comment.setIpAddress(ipAddr);
            comment.setCommentTime(String.valueOf(System.currentTimeMillis()));
            return commentService.addcomment(comment);
        }

    }

    /**
     * 根据评论id删除评论
     * @param id
     * @return
     */
    @DeleteMapping("comment/{id}")
    public Object deleteId( @PathVariable Long id) {
        return commentService.deleteId(id);
    }

}