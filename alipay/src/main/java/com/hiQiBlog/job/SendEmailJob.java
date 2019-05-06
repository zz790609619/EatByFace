package com.hiQiBlog.job;

import com.hiQiBlog.service.SendEmailSevice;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ${lcl}
 * @Title: HelloJob
 * @ProjectName javaemaildemo
 * @Description: TODO
 * @date 2019/2/27 002713:13
 */
public class SendEmailJob implements  Job {
    @Autowired
    private SendEmailSevice emailService;
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在的时间是：" + sf.format(date));
        //具体的业务逻辑
        System.out.println("开始任务");
        emailService.sendEmail();
    }
}
