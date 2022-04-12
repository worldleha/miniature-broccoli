package com.miniaturebroccoli.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author scc
 */

//无参构造
@NoArgsConstructor
//有参构造
@AllArgsConstructor
@Data
@TableName(value = "dp")
public class Dataprocessing {
    @TableId(type = IdType.AUTO)
    private Long identificationId;
    private String sensitiveWords;
}