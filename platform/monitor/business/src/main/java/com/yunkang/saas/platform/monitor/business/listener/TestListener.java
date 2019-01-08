package com.yunkang.saas.platform.monitor.business.listener;

import com.netflix.discovery.EurekaClient;
import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.alarm.service.AlarmAction;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.collector.service.ApplicationStatusUpdater;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorValueService;
import de.codecentric.boot.admin.event.ClientApplicationDeregisteredEvent;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.event.ClientApplicationRegisteredEvent;
import de.codecentric.boot.admin.event.ClientApplicationStatusChangedEvent;
import de.codecentric.boot.admin.model.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 参考 sba的 InstanceDiscoveryListener
 */
@Component
@Slf4j
public class TestListener {


    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private IndicatorValueService indicatorValueService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationInstanceService applicationInstanceService;

    @Autowired
    private ApplicationStatusUpdater applicationStatusUpdater;

    @Autowired
    private AlarmAction alarmAction;

    @EventListener
    public void onInstanceRegistered(ClientApplicationRegisteredEvent event) {

        Application application = event.getApplication();

        log.info("onInstanceRegistered:{}" + event.getApplication().getName());

    }

    @EventListener
    public void onInstanceDeregistered(ClientApplicationDeregisteredEvent event){

        log.info("onInstanceDeregistered:{},{}" , event.getApplication().getName(), event.getApplication().getStatusInfo().getStatus());

        monitorStatusChange(event, "OFFLINE");


    }
    @EventListener
    public void onStatusChanged(ClientApplicationStatusChangedEvent event){
        log.info("onStatusChanged:{}" + event.getApplication());


        Application application = event.getApplication();

        //如果是事件则直接跳过, 这里就只会处理 UP 事件
        if(StringUtils.equals("OFFLINE", event.getTo().getStatus())){
            return;
        }


        monitorStatusChange(event, null);
    }

    @Transactional
    public void monitorStatusChange(ClientApplicationEvent event, String status){

        String targetStatus = status;

        if(StringUtils.isEmpty(targetStatus)){
            targetStatus = event.getApplication().getStatusInfo().getStatus();
        }

        Application application = event.getApplication();
        String applicationName = event.getApplication().getName();

        /*不监控直接跳过*/
        if(!applicationService.contain(applicationName)){
            return;
        }

        /*
         * 1.先刷一下应用状态
         * 2.判断是否是已知应用, 如果是已知应用,则保存状态值
         */
        applicationStatusUpdater.update(eurekaClient.getApplication(applicationName));


        String instanceId = applicationInstanceService.getInstanceId(application);

        IndicatorValue indicatorValue = makeIndicator(event.getTimestamp(), event.getType(), targetStatus, instanceId);

        if(!StringUtils.equals("OFFLINE", targetStatus) && !StringUtils.equals("DOWN", targetStatus) ){
            return;
        }

        Alarm alarm = new Alarm();
        alarm.setApp(applicationName);
        alarm.setCounter(indicatorValue.getId());
        alarm.setContent("状态:" + application.getStatusInfo().getStatus());
        alarm.setOccurTime(new Date());
        alarm.setCode(event.getType());
        alarm.setName(event.getType());
        alarm.setValue(indicatorValue.getValue());
        alarmAction.add(alarm);
    }


    private IndicatorValue makeIndicator(long collectTime, String indicatorType, String value, String instanceId) {

        IndicatorValue indicatorValue = new IndicatorValue();
        indicatorValue.setCollectTime(new Date(collectTime));
        indicatorValue.setSaveTime(new Date());
        indicatorValue.setIndicator(indicatorType);
        indicatorValue.setTarget(instanceId);
        indicatorValue.setTargetType("INSTANCE");
        indicatorValue.setValue(value);
        indicatorValueService.add(indicatorValue);

        return indicatorValue;
    }


}
