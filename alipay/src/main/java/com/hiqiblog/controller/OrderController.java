package com.hiqiblog.controller;

import com.hiqiblog.ViewModel.ResponseMessage;
import com.hiqiblog.queue.producer.BaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

/** 功能描述：模拟微信支付回调 @Author helloc @Date 2019/7/25 14:00 @Version 1.0 */
@RestController
@RequestMapping("/api/v1")
public class OrderController {

  @Autowired private BaseProducer baseProducer;
  /**
   * 功能描述：微信支付回调接口
   *
   * @param msg 支付信息
   * @return
   */
  @GetMapping("queue")
  public Object order(String msg) {
    baseProducer.enqueue(msg);
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setCode("1");
    responseMessage.setMsg("Success");
    return responseMessage;
  }

  @GetMapping("topic")
  public Object common(String msg) {
    baseProducer.enqueue(msg);
    ResponseMessage responseMessage = new ResponseMessage();
    responseMessage.setCode("1");
    responseMessage.setMsg("Success");
    return responseMessage;
  }

}

