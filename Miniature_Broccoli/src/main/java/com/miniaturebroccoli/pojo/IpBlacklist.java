package com.miniaturebroccoli.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//无参构造
@NoArgsConstructor
//有参构造
@AllArgsConstructor
@Data
public class IpBlacklist {
    @TableId(type = IdType.AUTO)
    private Long ipId; //ipId
    private String ipAddress; //ip地址
}
