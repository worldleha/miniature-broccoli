package com.Miniature_Broccoli;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
@MapperScan("com.Miniature_Broccoli.Mapper")
public class MiniatureBroccoliApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniatureBroccoliApplication.class, args);
    }

}
