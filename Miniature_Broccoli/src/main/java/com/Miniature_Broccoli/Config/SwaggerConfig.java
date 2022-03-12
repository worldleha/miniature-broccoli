package com.Miniature_Broccoli.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@EnableOpenApi //开启Swagger3
public class SwaggerConfig {
    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());//配置Swagger信息
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "疯狂的西兰花",
                "个人博客 API文档",
                "1.0",//版本信息
                "",//团队信息的url
                new Contact("scc", "http://sccdqz.ltd", "1584725966@qq.com"),//作者信息
                /*Contact(String name, String url, String email)*/
                "--",
                "--",
                new ArrayList<>());
    }
}
