package com.hiqiblog.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author helloc
 * @Date 2019/7/26 11:40
 * @Version 1.0
 */

@RestController
public class ConsumeController {
    /**
     * 客户控制器
     */
    @JmsListener(destination = "helloq")
    public void readActiveQueue(String message){
        System.out.println("接收到"+message);
    }
}
