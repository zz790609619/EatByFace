package com.hiqiblog.init;

import com.hiqiblog.entity.QuartzJobs;
import com.hiqiblog.enums.JobStatus;
import com.hiqiblog.mapper.QuartzJobsMapper;
import com.hiqiblog.service.IJobService;
import com.hiqiblog.service.impl.JobServiceImpl;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 主要用于实现在应用初始化后，去执行一段代码块逻辑，这段初始化代码在整个应用生命周期内只会执行一次。
 * 三种实现方式
 * 1.和@Component注解一起使用,和下文一样
 * 2.和@SpringBootApplication注解一起使用。启动类后面实现CommandLineRunner，在启动类里面重写run方法
 * 3.声明一个实现了CommandLineRunner接口的Bean   在启动类中调用
 */

/**
 * @Author helloc
 * @Date 2019/5/7 15:03
 * @Version 1.0
 */
@Component
public class ApplicationInit implements CommandLineRunner {

   private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInit.class);

    @Autowired
    private QuartzJobsMapper quartzJobsMapper;
    @Autowired
    private IJobService jobService;
    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        loadJobToQuartz();
    }

    private void loadJobToQuartz() throws Exception {
        LOGGER.info("quartz job load...");
        //从数据库中找到所有的job
        List<QuartzJobs> jobs = quartzJobsMapper.listJob("ss");
        //循环启动job
        for(QuartzJobs job : jobs) {
            jobService.schedulerJob(job);
            //如果数据库中状态为暂停 就暂停job
            if (JobStatus.PAUSED.equals(job.getTriggerState())) {
                scheduler.pauseJob(new JobKey(job.getJobName(), job.getJobGroup()));
            }
        }
    }
}

