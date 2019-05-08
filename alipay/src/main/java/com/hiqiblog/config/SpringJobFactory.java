package com.hiqiblog.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;
/**
 * @Author helloc
 * @Date 2019/5/8 15:30
 * @Version 1.0
 */

    @Component
    public class SpringJobFactory  extends AdaptableJobFactory
    {
        /**
         * 将Qurartz创建好Job的bean放入这个BeanFactory进行管理，方便job任务中自动装配其他的Spring的bean.
         * 例如：SendEmailJob中的sendEmailService的autowired
         */
        @Autowired
        private AutowireCapableBeanFactory capableBeanFactory;
        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception
        {
            // 首先，调用父类的方法创建好Quartz所需的Job实例
            Object jobInstance = super.createJobInstance(bundle);
            // 然后，使用BeanFactory为创建好的Job实例进行属性自动装配并将其纳入到Spring容器的管理之中
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }



