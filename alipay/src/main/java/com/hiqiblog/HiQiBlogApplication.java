package com.hiqiblog;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * @author ${ww}
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.hiqiblog.feign")
@MapperScan("com.hiqiblog.mapper")
@EnableJms
@ServletComponentScan
public class HiQiBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(HiQiBlogApplication.class, args);
    }

}
