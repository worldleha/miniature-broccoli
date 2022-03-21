package com.Miniature_Broccoli.Controller;

import com.Miniature_Broccoli.PoJo.Article;
import com.Miniature_Broccoli.service.articleService;
<<<<<<< Updated upstream
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
=======
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import net.dreamlu.mica.xss.core.XssCleanIgnore;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.*;

import java.util.List;

<<<<<<< Updated upstream
=======
@XssCleanIgnore //防御xss攻击关闭
>>>>>>> Stashed changes
@Api(tags = "文章接口")
@RestController
@RequestMapping("/article")
public class articleController {
    private final articleService articleService;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    public articleController(articleService articleService) {
        this.articleService = articleService;
    }

<<<<<<< Updated upstream
    @ApiOperation("返回所有文章信息")
    @GetMapping()
    public Object save() {
        List<Article> list = articleService.list(null);
=======
    /**
     * 通用判断返回数据是否为空
     */
    private Object isnull(List<Article> list) {
>>>>>>> Stashed changes
        if (list.size() > 0) {
            return list;
        } else {
            return "数据为空";
        }
<<<<<<< Updated upstream

    }

    @ApiOperation("根据id查询")
    @ApiParam(name = "id", value = "文章id", required = true)
    @GetMapping("/{id}")
    public Object getId(@PathVariable Integer id) {
        Article byId = articleService.getById(id);
        if (byId == null) {
            return "没有查询到id=" + id + "的数据";
        }
        return byId;
    }

    @ApiOperation("根据id删除")
    @ApiParam(name = "id", value = "文章id", required = true)
    @DeleteMapping("/{id}")
    public Object deleteId(@PathVariable Integer id) {
=======
    }

//    @ApiOperation("返回所有文章数据")
//    @GetMapping()
//    public Object getArticle() {
//        List<Article> list = articleService.list(null);
//        return isnull(list);
//    }

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
>>>>>>> Stashed changes
        boolean b = articleService.removeById(id);
        if (b) {
            return "删除成功";
        } else {
            return "没有查询到id=" + id + "的数据";
        }
    }

<<<<<<< Updated upstream
    @ApiOperation("添加文章数据")
    @ApiParam(name = "Article", value = "文章对象", required = true)
    @PostMapping
    public String add(@RequestBody Article article) {
=======

    @PostMapping
    @ApiOperation("添加文章数据(ID必不可缺)")
    public String addArticle(@RequestBody Article article) {
>>>>>>> Stashed changes
        boolean b = articleService.save(article);
        if (b) {
            return "添加成功";
        }
        return "添加失败";
    }

<<<<<<< Updated upstream
=======
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


>>>>>>> Stashed changes
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
        } else
            return "数据为空";
    }

<<<<<<< Updated upstream
=======

    @ApiOperation("指定文章分类模糊查询  查询字段为标题和标签")
    @GetMapping("/named_query/{inquire}/{classify}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inquire", value = "用户输入", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classify", value = "分类", required = true, dataType = "String")
    })
    public Object named_query(@PathVariable String inquire, @PathVariable String classify) {
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
>>>>>>> Stashed changes
}