package com.alipay.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.alipay.demo.mapper")
@ServletComponentScan
public class demoApplication {
    public static void main(String[] args) {
        SpringApplication.run(demoApplication.class, args);
    }
}
