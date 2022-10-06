package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.annotation.MyLog;
import com.miniaturebroccoli.pojo.Comment;
import com.miniaturebroccoli.service.CommentService;
import com.miniaturebroccoli.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author scc
 */
@Slf4j
@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 根据文章id返回所有评论
     */
    @MyLog("根据文章id返回所有评论")
    @JwtIgnore
    @GetMapping("/comment/{id}")
    public Object returneeArticled(@PathVariable Long id) {
        return  commentService.returnByArticleId(id);

    }

    /**
     * 根据文章id返回对应文章评论条数
     */
    @MyLog("根据文章id返回对应文章评论条数")
    @JwtIgnore
    @GetMapping("/comment_total/{id}")
    public Object articleTotal(@PathVariable Long id) {
        return  commentService.getArticleTotal(id);
    }

    /**
     * 返回总评论数
     */
    @MyLog("返回总评论数")
    @JwtIgnore
    @GetMapping("/comment_total")
    public Long commentTotal() {
        return commentService.getCommentTotal();
    }

    /**
     * 添加评论
     */
    @MyLog("添加评论")
    @PostMapping("/comment")
    public String addComment(@RequestBody Comment comment, HttpServletRequest request) {
        /* 获取评论者ip地址**/
        String ipAdder = IpUtil.getIpAdder(request);
        /*获取评论者ip和评论时间**/
        comment.setIpAddress(ipAdder);
        comment.setCommentTime(String.valueOf(System.currentTimeMillis()));
        return commentService.addComment(comment);
    }

    /**
     * 根据评论id删除评论
     */
    @MyLog("根据评论id删除评论")
    @DeleteMapping("/comment/{id}")
    public int deleteId(@PathVariable Long id) {
      return commentService.deleteId(id);

    }
}