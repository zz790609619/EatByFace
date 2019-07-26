package com.hiqiblog.queue.producer;

import com.hiqiblog.queue.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

public abstract class BaseProducer implements Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Override
    public void enqueue(String message) {
        this.enqueue(this.getQueueName(), message);
    }

    protected void enqueue(String destinationName, String message) {
        jmsTemplate.convertAndSend(destinationName, message);
    }

    protected abstract String getQueueName();
}
