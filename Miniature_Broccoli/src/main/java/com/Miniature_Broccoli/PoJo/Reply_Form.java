package com.Miniature_Broccoli.PoJo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
@Data
public class Reply_Form {
    @TableId
    private Integer replyID;//回复id
    private Integer commentID;//评论id
    private String replyOntent;//回复内容
    private String replyTime;//回复时间
    private String replyIpaddress;//回复ip地址
    private String replyElectronicmailbox;//回复邮箱地址
}
