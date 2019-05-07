package com.hiqiblog.service.impl;


import com.hiqiblog.service.ISendEmailSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author ${ww}
 * @Description: TODO
 */
@Service
public class SendEmailSeviceImpl implements ISendEmailSevice {
    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 注入spring发送邮件的对象
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("790609619@qq.com");
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setSubject("测试");
        simpleMailMessage.setText("utf-8");
        try {
            //执行发送
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sendAttachmentMail() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo("1579258595@qq.com");
            helper.setSubject("测试");
            helper.setText("utf-8");
            helper.setFrom(fromEmail);
//            if(filepath.size()>0){						//读取附件文件（传入文件路径）
//                for (Object string : filepath) {		//遍历文件数组，实现多个附件的添加
//                    FileSystemResource file = new FileSystemResource(string.toString());
//                    String fileName = file.getFilename();//获取文件名
//                    helper.addAttachment(fileName, file);//参数：文件名，文件路径
//                }
//                try {
//                    javaMailSender.send(mimeMessage);		//发送邮件
//                } catch (Exception e) {
//                    return false;						//发送出现异常(或者文件路径不对)
//                }
//                return true;							//成功发送
//            }else {
//                return false;    						//没有附件文件（中断发送）
//            }
            try {
                //发送邮件
                    javaMailSender.send(mimeMessage);
                } catch (Exception e) {
                //发送出现异常(或者文件路径不对)
                    return false;
                }
                return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            //捕获到创建MimeMessageHelper的异常
//			return false;
            return true;
        }

    }
}
