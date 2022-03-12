package com.Miniature_Broccoli.Controller;
import com.Miniature_Broccoli.PoJo.Article;
import com.Miniature_Broccoli.service.articleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("文章接口")
@RestController
@RequestMapping("/article")
public class articleController {
    private final articleService articleService;

    public articleController(articleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation("返回所有文章信息")
    @GetMapping()
    public List<Article> save() {
        return articleService.list(null);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public Object getId(@PathVariable Integer id) {
        Article byId = articleService.getById(id);
        if (byId == null) {
            return "没有查询到id=" + id + "的数据";
        }
        return byId;
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Object deleteId(@PathVariable Integer id) {
        boolean b = articleService.removeById(id);
        if (!b) {
            return "没有查询到id=" + id + "的数据";
        } else {
            return "删除成功";
        }
    }

    @ApiOperation("添加文章数据")
    @PostMapping
    public String add(@RequestBody Article article) {
        boolean b = articleService.save(article);
        if (b == true) {
            return "添加成功";
        }
        return "添加失败";
    }

    @ApiOperation("文章分页")
    @GetMapping("/{current}/{size}")
    public Object getPage(@PathVariable Integer current, @PathVariable Integer size) {
        Page<Article> articlePage = new Page<>(current, size);
        Page<Article> orderTblPage = articleService.page(articlePage, null);
        if (orderTblPage.getRecords().size() > 0) {
            return orderTblPage;
        } else
            return "数据为空";
    }

    /**
     * 测试异常请求
     *
     */

    @GetMapping("/c")
    public int c() {
        return 10 / 0;
    }

    @GetMapping("/d")
    public Article d() {
        return articleService.getById(15);
    }
}