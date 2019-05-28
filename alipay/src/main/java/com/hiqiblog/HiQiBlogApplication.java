package com.hiqiblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author ${ww}
 */
@SpringBootApplication
@MapperScan("com.hiqiblog.mapper")
@ServletComponentScan
public class HiQiBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiQiBlogApplication.class, args);
    }
}
