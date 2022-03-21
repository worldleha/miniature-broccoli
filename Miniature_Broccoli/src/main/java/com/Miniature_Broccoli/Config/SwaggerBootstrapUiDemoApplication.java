package com.Miniature_Broccoli.Config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication
<<<<<<< Updated upstream
=======
/**
 * 处理 springboot 404异常后  Knife4japi文档不显示问题 且 处理谷歌  edge浏览器  对 favicon.ico资源 误报
 */
>>>>>>> Stashed changes
public class SwaggerBootstrapUiDemoApplication  implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}