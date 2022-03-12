package com.Miniature_Broccoli.PoJo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
@Data
public class Article {
    @TableId
    private Integer articleId;//文章id
    private String articleTitle; //标题
    private String articleTime;//发布时间
    private String articleContent;//文章内容
    private String articleThumbnail;//缩略图
}
