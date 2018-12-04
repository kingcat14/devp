package com.yunkang.saas.platform.monitor.business.notification.sender;

import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorValueService;
import com.yunkang.saas.platform.monitor.business.notification.mail.MailService;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationCenter {


    @Autowired
    AliSmsSender aliSmsSender;

    @Autowired
    private MailService mailService;

    @Autowired
    private IndicatorValueService indicatorValueService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationInstanceService instanceService;

    public void sendAlarm(String type, Supporter supporter, Alarm alarm){

        IndicatorValue indicatorValue = indicatorValueService.find(alarm.getCounter());
        indicatorValueService.find(indicatorValue.getTarget());


        String targetName = getTargetName(indicatorValue);
        int aliveCount = getAliveCount(indicatorValue);

        if(StringUtils.equals(type, "SMS")){
            //aliSmsSender.sendAppAlarm(supporter.getMobile(), targetName, indicatorValue.getValue(), aliveCount);
        }

        if(StringUtils.isNotEmpty(supporter.getEmail())){
            String messageTpl = "应用%s,有实例的状态变为:%s。当前存活数量:%s";
            String message = String.format(messageTpl, targetName, indicatorValue.getValue(), aliveCount+"");
            log.warn(message);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setText(message);
            simpleMailMessage.setTo(supporter.getEmail());
            simpleMailMessage.setSubject("应用("+targetName+")实例状态发生变化!");
            mailService.send(simpleMailMessage);
        }

        if(!(StringUtils.equals(type, "SMS") || StringUtils.equals(type, "EMAIL"))){
            log.error("NOTIFICATION TYPE {}, NOT SUPPORT.", type);
        }
    }


    int getAliveCount(IndicatorValue indicatorValue){

        int result = 0;

        String targetType = indicatorValue.getTargetType();
        String target = indicatorValue.getTarget();
        Application application = null;

        if(StringUtils.equals("INSTANCE", targetType)){
            ApplicationInstance instance = instanceService.find(target);
            if(instance != null){
                application = applicationService.find(instance.getApp());
            }
        }

        if(StringUtils.equals("APPLICATION", targetType)){
            application = applicationService.find(target);
        }
        if(application != null){
            result = application.getAliveCount();
        }

        return result;
    }
    String getTargetName(IndicatorValue indicatorValue){

        String result = "不确定";
        String targetType = indicatorValue.getTargetType();
        String target = indicatorValue.getTarget();

        if(StringUtils.equals("INSTANCE", targetType)){
            ApplicationInstance instance = instanceService.find(target);
            if(instance != null){
                Application application = applicationService.find(instance.getApp());
                if(application != null){
                    result = application.getName();
                }
            }
        }

        if(StringUtils.equals("APPLICATION", targetType)){
            Application application = applicationService.find(target);
            if(application != null){
                result = application.getName() ;
            }
        }

        return result;

    }





}
