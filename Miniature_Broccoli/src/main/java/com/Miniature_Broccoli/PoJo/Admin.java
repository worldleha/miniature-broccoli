package com.Miniature_Broccoli.PoJo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
@Data
@ApiModel(description = "管理员实体类")
public class Admin {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "管理员id")
    private Long adminId;
    @ApiModelProperty(value = "管理员昵称")
    private String adminNickname;
    @ApiModelProperty(value = "管理员密码")
    private String adminPassword;
    @ApiModelProperty(value = "管理员手机号")
    private String adminPhone;
    @ApiModelProperty(value = "管理员邮箱")
    private String adminEmail;
}