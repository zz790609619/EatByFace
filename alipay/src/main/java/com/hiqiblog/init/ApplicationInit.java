package com.hiqiblog.init;

import com.hiqiblog.entity.QuartzJob;
import com.hiqiblog.enums.JobStatus;
import com.hiqiblog.mapper.QuartzJobMapper;
import com.hiqiblog.service.IJobService;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author helloc
 * @Date 2019/5/7 15:03
 * @Version 1.0
 */
@Component
public class ApplicationInit implements CommandLineRunner {

   private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInit.class);

    @Autowired
    private QuartzJobMapper jobMapper;
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
        List<QuartzJob> jobs = jobMapper.listJob("ss");
        for(QuartzJob job : jobs) {
            jobService.schedulerJob(job);
            if (JobStatus.PAUSED.equals(job.getTriggerState())) {
                scheduler.pauseJob(new JobKey(job.getJobName(), job.getJobGroup()));
            }
        }
    }
}

