package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.pojo.Audience;
import com.miniaturebroccoli.service.ArticleService;
import com.miniaturebroccoli.utils.JwtTokenUtil;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.SensitiveWordUtil;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.xss.core.XssCleanIgnore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author scc
 * "@XssCleanIgnore"注解防御xss攻击关闭
 */
@Slf4j
@XssCleanIgnore
@RestController
public class AticleController {
    private final Audience audience;
    private final ArticleService articleService;

    public AticleController(ArticleService articleService, Audience audience) {
        this.articleService = articleService;
        this.audience = audience;
    }

    /**
     * 返回文章所有信息
     */
    @JwtIgnore
    @GetMapping("/article")
    public Object getAll() {
        return articleService.getAll();
    }

    /**
     * 根据id返回所有字段数据
     */
    @JwtIgnore
    @GetMapping("/article/{id}")
    public Object getId(@PathVariable Long id) {
        return articleService.getId(id);
    }

    /**
     * 根据id返回文章数据(只含文章内容)
     */
    @JwtIgnore
    @GetMapping("/article/part/{id}")
    public Object selectId(@PathVariable Long id) {
        return articleService.selectId(id);
    }

    /**
     * 返回所有文章标签
     */
    @JwtIgnore
    @GetMapping("/article/articleLabel")
    public Object articleClassify() {
        return articleService.articleClassify();
    }

    /**
     * 根据文章标签返回对应文章
     */
    @JwtIgnore
    @GetMapping("/article/articleLabel/{articleLabel}")
    public Object getClassify(@PathVariable String articleLabel) {
        Object cofing = SensitiveWordUtil.cofing(articleLabel);
        if (cofing != null) {
            return ResultData.customize(500, "参数中含有敏感词", cofing);
        } else {
            return articleService.getClassify(articleLabel);
        }

    }

    /**
     * 分页文章数据
     */
    @JwtIgnore
    @GetMapping("/article/page/{current}")
    public Object getPage(@PathVariable int current) {
        return articleService.getPage(current);
    }

    /**
     * 指定标签模糊查询标题(不含文章内容)
     */

    @JwtIgnore
    @GetMapping("/query/{articleLabel}/{inquire}")
    public Object namedQuery(@PathVariable String articleLabel, @PathVariable String inquire) {
        Object cofing = SensitiveWordUtil.cofing(articleLabel + inquire);
        if (cofing != null) {
            return ResultData.customize(500, "参数中含有敏感词", cofing);
        } else {
            return articleService.namedQuery(inquire, articleLabel);
        }
    }

    /**
     * 全局搜索 根据便签或标题模糊查询(不含文章内容)
     */
    @JwtIgnore
    @GetMapping("/article/query/{inquire}")
    public Object query(@PathVariable String inquire) {
        Object cofing = SensitiveWordUtil.cofing(inquire);
        if (cofing != null) {
            return ResultData.customize(500, "参数中含有敏感词", cofing);
        } else {
            return articleService.query(inquire);
        }
    }

    /**
     * 返回文章总条数
     */

    @JwtIgnore
    @GetMapping("/article/total")
    private Object total() {
        return articleService.total();
    }

    /**
     * 根据时间段返回数据
     */
    @JwtIgnore
    @GetMapping("betweenTime/{start_time}/{Change_the_time}")
    private Object betweenTime(@PathVariable Long start_time, @PathVariable Long Change_the_time) {
        return articleService.betweenTime(start_time, Change_the_time);
    }

    /**
     * 点赞实现
     */

    @JwtIgnore
    @GetMapping("like/{id}")
    private Object like(@PathVariable Long id) {
        return articleService.like(id);
    }

    /**
     * 获取点赞量
     */
    @JwtIgnore
    @GetMapping("likes/{id}")
    private Object likes(@PathVariable Long id) {
        return articleService.likes(id);
    }

    /**
     * 添加文章详情页访问量
     */
    @JwtIgnore
    @GetMapping("addPageView/{id}")
    private Object pageView(@PathVariable Long id) {
        return articleService.pageView(id);
    }

    /**
     * 获取文章详情页访问量
     */
    @JwtIgnore
    @GetMapping("pageViews/{id}")
    private Object pageViews(@PathVariable Long id) {
        return articleService.pageViews(id);
    }

    /**
     * 根据id删除数据
     */
    @DeleteMapping("article/delete/{id}")
    public Object deleteId(@PathVariable Long id) {
        return articleService.deleteId(id);
    }

    /**
     * 添加文章信息
     */
    @PostMapping("/article/add")
    public Object addArticle(@RequestBody Article article, HttpServletRequest request) {
        Object cofing = SensitiveWordUtil.cofing(String.valueOf(article));
        if (cofing != null) {
            return ResultData.customize(500, "参数中含有敏感词", cofing);
        } else {
            String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
            String token = authHeader.substring(7);
            System.err.println(authHeader + "\n" + token);
            /*获取管理员昵称*/
            String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
            log.info("管理员昵称:" + username);
            if (username != null) {
                article.setAdminNickname(username);
                /*文章发布时间**/
                article.setReleaseTime(System.currentTimeMillis());
                /*文章最后修改时间**/
                article.setModificationTime(System.currentTimeMillis());
                return articleService.addArticle(article);
            } else {
                return ResultData.fail(500, "Token为空");
            }
        }

    }

    /**
     * 更新文章信息
     */
    @PutMapping("/article/update")
    public Object updateArticle(@RequestBody Article article) {
        log.info("用户id为" + article.getArticleId());
        Object cofing = SensitiveWordUtil.cofing(String.valueOf(article));
        if (cofing != null) {
            return ResultData.customize(500, "参数中含有敏感词", cofing);
        } else {
            if (article.getArticleId() != null) {
                article.setArticleId(article.getArticleId());
                article.setModificationTime(System.currentTimeMillis());/*最后修改时间*/
                return articleService.updateArticle(article);
            } else {
                return ResultData.fail(500, "Token为空");
            }
        }
    }

    /**
     * 获取token中的管理员昵称
     */
    public void a(HttpServletRequest request) {
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        String token = authHeader.substring(7);
        System.err.println(authHeader + "\n" + token);
        /*获取管理员昵称*/
        String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
    }

}