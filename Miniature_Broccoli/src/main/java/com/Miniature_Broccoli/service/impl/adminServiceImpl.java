package com.Miniature_Broccoli.service.impl;

import com.Miniature_Broccoli.Mapper.adminMapper;
import com.Miniature_Broccoli.Mapper.articleMapper;
import com.Miniature_Broccoli.PoJo.Admin;
import com.Miniature_Broccoli.PoJo.Article;
import com.Miniature_Broccoli.service.adminService;
import com.Miniature_Broccoli.service.articleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class adminServiceImpl extends ServiceImpl<adminMapper, Admin> implements adminService {

}
