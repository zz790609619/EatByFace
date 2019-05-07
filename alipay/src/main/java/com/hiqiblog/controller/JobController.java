package com.hiqiblog.controller;

import com.hiqiblog.entity.QuartzJob;
import com.hiqiblog.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author helloc
 * @Date 2019/5/7 19:52
 * @Version 1.0
 */
@Controller
public class JobController {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private IJobService iJobService;
    @RequestMapping(value = "/addJob",method = RequestMethod.GET)
    @ResponseBody
    public String addJob(){
        QuartzJob job=new QuartzJob();
        job.setJobGroup("ss");
        job.setJobName("ss");
        job.setCronExpression("*/5 * * * * ?");
        job.setDescription("ss");
        job.setJobClassName("SendEmailJob");
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
