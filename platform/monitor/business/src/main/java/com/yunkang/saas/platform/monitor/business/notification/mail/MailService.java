package com.yunkang.saas.platform.monitor.business.notification.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage mailMessage){

        if(ArrayUtils.getLength(mailMessage.getTo()) < 1){
            log.error("mail receiver not set. ignore the message");
            return;
        }
        mailMessage.setFrom("13962217@qq.com");
//        mailMessage.setTo("hongruigong@qq.com");
        mailSender.send(mailMessage);
    }
}
