package com.miniaturebroccoli.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 从配置文件中读取数据
 */
@ConfigurationProperties(prefix = "audience")
@Data
@Component
public class Audience {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}