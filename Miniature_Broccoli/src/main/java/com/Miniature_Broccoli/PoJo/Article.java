package com.Miniature_Broccoli.PoJo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
@Data
@ApiModel(description = "文章实体类")
public class Article {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "文章id")
    private Long articleId;
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
<<<<<<< Updated upstream
    @ApiModelProperty(value = "文章发布时间")
    private String articleTime;
    @ApiModelProperty(value = "文章内容")
    private String articleContent;
    @ApiModelProperty(value = "文章缩略图")
    private String articleThumbnail;
=======
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
>>>>>>> Stashed changes
}
