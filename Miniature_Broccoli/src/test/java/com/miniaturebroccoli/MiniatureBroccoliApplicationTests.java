package com.miniaturebroccoli;

import com.miniaturebroccoli.mapper.ArticleMapper;
import com.miniaturebroccoli.mapper.CommentMapper;
import com.miniaturebroccoli.mapper.ReplyMapper;
import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.pojo.Comment;
import com.miniaturebroccoli.pojo.Replyform;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MiniatureBroccoliApplicationTests {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    void contextLoads() {
        List<Article> articles = articleMapper.selectList(null);
        List<Comment> commentList = commentMapper.selectList(null);
        List<Replyform> replyMappers = replyMapper.selectList(null);
        System.err.println("文章表信息" + articles.toString() + "\n");
//                +"评论表信息"+commentList.toString()
//                +"回复表信息"+replyMappers.toString());
    }

    @Test
    void d() {
        Page<Article> articlePage = new Page<>(2, 5);
        Page<Article> orderTblPage = articleMapper.selectPage(articlePage, null);
        System.err.println("查询结果列表大小:" + orderTblPage.getRecords().size());
        System.err.println("总条数:" + orderTblPage.getTotal());
        System.err.println("当前页:" + orderTblPage.getCurrent());
        System.err.println("每页显示条数，默认 10条:" + "实际" + orderTblPage.getSize() + "条");
        System.err.println("总页数:" + orderTblPage.getPages());
    }

}

