package com.miniaturebroccoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miniaturebroccoli.constant.Constant;
import com.miniaturebroccoli.interceptor.exception.CustomException;
import com.miniaturebroccoli.mapper.ArticleMapper;
import com.miniaturebroccoli.pojo.Article;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.service.ArticleService;
import com.miniaturebroccoli.utils.ResultData;
import com.miniaturebroccoli.utils.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author scc
 */
@Slf4j
@Service

public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 根据id查询(返回所有字段数据)
     */
    public List<Article> getId(Long id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", id);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (articles.size() > 0) {
            return articles;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "没有id为" + id + "的数据"));
        }
    }

    /**
     * 返回文章对象中除了文章内容以为的所有字段的数据
     */
    @Override
    public List<Article> getArticle() {
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
        if (articles.size() > 0) {
            return articles;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        }
    }

    /**
     * 根据id返回文章数据(只含文章内容)
     */
    @Override
    public List<Article> selectId(Long id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("article_content").eq("article_id", id);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (articles.size() > 0) {
            return articles;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "没有id为" + id + "的数据"));
        }
    }

    /**
     * 返回所有文章标签
     */
    @Override
    public Object getArticleClassify() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct article_label");
        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (articles.size() > 0) {
            List<String> collect = articles.stream().map(Article::getArticleLabel).collect(Collectors.toList());
            String s = collect.toString();
            log.info("默认数据" + s);
            String substring = s.substring(1, s.length() - 1);
            /*以逗号分割*/
            String[] split = substring.split(",");
            Set<String> hashSet = new HashSet<>();
            for (String string2 : split) {
                hashSet.add(string2.strip().toLowerCase());
                log.info("以逗号分割后的数据" + string2);
            }
            log.info("添加到Set集合去重的数据" + hashSet);
            return hashSet;
        }
        else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        }

    }

    /**
     * 根据文章标签返回对应文章
     */
    @Override
    public List<Article> getArticleClassify(String articleLabel) {
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
        List<Article> articles = articleMapper.selectList(wrapper);
        if (articles.size() > 0) {
            return articles;
        }
        else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        }

    }

    /**
     * 返回文章总条数
     */
    @Override
    public Long getArticleTotal() {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.select();
        return articleMapper.selectCount(qw);
    }

    /**
     * 分页文章数据
     */
    @Override
    public Page<Article> getArticlePage(int current) {
        /*获取默认每页显示条数*/
        int size = Constant.page_size;
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
        return articleMapper.selectPage(articlePage, wrapper);
    }

    /**
     * 指定标签模糊查询标题(不含文章内容)
     */
    @Override
    public List<Article> articleNamedQuery(String inquire, String articleLabel) {
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

        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (articles.size() > 0) {
            return articles;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        }
    }

    /**
     * 全局搜索 根据便签或标题模糊查询(不含文章内容)
     */
    @Override
    public List<Article> articleQuery(String inquire) {
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
        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (articles.size() > 0) {
            return articles;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        }
    }


    /**
     * 根据时间段返回数据
     */
    @Override
    public List<Article> betweenTime(Long start_time, Long Change_the_time) {
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
        List<Article> articles = articleMapper.selectList(queryWrapper);
        if (articles.size() > 0) {
            return articles;
        }
        else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "数据为空"));
        }


    }

    /**
     * 点赞功能访问一次点赞一次
     */
    @Override
    public Object articleLike(Long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        List<Article> articleLikes = this.getArticleLikes(id);
        if (articleLikes.isEmpty()) {
            return null;
        } else {
            Long likes = articleLikes.get(0).getArticleLikes();/*根据id调用likes方法获取数据库中已经有的点赞数*/
            updateWrapper.eq("article_id", id);
            updateWrapper.set("article_likes", likes + 1);
            try {
                baseMapper.update(null, updateWrapper);
            } catch (CustomException e) {
                throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "点赞失败"));
            }
            return "点赞成功";
        }
    }

    /**
     * 获取点赞量
     */
    @Override
    public List<Article> getArticleLikes(Long id) {
        QueryWrapper qw = new QueryWrapper<>();
        qw.select("article_likes").eq("article_id", id);
        List list = articleMapper.selectList(qw);
        if (list.size() > 0) {
            return list;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "没有id为" + id + "的数据"));
        }

    }

    /**
     * 添加文章详情页访问
     */
    @Override
    public Object addArticlePageView(Long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        List<Article> articlePageViews = this.getArticlePageViews(id);
        if (articlePageViews.isEmpty()) {
            return null;
        } else {
            Long likes = articlePageViews.get(0).getPageViews();/*根据id调用likes方法获取数据库中已经有的点赞数*/
            updateWrapper.eq("article_id", id);
            updateWrapper.set("page_views", likes + 1);
            try {
                baseMapper.update(null, updateWrapper);
            } catch (CustomException e) {
                throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "点赞失败"));
            }
            return "增加页面访问量成功";
        }
    }


    /**
     * 获取文章详情页访问量
     */
    @Override
    public List<Article> getArticlePageViews(Long id) {
        QueryWrapper qw = new QueryWrapper<>();
        qw.select("page_views").eq("article_id", id);
        List list = articleMapper.selectList(qw);
        if (list.size() > 0) {
            return list;
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "没有id为" + id + "的数据"));
        }
    }

    /**
     * 根据id删除数据
     */
    @Override
    public String deleteId(Long id) {
        int i = articleMapper.deleteById(id);
        if (i == 1) {
            return "删除成功";
        }
        else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "没有id为" + id + "的文章数据"));
        }

    }

    /**
     * 添加文章信息
     */
    @Override
    public String addArticle(Article article) {
        int insert = articleMapper.insert(article);
        if (insert == 1) {
            return "添加成功";
        } else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "添加失败"));
        }
    }

    /**
     * 修改文章信息只能修改文章表中的* 标题* 最后修改时间* 文章内容* 文章标签* 文章简介*** 文章图片链接**
     * <p>
     * 不能修改的字段为* 文章id* 管理员昵称* 文章发布时间* 文章点赞量* 文章详情页访问量
     */

    @Override
    public String updateArticle(Article article) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("article_id", article.getArticleId());
        updateWrapper.set("article_title", article.getArticleTitle());
        updateWrapper.set("modification_time", article.getModificationTime());
        updateWrapper.set("article_content", article.getArticleContent());
        updateWrapper.set("article_label", article.getArticleLabel());
        updateWrapper.set("article_intro", article.getArticleIntro());
        updateWrapper.set("article_picture_link", article.getArticlePictureLink());
        int update = baseMapper.update(null, updateWrapper);
        if (update == 1) {
            return "修改成功";
        }
        else {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "修改失败"));
        }

    }

    /**
     * 批量删除文章数据
     */
    @Override
    public String deleteArticlesInBulk(IdList idList) {
        List<Long> ids = idList.getIdList();
        if (!ids.isEmpty() && ids.size() == 0) {
            throw new CustomException(ResultData.customize1(ReturnCode.RC500.getCode(), "没有数据"));
        } else {
            int delete = articleMapper.deleteBatchIds(ids);
            log.error(String.valueOf(delete));
            return "删除成功";
        }

    }
}