package com.Miniature_Broccoli.Controller;

import com.Miniature_Broccoli.PoJo.Article;
import com.Miniature_Broccoli.service.articleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "文章接口")
@RestController
@RequestMapping("/article")
public class articleController {
    private final articleService articleService;

    public articleController(articleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation("返回所有文章信息")
    @GetMapping()
    public Object save() {
        List<Article> list = articleService.list(null);
        if (list.size() > 0) {
            return list;
        } else {
            return "数据为空";
        }

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
        boolean b = articleService.removeById(id);
        if (b) {
            return "删除成功";
        } else {
            return "没有查询到id=" + id + "的数据";
        }
    }

    @ApiOperation("添加文章数据")
    @ApiParam(name = "Article", value = "文章对象", required = true)
    @PostMapping
    public String add(@RequestBody Article article) {
        boolean b = articleService.save(article);
        if (b) {
            return "添加成功";
        }
        return "添加失败";
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
        } else
            return "数据为空";
    }

}