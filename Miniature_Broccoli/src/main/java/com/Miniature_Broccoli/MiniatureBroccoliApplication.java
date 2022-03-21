package com.Miniature_Broccoli;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< Updated upstream


@SpringBootApplication()
=======
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication()
@ServletComponentScan("com.Miniature_Broccoli.Config")
>>>>>>> Stashed changes
@MapperScan("com.Miniature_Broccoli.Mapper")
public class MiniatureBroccoliApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniatureBroccoliApplication.class, args);
    }

}
