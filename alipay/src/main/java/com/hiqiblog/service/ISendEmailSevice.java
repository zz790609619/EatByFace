package com.hiqiblog.service;
/**
 * @author ${ww}=
 * @Description: TODO
 */
public interface ISendEmailSevice {
    /**
     * 发送邮件
     * @return
     */
     boolean sendEmail();

    /**
     * 发送带附件的邮件
     * @return
     */
     boolean  sendAttachmentMail();
}
