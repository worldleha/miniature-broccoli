package com.miniaturebroccoli.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor /*无参构造*/
@AllArgsConstructor /*有参构造*/
@Data
@TableName(value = "syslog")
public class SysLog {
    @TableId(type = IdType.AUTO)
    private Long logId;
    private String logUsername; //用户名

    private String logOperation; //操作

    private String logMethod; //方法名

    private String logParams; //参数

    private String logIp; //ip地址

    private String logDate; //操作时间
    private String logRequestMode;
}
