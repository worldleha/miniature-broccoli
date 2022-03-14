package com.Miniature_Broccoli.service.impl;

import com.Miniature_Broccoli.Mapper.commentMapper;
import com.Miniature_Broccoli.PoJo.Comment;
import com.Miniature_Broccoli.service.commentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class commentServiceImpl extends ServiceImpl<commentMapper, Comment> implements commentService {

}
