package com.Miniature_Broccoli.PoJo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
@Data
@ApiModel(description = "评论实体类")
public class Comment {
    @TableId
    @ApiModelProperty(value = "评论id")
    private Long commentId;
    @ApiModelProperty(value = "文章id")
    private Long articleId;
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    @ApiModelProperty(value = "评论时间")
    private String commentTime;
    @ApiModelProperty(value = "评论者ip地址")
    private String ipAddress;
    @ApiModelProperty(value = "评论者邮箱地址")
    private String electronicMailbox;
}