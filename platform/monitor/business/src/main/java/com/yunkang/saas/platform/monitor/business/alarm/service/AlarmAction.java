package com.yunkang.saas.platform.monitor.business.alarm.service;

import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.notification.sender.NotificationCenter;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import com.yunkang.saas.platform.monitor.business.supporter.domain.SupporterAppRelation;
import com.yunkang.saas.platform.monitor.business.supporter.service.SupporterAppRelationService;
import com.yunkang.saas.platform.monitor.business.supporter.service.SupporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 告警服务
 */
@Service
public class AlarmAction {

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private NotificationCenter notificationCenter;

    @Autowired
    private SupporterAppRelationService supporterAppRelationService;

    @Autowired
    private SupporterService supporterService;

    public void add(Alarm alarm){

        alarmService.add(alarm);

//        Application application = applicationService.find(alarm.getApp());

        List<SupporterAppRelation> relationList = supporterAppRelationService.findByApplication(alarm.getApp());

        for(SupporterAppRelation relation : relationList){

            Supporter supporter = supporterService.find(relation.getSupporter());
            if(supporter==null){
                continue;
            }

            notificationCenter.sendAlarm(relation.getNotificationType(), supporter, alarm);


        }


    }

}
