package com.miniaturebroccoli.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miniaturebroccoli.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miniaturebroccoli.pojo.IdList;

import java.util.List;

/**
 * @author scc
 */
public interface ArticleService extends IService<Article> {
    /**
     * 根据id查询
     */
    List<Article> getId(Long id);

    /**
     * 返回所有文章信息
     */
    List<Article> getArticle();

    /**
     * 根据id返回文章数据(不含文章内容)
     */
    List<Article> selectId(Long id);

    /**
     * 根据id删除数据
     */
    String deleteId(Long id);


    /**
     * 添加文章信息
     */
    String addArticle(Article article);

    /**
     * 修改文章信息
     */
    String updateArticle(Article article);

    /**
     * 返回所有文章标签
     */
    Object getArticleClassify();

    /**
     * 根据文章标签返回对应文章(不含文章内容)
     */
    List<Article> getArticleClassify(String articleLabel);

    /**
     * 分页文章数据
     */
    Page<Article> getArticlePage(int current);

    /**
     * 指定标签模糊查询标签(不含文章内容)
     */
    List<Article> articleNamedQuery(String inquire, String articleLabel);

    /**
     * 全局搜索 根据便签或标题模糊查询(不含文章内容)
     */
    List<Article> articleQuery(String inquire);

    /**
     * 文章总数
     */
    Long getArticleTotal();


    /**
     * 根据时间段返回数据
     */

    List<Article> betweenTime(Long start_time, Long Change_the_time);

    /**
     * 点赞
     */
    Object articleLike(Long id);

    /**
     * 点赞量
     */
    List<Article> getArticleLikes(Long id);

    /**
     * 获取文章详情页访问量
     */
    List<Article> getArticlePageViews(Long id);

    /**
     * 添加文章详情页访问量
     */
    Object addArticlePageView(Long id);


    /**
     * 批量删除文章数据
     */

    String deleteArticlesInBulk(IdList idList);
}
