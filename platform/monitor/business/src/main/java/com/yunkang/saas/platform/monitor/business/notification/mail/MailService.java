package com.yunkang.saas.platform.monitor.business.notification.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@Slf4j
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void send(SimpleMailMessage mailMessage){

        try {
            javax.mail.internet.MimeUtility.encodeText("服务监控");
        } catch (UnsupportedEncodingException e) {
            log.error("设置发件人昵称出错");
        }

        if(ArrayUtils.getLength(mailMessage.getTo()) < 1){
            log.error("mail receiver not set. ignore the message");
            return;
        }
        mailMessage.setFrom(from);
//        mailMessage.setTo("hongruigong@qq.com");
        mailSender.send(mailMessage);
    }
}
