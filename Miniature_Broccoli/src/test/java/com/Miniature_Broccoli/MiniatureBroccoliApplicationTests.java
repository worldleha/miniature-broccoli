package com.Miniature_Broccoli;

import com.Miniature_Broccoli.Mapper.articleMapper;
import com.Miniature_Broccoli.Mapper.commentMapper;
import com.Miniature_Broccoli.PoJo.Article;
import com.Miniature_Broccoli.PoJo.Comment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MiniatureBroccoliApplicationTests {
    @Autowired
    private articleMapper articleMapper;
    @Autowired
    private commentMapper commentMapper;

    @Test
    void contextLoads() {
        Article article = articleMapper.selectById(12);
        System.out.println(article.toString());
    }

    @Test
    void a() {
        List<Comment> commentList = commentMapper.selectList(null);
        System.out.println(commentList.toString());
    }
    @Test
    void d(){
        Page<Article> articlePage=new Page<>(2,5);
        Page<Article> orderTblPage = articleMapper.selectPage(articlePage,null);
        System.err.println("查询结果列表大小:"+orderTblPage.getRecords().size());
        System.err.println("总条数:"+orderTblPage.getTotal());
        System.err.println("当前页:"+orderTblPage.getCurrent());
        System.err.println("每页显示条数，默认 10条:"+"实际"+orderTblPage.getSize()+"条");
        System.err.println("总页数:"+orderTblPage.getPages());
    }
}

