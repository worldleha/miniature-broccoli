package com.miniaturebroccoli.config.knife4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @author scc
 * Knife4j配置
 */
@Configuration
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("疯狂的西兰花")
                        .description("个人博客API文档")
                        .termsOfServiceUrl("http://sccdqz.ltd")
                        .contact(new Contact("scc","sccdqz.ltd","1584725966@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.miniaturebroccoli"))
                .paths(PathSelectors.any())
                .build();
    }
}