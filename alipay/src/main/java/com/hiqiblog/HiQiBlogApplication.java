package com.hiqiblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ${ww}=
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan("com.hiqiblog.mapper")
@ComponentScan(basePackages = "com.hiqiblog.service")
@ServletComponentScan
public class HiQiBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiQiBlogApplication.class, args);
    }
}
