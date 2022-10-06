package com.miniaturebroccoli.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author scc
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Admin {
    /**
     * 管理员id
     */
    @TableId(type = IdType.AUTO)
    private Long adminId;
    /**
     * 管理员昵称
     */
    private String adminNickname;
    /**
     * 管理员密码
     */
    private String adminPassword;
    /**
     * 管理员手机号
     */
    private String adminPhone;
    /**
     * 管理员邮箱地址
     */
    private String adminEmail;
}







