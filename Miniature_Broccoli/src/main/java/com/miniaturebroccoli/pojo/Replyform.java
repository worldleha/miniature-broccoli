package com.miniaturebroccoli.pojo;

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
@ApiModel(description = "回复实体类")
public class Replyform {
    @TableId
    @ApiModelProperty(value = "回复id")
    private Long replyId;
    @ApiModelProperty(value = "评论id")
    private Long commentId;
    @ApiModelProperty(value = "回复内容")
    private String replyContent;
    @ApiModelProperty(value = "回复时间")
    private String replyTime;
    @ApiModelProperty(value = "回复ip地址")
    private String replyIpaddress;
    @ApiModelProperty(value = "回复邮箱地址")
    private String replyElectronicmailbox;
}
