package com.hiqiblog.service.impl;

import com.hiqiblog.entity.QuartzJobs;
import com.hiqiblog.enums.JobStatus;
import com.hiqiblog.job.SendEmailJob;
import com.hiqiblog.mapper.QuartzJobsMapper;
import com.hiqiblog.service.IJobService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author helloc
 * @Date 2019/5/7 19:21
 * @Version 1.0
 */
@Service
public class JobServiceImpl implements IJobService {
    @Autowired
    private QuartzJobsMapper quartzJobsMapper;
    @Autowired
    private Scheduler scheduler;

    private static final String TRIGGER_IDENTITY = "trigger";



    @Override
    public List<QuartzJobs> listQuartzJob(String jobName) {
        List<QuartzJobs> jobList = quartzJobsMapper.listJob(jobName);
        return jobList;
    }

    @Override
    public int saveJob(QuartzJobs quartz){
        try {
            schedulerJob(quartz);
            quartz.setTriggerState(JobStatus.RUNNING.getStatus());
            quartz.setOldJobGroup(quartz.getJobGroup());
            quartz.setOldJobName(quartz.getJobName());
            quartzJobsMapper.insert(quartz);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int triggerJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName,jobGroup);
        try {
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

//    @Override
//    public int pauseJob(String jobName, String jobGroup) {
//        JobKey key = new JobKey(jobName,jobGroup);
//        try {
//            scheduler.pauseJob(key);
//            jobMapper.updateJobStatus(jobName, jobGroup, JobStatus.PAUSED.getStatus());
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    public int resumeJob(String jobName, String jobGroup) {
//        JobKey key = new JobKey(jobName,jobGroup);
//        try {
//            scheduler.resumeJob(key);
//            jobMapper.updateJobStatus(jobName,jobGroup, JobStatus.RUNNING.getStatus());
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//            return 0;
//        }
//        return 1;
//    }
//
//    @Override
//    public int removeJob(String jobName, String jobGroup) {
//        try {
//            TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_IDENTITY + jobName, jobGroup);
//            // 停止触发器
//            scheduler.pauseTrigger(triggerKey);
//            // 移除触发器
//            scheduler.unscheduleJob(triggerKey);
//            // 删除任务
//            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
//            jobMapper.removeQuartzJob(jobName, jobGroup);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//        return 1;
//    }
//
//    @Override
//    public QuartzJob getJob(String jobName, String jobGroup) {
//        QuartzJob job= jobMapper.getJob(jobName, jobGroup);
//        return job;
//    }
//
//    @Override
//    public int updateJob(QuartzJobs quartz) {
//        try {
//
//            scheduler.deleteJob(new JobKey(quartz.getOldJobName(),quartz.getOldJobGroup()));
//            schedulerJob(quartz);
//
//            quartz.setOldJobGroup(quartz.getJobGroup());
//            quartz.setOldJobName(quartz.getJobName());
//            jobMapper.updateJob(quartz);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//        return 1;
//    }

    @Override
    public void schedulerJob(QuartzJobs job) throws Exception {
//        构建job信息
//        Class cls = Class.forName(job.getJobClassName()) ;
//         检验类是否存在
//        boolean isExist= (boolean)cls.newInstance();
        //用JobDetail来装饰Job
        JobDetail jobDetail = JobBuilder.newJob(SendEmailJob.class).withIdentity(job.getJobName(),job.getJobGroup())
                .withDescription(job.getDescription()).build();

        // 触发时间点(cron表达式 "*/5 * * * * ?"==五秒一次)
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression().trim());
        //建立触发器,将触发时间点配置到触发器中
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_IDENTITY + job.getJobName(), job.getJobGroup())
                .startNow().withSchedule(cronScheduleBuilder).build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
