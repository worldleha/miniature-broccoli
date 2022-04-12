package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.constant.constant;
import com.miniaturebroccoli.mapper.ArticleMapper;
import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.service.ArticleService;
import com.miniaturebroccoli.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author scc
 */
@SuppressWarnings("unchecked")
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 通用判断返回数据是否为空
     */
    private Object isnull(List<Article> list) {
        if (list.size() > 0) {
            return list;
        } else {
            return ResultData.fail(200, "数据为空");
        }
    }

    /**
     * 根据id查询(返回所有字段数据)
     */
    public Object getId(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            return "没有查询到id=" + id + "的数据";
        }
        return article;
    }

    /**
     * 返回文章对象中除了文章内容以为的所有字段的数据
     */
    @Override
    public Object getAll() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("article_id",
                "article_title",
                "admin_nickname",
                "release_time",
                "modification_time",
                "article_label",
                "article_intro",
                "article_picture_link",
                "article_likes");
        List<Article> articles = articleMapper.selectList(wrapper);
        return isnull(articles);
    }

    /**
     * 根据id返回文章数据(只含文章内容)
     */
    public Object selectId(Long id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("article_content").eq("article_id", id);
        List<Article> list = articleMapper.selectList(queryWrapper);
        return isnull(list);
    }

    /**
     * 返回所有文章标签
     */
    @Override
    public Object articleClassify() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct article_label");
        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (articles.size() > 0) {
            List<String> collect = articles.stream().map(Article::getArticleLabel).collect(Collectors.toList());
            String s = collect.toString();
            log.info("默认数据" + s);
            String substring = s.substring(1, s.length() - 1);
            String[] split = substring.split(",");//以逗号分割
            Set<String> hashSet = new HashSet<>();
            for (String string2 : split) {
                hashSet.add(string2.strip().toLowerCase());
                log.info("以逗号分割后的数据" + string2);
            }
            log.info("添加到Set集合去重的数据" + hashSet);
            return hashSet;
        }
        return "数据为空";
    }

    /**
     * 根据文章标签返回对应文章
     */
    @Override
    public Object getClassify(String articleLabel) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("article_id",
                        "article_title",
                        "admin_nickname",
                        "release_time",
                        "modification_time",
                        "article_intro",
                        "article_picture_link",
                        "article_likes")
                .eq("article_label", articleLabel);
        return articleMapper.selectList(wrapper);
    }


    /**
     * 分页文章数据
     */
    @Override
    public Object getPage(int current) {
        /*获取默认每页显示条数**/
        int size = constant.page_size;
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.select("article_id",
                "article_title",
                "admin_nickname",
                "release_time",
                "modification_time",
                "article_label",
                "article_intro",
                "article_picture_link",
                "article_likes");
        Page<Article> articlePage = new Page<>(current, size);
        Page<Article> orderTblPage = articleMapper.selectPage(articlePage, wrapper);
        if (orderTblPage.getRecords().size() > 0) {
            return orderTblPage;
        } else {
            return "数据为空";
        }
    }

    /**
     * 指定标签模糊查询标题(不含文章内容)
     */
    @Override
    public Object namedQuery(String inquire, String articleLabel) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("article_id",
                        "article_title",
                        "admin_nickname",
                        "release_time",
                        "modification_time",
                        "article_label",
                        "article_intro",
                        "article_picture_link",
                        "article_likes")
                .like("article_title", inquire).eq("article_label", articleLabel);
        return articleMapper.selectList(queryWrapper);
    }

    /**
     * 全局搜索 根据便签或标题模糊查询(不含文章内容)
     */
    @Override
    public Object query(String inquire) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("article_id",
                        "article_title",
                        "admin_nickname",
                        "release_time",
                        "modification_time",
                        "article_label",
                        "article_intro",
                        "article_picture_link",
                        "article_likes")
                .like("article_title", inquire).or().like("article_label", inquire);
        return articleMapper.selectList(queryWrapper);
    }

    /**
     * 返回文章总条数
     */
    @Override
    public Object total() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        return articleMapper.selectCount(queryWrapper);
    }

    /**
     * 根据时间段返回数据
     */
    @Override
    public Object betweenTime(Long start_time, Long Change_the_time) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("article_id",
                        "article_title",
                        "admin_nickname",
                        "release_time",
                        "modification_time",
                        "article_label",
                        "article_intro",
                        "article_picture_link",
                        "article_likes")
                .between("modification_time", start_time, Change_the_time);
        return articleMapper.selectList(queryWrapper);
    }

    /**
     * 点赞功能访问一次点赞一次
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public Object like(Long id) {
        if (likes(id).equals("没有id为" + id + "的文章")) {
        } else {
            int likes = (int) likes(id);/*根据id调用likes方法获取数据库中已经有的点赞数*/
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("article_id", id);
            updateWrapper.set("article_likes", likes + 1);
            int update = baseMapper.update(null, updateWrapper);
            if (update > 0) {
                return "点赞次数+1";
            }
        }
        return "点赞失败没有id为" + id + "的文章";
    }

    /**
     * 获取点赞量
     */
    @Override
    public Object likes(Long id) {
        QueryWrapper qw = new QueryWrapper<>();
        qw.select("page_views").eq("article_id", id);
        List<Article> likes = articleMapper.selectList(qw);
        log.info("11111111111" + likes.toString());
        if (likes.size() > 0) {
            log.info(String.valueOf(likes.get(0).getArticleLikes()));
            return likes.get(0).getArticleLikes();

        }
        return "没有id为" + id + "的文章";
    }

    /**
     * 添加文章详情页访问
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public Object pageView(Long id) {
        if (likes(id).equals("没有id为" + id + "的文章")) {
        } else {
            int likes = (int) pageViews(id);/*根据id调用likes方法获取数据库中已经有的点赞数*/
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("article_id", id);
            updateWrapper.set("page_views", likes + 1);
            int update = baseMapper.update(null, updateWrapper);
            if (update > 0) {
                return "文章详情页访问量+1";
            }
        }
        return "没有id为" + id + "的文章";
    }

    /**
     * 获取文章详情页访问量
     */
    @Override
    public Object pageViews(Long id) {
        QueryWrapper qw = new QueryWrapper<>();
        qw.select("page_views").eq("article_id", id);
        List<Article> likes = articleMapper.selectList(qw);
        if (likes.size() > 0) {
            log.info(String.valueOf(likes.get(0).getArticleLikes()));
            return likes.get(0).getPageViews();
        }
        return "没有id为" + id + "的文章";
    }

    /**
     * 根据id删除数据
     */
    @Override
    public Object deleteId(Long id) {
        int i = articleMapper.deleteById(id);
        if (i > 0) {
            return ResultData.fail(200, "删除成功");
        } else {
            return ResultData.customize(200, "请求成功", "没有id=" + id + "的数据");
        }
    }

    /**
     * 添加文章信息
     */
    @Override
    public Object addArticle(Article article) {
        int i = articleMapper.insert(article);
        if (i > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    /**
     * 修改文章信息只能修改文章表中的* 标题* 最后修改时间* 文章内容* 文章标签* 文章简介*** 文章图片链接**
     * <p>
     * 不能修改的字段为* 文章id* 管理员昵称* 文章发布时间* 文章点赞量* 文章详情页访问量
     */

    @Override
    public Object updateArticle(Article article) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("article_id", article.getArticleId());
        updateWrapper.set("article_title", article.getArticleTitle());
        updateWrapper.set("modification_time", article.getModificationTime());
        updateWrapper.set("article_content", article.getArticleContent());
        updateWrapper.set("article_label", article.getArticleLabel());
        updateWrapper.set("article_intro", article.getArticleIntro());
        updateWrapper.set("article_picture_link", article.getArticlePictureLink());
        int update = baseMapper.update(null, updateWrapper);
        if (update > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

}