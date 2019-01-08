package com.yunkang.saas.platform.monitor.business.alarm.service;

import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorValueService;
import com.yunkang.saas.platform.monitor.business.notification.sender.NotificationCenter;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import com.yunkang.saas.platform.monitor.business.supporter.domain.SupporterAppRelation;
import com.yunkang.saas.platform.monitor.business.supporter.service.SupporterAppRelationService;
import com.yunkang.saas.platform.monitor.business.supporter.service.SupporterService;
import org.apache.commons.lang3.StringUtils;
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
    private ApplicationInstanceService instanceService;

    @Autowired
    private IndicatorValueService indicatorValueService;

    @Autowired
    private SupporterService supporterService;

    public void add(Alarm alarm){

        alarmService.add(alarm);

//        Application application = applicationService.find(alarm.getApp());

        List<SupporterAppRelation> relationList = supporterAppRelationService.findByApplication(alarm.getApp());

        IndicatorValue indicatorValue = indicatorValueService.find(alarm.getCounter());

        String targetName = getTargetName(indicatorValue);
        int aliveCount = getAliveCount(indicatorValue);

        for(SupporterAppRelation relation : relationList){

            Supporter supporter = supporterService.find(relation.getSupporter());
            if(supporter==null){
                continue;
            }




            notificationCenter.statueChange(relation.getNotificationType(), supporter, targetName, indicatorValue.getValue(), aliveCount);

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
