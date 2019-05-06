package com.hiQiBlog.service;

import com.hiQiBlog.entity.User;

import java.util.List;

public interface SendEmailSevice {
   public boolean sendEmail();
   public boolean  sendAttachmentMail();
}
