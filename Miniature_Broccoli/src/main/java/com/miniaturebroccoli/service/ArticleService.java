package com.miniaturebroccoli.service;

import com.miniaturebroccoli.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author scc
 */
public interface ArticleService extends IService<Article> {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Object getId(Long id);

    /**
     * 返回所有文章信息
     *
     * @return
     */
    Object getAll();

    /**
     * 根据id返回文章数据(不含文章内容)
     *
     * @param id
     * @return
     */
    Object selectId(Long id);

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    Object deleteId(Long id);


    /**
     * 添加文章信息
     *
     * @param article
     * @return
     */
    Object addArticle(Article article);

    /**
     * 修改文章信息
     *
     * @param article
     * @return
     */
    Object updateArticle(Article article);

    /**
     * 返回所有文章标签
     *
     * @return
     */
    Object articleClassify();

    /**
     * 根据文章标签返回对应文章(不含文章内容)
     *
     * @param articleLabel
     * @return
     */
    Object getClassify(String articleLabel);

    /**
     * 分页文章数据
     *
     * @param current
     * @return
     */
    Object getPage(int current);

    /**
     * 指定标签模糊查询标签(不含文章内容)
     *
     * @param inquire
     * @param articleLabel
     * @return
     */
    Object namedQuery(String inquire, String articleLabel);

    /**
     * 全局搜索 根据便签或标题模糊查询(不含文章内容)
     *
     * @param inquire
     * @return
     */
    Object query(String inquire);

    /**
     * 文章总数
     */
    Object total();


    /**
     * 根据时间段返回数据
     */

    Object betweenTime(Long start_time, Long Change_the_time);

    /**
     * 点赞
     */
    Object like(Long id);

    /**
     * 点赞量
     */
    Object likes(Long id);

    /**
     * 获取文章详情页访问量
     */
    Object pageViews(Long id);

    /**
     * 添加文章详情页访问量
     */
    Object pageView(Long id);
}
