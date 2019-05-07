package com.hiqiblog;

import com.hiqiblog.service.IQuartzService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

@Autowired
IQuartzService e;
    /**
     * 发送html格式Email
     */
    @Test
    public void testEmail() throws Exception {
        //sendEmailService.sendSimpleMail();
        e.startJob("*/5 * * * * ? ","zz","zz");
    }

}
