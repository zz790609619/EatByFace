package com.hiqiblog.controller;

import com.hiqiblog.entity.QuartzJobs;
import com.hiqiblog.service.IJobService;
import com.hiqiblog.service.impl.JobServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Author helloc
 * @Date 2019/5/7 19:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/job")
public class JobController {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private IJobService iJobService ;

    @RequestMapping(value = "/addJob",method = RequestMethod.GET)
    @ResponseBody
    public String addJob(){
        QuartzJobs job=new QuartzJobs();
        job.setJobGroup("ss");
        job.setJobName("ss");
        job.setCronExpression("*/5 * * * * ?");
        job.setDescription("ss");
        job.setJobClassName("com.hiqiblog.job.SendEmailJob");
        int result= iJobService.saveJob(job);
        if(result != 1){
            return "失败";
        }
        else{
            return "成功";
        }
    }

    @RequestMapping(value = "/startJob",method = RequestMethod.GET)
    @ResponseBody
    public  String trigger() {
        LOGGER.info("触发任务");
        int result = iJobService.triggerJob("ss", "ss");
        if(result != 1){
            return "失败";
        }
        else{
            return "成功";
        }
    }
}
