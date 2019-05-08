package com.hiqiblog.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * @Author helloc
 * @Date 2019/5/8 15:36
 * @Version 1.0
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private SpringJobFactory  springJobFactory;

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //自动启动
        schedulerFactoryBean.setAutoStartup(true);
        //延时5秒启动
        schedulerFactoryBean.setStartupDelay(5);
        schedulerFactoryBean.setJobFactory(springJobFactory);
        return schedulerFactoryBean;
    }

    /**
     * 被spring管理的bean
     * @return
     * @throws IOException
     */
    @Bean
    public Scheduler scheduler()  throws IOException{
        return schedulerFactoryBean().getScheduler();
    }

}
