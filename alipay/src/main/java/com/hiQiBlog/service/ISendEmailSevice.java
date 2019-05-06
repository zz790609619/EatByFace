package com.hiQiBlog.service;

import com.hiQiBlog.entity.User;

import java.util.List;

public interface ISendEmailSevice {
    public boolean sendEmail();
    public boolean  sendAttachmentMail();
}
