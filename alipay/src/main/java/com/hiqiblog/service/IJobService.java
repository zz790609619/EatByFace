package com.hiqiblog.service;

import com.hiqiblog.entity.QuartzJobs;

import java.util.List;

/**
 * @Author helloc
 * @Date 2019/5/7 19:16
 * @Version 1.0
 */
public interface IJobService {
    /**
     * 获取所有
     * @param jobName
     * @return
     */

    List<QuartzJobs> listQuartzJob(String jobName);

    /**
     * 新增job
     * @param quartz
     * @return
     */
    int saveJob(QuartzJobs quartz);

    /**
     * 触发job
     * @param jobName
     * @param jobGroup
     * @return
     */
    int triggerJob(String jobName, String jobGroup);

//    /**
//     * 暂停job
//     * @param jobName
//     * @param jobGroup
//     * @return
//     */
//    int pauseJob(String jobName, String jobGroup);
//
//    /**
//     * 恢复job
//     * @param jobName
//     * @param jobGroup
//     * @return
//     */
//    int resumeJob(String jobName, String jobGroup);
//
//    /**
//     * 移除job
//     * @param jobName
//     * @param jobGroup
//     * @return
//     */
//    int removeJob(String jobName, String jobGroup);
//
//    /**
//     * 根据jobname 和jobgroup 获得job
//     * @param jobName
//     * @param jobGroup
//     * @return
//     */
//    QuartzJob getJob(String jobName, String jobGroup);
//    /**
//     * 更新
//     * @param quartz
//     * @return
//     */
//    int updateJob(QuartzJob quartz);
//
    /**
     * 计划
     * @param job
     * @throws Exception
     */
    void schedulerJob(QuartzJobs job) throws Exception;
}
