package com.miniaturebroccoli.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor  /* 无参构造*/
@AllArgsConstructor /* 有参构造**/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {
    /**
     * 文章id
     **/
    @TableId(type = IdType.AUTO)
    private Long articleId;
    /**
     * 文章标题
     **/
    private String articleTitle;
    /**
     * 管理员
     **/
    private String adminNickname;
    /**
     * 开始编写时间
     **/
    private String releaseTime;
    /**
     * 最后修改时间
     **/
    private String modificationTime;
    /**
     * 文章内容
     **/
    private String articleContent;
    /**
     * 文章标签
     **/
    private String articleLabel;
    /**
     * 文章简介
     **/
    private String articleIntro;
    /**
     * 文章图片链接
     **/
    private String articlePictureLink;
    /**
     * 点赞数
     **/
    private long articleLikes;
    /**
     * 文章详情页访问量
     */
    private long pageViews;
}
