package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miniaturebroccoli.service.CommentService;
import io.swagger.annotations.*;
import net.dreamlu.mica.xss.core.XssCleanIgnore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author scc
 */
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
@XssCleanIgnore //防御xss攻击关闭
@Api(tags = "文章接口")
@RestController
@RequestMapping("/article")
public class AticleController {
    private final ArticleService articleService;
    private final CommentService commentService;
    public AticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    /**
     * 通用判断返回数据是否为空
     */
    private Object isnull(List<Article> list) {
        if (list.size() > 0) {
            return list;
        } else {
            return "数据为空";
        }
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public Object getId(@ApiParam(name = "id", value = "文章id", required = true) @PathVariable Long id) {
        Article article = articleService.getById(id);
        if (article == null) {
            return "没有查询到id=" + id + "的数据";
        }
        return article;
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Object deleteId(@ApiParam(name = "id", value = "文章id", required = true) @PathVariable Long id) {
        boolean b = articleService.removeById(id);
        if (b) {
            return "删除成功";
        } else {
            return "没有查询到id=" + id + "的数据";
        }
    }


    @PostMapping
    @ApiOperation("添加文章数据(ID必不可缺)管理员昵称登录后无需添加")
    public String addArticle(@RequestBody Article article, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        article.setAdminNickname(username);
        boolean b = articleService.save(article);
        if (b) {
            return "添加成功";
        }
        return "添加失败";
    }

    @ApiOperation("修改文章数据(ID必不可缺)")
    @PutMapping
    @ApiParam(name = "Article", value = "文章对象", required = true)
    public String updateArticle(@RequestBody Article article) {
        boolean b = articleService.updateById(article);
        if (b) {
            return "修改成功";
        }
        return "修改失败";
    }

    @ApiOperation("返回文章分类")
    @GetMapping("/classify")
    public Object articleClassify() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct article_classify");
        List<Article> list = articleService.list(queryWrapper);
        return isnull(list);
    }

    @ApiOperation("根据文章分类返回对应文章")
    @GetMapping("/classify/{classify}")
    public Object getClassify(@ApiParam(name = "classify", value = "文章分类", required = true) @PathVariable String classify) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("article_classify", classify);
        List<Article> list = articleService.list(wrapper);
        return isnull(list);
    }


    @ApiOperation("分页文章数据")
    @GetMapping("/{current}/{size}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页显示的条数", required = true, dataType = "Integer")
    })
    public Object getPage(@PathVariable Integer current, @PathVariable Integer size) {
        Page<Article> articlePage = new Page<>(current, size);
        Page<Article> orderTblPage = articleService.page(articlePage, null);
        if (orderTblPage.getRecords().size() > 0) {
            return orderTblPage;
        } else {
            return "数据为空";
        }
    }


    @ApiOperation("指定文章分类模糊查询  查询字段为标题和标签")
    @GetMapping("/named_query/{inquire}/{classify}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inquire", value = "用户输入", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classify", value = "分类", required = true, dataType = "String")
    })
    public Object namedQuery(@PathVariable String inquire, @PathVariable String classify) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("article_title", inquire).or().like("article_label", inquire).eq("article_classify", classify);
        List<Article> list = articleService.list(queryWrapper);
        return isnull(list);
    }

    @ApiOperation("不指定 模糊查询 查询字段为标题和标签")
    @GetMapping("/query/{inquire}")
    public Object query(@ApiParam(name = "inquire", value = "用户输入", required = true) @PathVariable String inquire) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("article_title", inquire).or().like("article_label", inquire);
        List<Article> list = articleService.list(queryWrapper);
        return isnull(list);
    }
}