package com.yunkang.saas.platform.monitor.business.notification.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage mailMessage){

        mailMessage.setFrom("13962217@qq.com");
        mailMessage.setTo("hongruigong@qq.com");

        mailSender.send(mailMessage);
    }
}
