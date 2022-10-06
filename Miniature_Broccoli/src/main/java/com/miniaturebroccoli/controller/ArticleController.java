package com.miniaturebroccoli.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.annotation.MyLog;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.pojo.Audience;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.service.ArticleService;
import com.miniaturebroccoli.utils.DateUtils;
import com.miniaturebroccoli.utils.JwtTokenUtil;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.xss.core.XssCleanIgnore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author scc
 * "@XssCleanIgnore"注解防御xss攻击关闭
 */
@Slf4j
@XssCleanIgnore
@RestController
public class ArticleController {
    public final Audience audience;
    public final ArticleService articleService;

    public ArticleController(ArticleService articleService, Audience audience) {
        this.articleService = articleService;
        this.audience = audience;
    }

    /**
     * 返回文章所有信息
     */
    @MyLog("返回文章所有信息(不包含文章内容)")
    @JwtIgnore
    @GetMapping("/article")
    public Object getArticle() {
        return articleService.getArticle();
    }


    /**
     * 根据id返回所有字段数据
     */
    @MyLog("根据id返回所有字段数据")
    @JwtIgnore
    @GetMapping("/article/{id}")
    public Object getId(@PathVariable Long id) {
        return articleService.getId(id);

    }


    /**
     * 根据id返回文章数据(只含文章内容)
     */
    @MyLog("根据id返回文章数据(只含文章内容)")
    @JwtIgnore
    @GetMapping("/article/part/{id}")
    public Object selectId(@PathVariable Long id) {
        return articleService.selectId(id);
    }

    /**
     * 返回所有文章标签
     */
    @MyLog("返回所有文章标签")
    @JwtIgnore
    @GetMapping("/article/articleLabel")
    public Object getArticleClassify() {
        return articleService.getArticleClassify();

    }

    /**
     * 根据文章标签返回对应文章
     */
    @MyLog("根据文章标签返回对应文章")
    @JwtIgnore
    @GetMapping("/article/articleLabel/{articleLabel}")
    public List<Article> getArticleClassify(@PathVariable String articleLabel) {
        return articleService.getArticleClassify(articleLabel);
    }


    /**
     * 返回文章总条数
     */
    @MyLog("返回文章总条数")
    @JwtIgnore
    @GetMapping("/article/total")
    public Long getArticleTotal() {
        return articleService.getArticleTotal();
    }


    /**
     * 分页文章数据
     */
    @MyLog("分页文章数据")
    @JwtIgnore
    @GetMapping("/article/page/{current}")
    public Page<Article> getArticlePage(@PathVariable int current) {
        return articleService.getArticlePage(current);
    }

    /**
     * 指定标签模糊查询标题(不含文章内容)
     */
    @MyLog("指定标签模糊查询标题(不含文章内容)")
    @JwtIgnore
    @GetMapping("/query/{articleLabel}/{inquire}")
    public Object articleNamedQuery(@PathVariable String articleLabel, @PathVariable String inquire) {
        List<Article> articles = articleService.articleNamedQuery(inquire, articleLabel);
        if (articles.size() > 0) {
            return articles;
        }
        return "数据为空";
    }

    /**
     * 全局搜索 根据便签或标题模糊查询(不含文章内容)
     */
    @MyLog("全局搜索 根据便签或标题模糊查询(不含文章内容)")
    @JwtIgnore
    @GetMapping("/article/query/{inquire}")
    public Object articleQuery(@PathVariable String inquire) {
        List<Article> articles = articleService.articleQuery(inquire);
        if (articles.size() > 0) {
            return articles;
        }
        return "数据为空";
    }


    /**
     * 根据时间段返回数据
     */
    @MyLog("根据时间段返回数据")
    @JwtIgnore
    @GetMapping("betweenTime/{start_time}/{Change_the_time}")
    public Object betweenTime(@PathVariable Long start_time, @PathVariable Long Change_the_time) {
        return articleService.betweenTime(start_time, Change_the_time);

    }


    /**
     * 获取点赞量
     */
    @MyLog("获取点赞量")
    @JwtIgnore
    @GetMapping("likes/{id}")
    public Object getArticleLikes(@PathVariable Long id) {
      return articleService.getArticleLikes(id);
    }

    /**
     * 获取文章详情页访问量
     */
    @MyLog("获取文章详情页访问量")
    @JwtIgnore
    @GetMapping("pageViews/{id}")
    public Object pageViews(@PathVariable Long id) {
       return articleService.getArticlePageViews(id);

    }

    /**
     * 点赞实现
     */
    @MyLog("点赞实现")
    @JwtIgnore
    @GetMapping("like/{id}")
    public Object articleLike(@PathVariable Long id) {
        return articleService.articleLike(id);
    }


    /**
     * 添加文章详情页访问量
     */
    @MyLog("添加文章详情页访问量")
    @JwtIgnore
    @GetMapping("addPageView/{id}")
    public Object addArticlePageView(@PathVariable Long id) {
      return articleService.addArticlePageView(id);

    }


    /**
     * 根据id删除数据
     */
    @MyLog("根据id删除数据")
    @DeleteMapping("article/delete/{id}")
    public String deleteId(@PathVariable Long id) {
       return articleService.deleteId(id);

    }

    /**
     * 添加文章信息
     */
    @MyLog("添加文章信息")
    @PostMapping("/article/add")
    public Object addArticle(@RequestBody Article article, HttpServletRequest request) {
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        String token = authHeader.substring(7);
        log.error(authHeader + "\n" + token);
        /*获取管理员昵称*/
        String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
        log.info("管理员昵称:" + username);
        if (username != null) {
            article.setAdminNickname(username);
            /*文章发布时间**/
            article.setReleaseTime(DateUtils.getSystemTime());
            /*文章最后修改时间**/
            article.setModificationTime(DateUtils.getSystemTime());
            return articleService.addArticle(article);
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "Token为空"));
        }
    }

    /**
     * 更新文章信息
     */
    @MyLog("更新文章信息")
    @PutMapping("/article/update")
    public Object updateArticle(@RequestBody Article article) {
        if (article.getArticleId() == null) {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "Token为空"));
        } else {
            article.setArticleId(article.getArticleId());
            /*最后修改时间*/
            article.setModificationTime(DateUtils.getSystemTime());
           return articleService.updateArticle(article);

        }
    }

    /**
     * 批量删除文章数据
     */
    @JwtIgnore
    @DeleteMapping("/deleteArticlesInBulk")
    public String deleteArticlesInBulk(@RequestBody IdList idList) {
        return articleService.deleteArticlesInBulk(idList);
    }

    /**
     * 获取token中的管理员昵称
     */
    @SuppressWarnings("unused")
    public void getUsername(HttpServletRequest request) {
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        String token = authHeader.substring(7);
        System.err.println(authHeader + "\n" + token);
        /*获取管理员昵称*/
        String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
    }

}