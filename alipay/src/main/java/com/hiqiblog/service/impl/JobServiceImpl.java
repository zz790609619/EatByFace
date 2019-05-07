package com.hiqiblog.service.impl;

import com.hiqiblog.entity.QuartzJob;
import com.hiqiblog.enums.JobStatus;
import com.hiqiblog.mapper.QuartzJobMapper;
import com.hiqiblog.service.IJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author helloc
 * @Date 2019/5/7 19:21
 * @Version 1.0
 */
public class JobServiceImpl implements IJobService {

    private static final String TRIGGER_IDENTITY = "trigger";

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private QuartzJobMapper jobMapper;

    @Override
    public List<QuartzJob> listQuartzJob(String jobName) {
        List<QuartzJob> jobList = jobMapper.listJob(jobName);
        return jobList;
    }

    @Override
    public int saveJob(QuartzJob quartz){
        try {
            schedulerJob(quartz);
            quartz.setTriggerState(JobStatus.RUNNING.getStatus());
            quartz.setOldJobGroup(quartz.getJobGroup());
            quartz.setOldJobName(quartz.getJobName());
            jobMapper.saveJob(quartz);
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

    @Override
    public int pauseJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName,jobGroup);
        try {
            scheduler.pauseJob(key);
            jobMapper.updateJobStatus(jobName, jobGroup, JobStatus.PAUSED.getStatus());
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    @Override
    public int resumeJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName,jobGroup);
        try {
            scheduler.resumeJob(key);
            jobMapper.updateJobStatus(jobName,jobGroup, JobStatus.RUNNING.getStatus());
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int removeJob(String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_IDENTITY + jobName, jobGroup);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            jobMapper.removeQuartzJob(jobName, jobGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public QuartzJob getJob(String jobName, String jobGroup) {
        QuartzJob job= jobMapper.getJob(jobName, jobGroup);
        return job;
    }

    @Override
    public int updateJob(QuartzJob quartz) {
        try {

            scheduler.deleteJob(new JobKey(quartz.getOldJobName(),quartz.getOldJobGroup()));
            schedulerJob(quartz);

            quartz.setOldJobGroup(quartz.getJobGroup());
            quartz.setOldJobName(quartz.getJobName());
            jobMapper.updateJob(quartz);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public void schedulerJob(QuartzJob job) throws Exception {
        //构建job信息
        Class cls = Class.forName(job.getJobClassName()) ;
        // cls.newInstance(); // 检验类是否存在
        JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(job.getJobName(),job.getJobGroup())
                .withDescription(job.getDescription()).build();

        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression().trim());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_IDENTITY + job.getJobName(), job.getJobGroup())
                .startNow().withSchedule(cronScheduleBuilder).build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
