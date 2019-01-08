package com.yunkang.saas.platform.monitor.business.notification.sender;

import com.yunkang.saas.platform.monitor.business.notification.mail.MailService;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationCenter {


    @Autowired
    AliSmsSender aliSmsSender;

    @Autowired
    private MailService mailService;

    @Value("${notify.sms.enable:false}")
    private Boolean smsEnable;


    /**
     * 状态变化信息
     * @param type
     * @param supporter
     * @param targetName
     * @param status
     * @param aliveCount
     */
    @Async
    public void statueChange(String type, Supporter supporter, String targetName, String status, int aliveCount){

        if(!(StringUtils.equals(type, "SMS") || StringUtils.equals(type, "EMAIL"))){
            log.error("NOTIFICATION TYPE {}, NOT SUPPORT.", type);
        }

        if(StringUtils.equals(type, "SMS") && smsEnable){
            aliSmsSender.sendAppAlarm(supporter.getMobile(), targetName, status, aliveCount);
        }

        if(StringUtils.isNotEmpty(supporter.getEmail())){
            String messageTpl = "应用%s, 有实例的状态变为:%s。当前存活数量:%s";
            String messageTitle = "INSTANCE OF %s : %s ";
            String message = String.format(messageTpl, targetName, status, aliveCount+"");
            log.warn(message);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setText(message);
            simpleMailMessage.setTo(supporter.getEmail());
            simpleMailMessage.setSubject(String.format(messageTitle, targetName, status));
            mailService.send(simpleMailMessage);
        }


    }

}
