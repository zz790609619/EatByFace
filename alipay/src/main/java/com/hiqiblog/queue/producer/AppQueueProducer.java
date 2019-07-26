package com.hiqiblog.queue.producer;

import org.springframework.stereotype.Component;

@Component
public class AppQueueProducer extends BaseProducer {

    public final static String APP_QUEUE = "helloq";

    @Override
    protected String getQueueName() {
        return APP_QUEUE;
    }

}
