package com.hiQiBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.hiQiBlog.mapper")
@ServletComponentScan
public class hiQiBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(hiQiBlogApplication.class, args);
    }
}
