package com.miniaturebroccoli.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 无参构造
 */
@NoArgsConstructor
/**
 * 有参构造
 */
@AllArgsConstructor
@Data
@ApiModel(description = "文章实体类")
public class Article {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "文章id")
    private Long articleId;
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    @ApiModelProperty(value = "管理员昵称")
    private String adminNickname;
    @ApiModelProperty(value = "文章发布时间")
    private String releaseTime;
    @ApiModelProperty(value = "最后修改时间")
    private String modificationTime;

    @ApiModelProperty(value = "页面访问量")
    private String pageViews;
    @ApiModelProperty(value = "文章内容")
    private String articleContent;
    @ApiModelProperty(value = "文章分类")
    private String articleClassify;
    @ApiModelProperty(value = "文章标签")
    private String articleLabel;
}
