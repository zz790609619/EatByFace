package com.hiqiblog.job;

import com.hiqiblog.service.ISendEmailService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 串行执行(去掉是并行)
 */
@DisallowConcurrentExecution
/**
 * @author ${ww}
 * @date
 */
public class SendEmailJob implements  Job {

    @Autowired
     ISendEmailService sendEmailService;
    /**
     *  重写excute方法 执行定时器的业务逻辑
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在的时间是：" + sf.format(date));
        //具体的业务逻辑
        System.out.println("开始任务");
        sendEmailService.sendEmail();
        System.out.println("结束任务");
    }
}
