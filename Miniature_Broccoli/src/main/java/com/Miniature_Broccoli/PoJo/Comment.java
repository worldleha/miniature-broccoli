package com.Miniature_Broccoli.PoJo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
@Data
public class Comment {
    @TableId
    private Integer commentId;//评论id
    private Integer articleId;//文章id
    private String commentOntent;//评论内容
    private String commentTime;//评论时间
    private String ipAddress;//评论者ip地址
    private String electronicMailbox;//评论者邮箱地址
}