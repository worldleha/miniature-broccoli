package com.miniaturebroccoli.service.impl;

import com.miniaturebroccoli.mapper.ArticleMapper;
import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
/**
 * @author scc
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
