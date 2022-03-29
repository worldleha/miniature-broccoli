package com.miniaturebroccoli.service.impl;

import com.miniaturebroccoli.mapper.CommentMapper;
import com.miniaturebroccoli.pojo.Comment;
import com.miniaturebroccoli.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author scc
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
