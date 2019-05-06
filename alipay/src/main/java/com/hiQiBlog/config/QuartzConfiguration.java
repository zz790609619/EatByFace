package com.hiQiBlog.config;



import com.hiQiBlog.conf.JobFactory;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
@Configuration
public class QuartzConfiguration {



    /**
     * @author ${ww}
     * @Title: QuartzConfigration
     * @ProjectName javaemaildemo
     * @Description: TODO
     * @date
     */
    @Configuration
    @EnableScheduling
    public class QuartzConfigration {

        @Autowired
        private JobFactory jobFactory;

        @Bean
        public SchedulerFactoryBean schedulerFactoryBean() {
            SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setJobFactory(jobFactory);
            return schedulerFactoryBean;
        }


        // 创建schedule
        @Bean(name = "scheduler")
        public Scheduler scheduler() {
            return schedulerFactoryBean().getScheduler();
        }

    }
}
