package com.miniaturebroccoli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;


/**
 * @author scc
 */
@SpringBootApplication()
@ServletComponentScan("com.miniaturebroccoli.config")
@MapperScan("com.miniaturebroccoli.mapper")
public class MiniatureBroccoliApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniatureBroccoliApplication.class, args);
    }
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/index.html");
            factory.addErrorPages(error404Page);
        };
    }


}
