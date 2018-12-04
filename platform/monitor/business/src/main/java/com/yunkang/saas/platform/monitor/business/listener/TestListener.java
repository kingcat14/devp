package com.yunkang.saas.platform.monitor.business.listener;

import com.netflix.discovery.EurekaClient;
import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.alarm.service.AlarmAction;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.app.service.UnknownAppService;
import com.yunkang.saas.platform.monitor.business.collector.service.ApplicationStatusUpdater;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorValueService;
import de.codecentric.boot.admin.event.ClientApplicationDeregisteredEvent;
import de.codecentric.boot.admin.event.ClientApplicationRegisteredEvent;
import de.codecentric.boot.admin.event.ClientApplicationStatusChangedEvent;
import de.codecentric.boot.admin.model.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
    private UnknownAppService unknownAppService;

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

        log.info("onInstanceRegistered:{}" + application.getName());

        String instanceId = applicationInstanceService.getInstanceId(application);

        makeIndicator(event.getTimestamp(), "Registered", application.getStatusInfo().getStatus(), instanceId);

    }



    @EventListener
    public void onInstanceDeregistered(ClientApplicationDeregisteredEvent event){

        Application application = event.getApplication();

        log.info("onInstanceDeregistered:{}" + application.getName());
        String instanceId = applicationInstanceService.getInstanceId(application);
        makeIndicator(event.getTimestamp(), "Deregistered", application.getStatusInfo().getStatus(), instanceId);

    }
    @EventListener
    public void onStatusChanged(ClientApplicationStatusChangedEvent event){
        log.info("onStatusChanged:{}" + event.getApplication());
        Application application = event.getApplication();

        String applicationName = application.getName();

        if(StringUtils.equals("OFFLINE",event.getTo().getStatus())){

        }

        /*
         * 1.先刷一下应用状态
         * 2.判断是否是已知应用, 如果是已知应用,则保存状态值
         */
        applicationStatusUpdater.update(eurekaClient.getApplication(applicationName));

        if(!applicationService.contain(applicationName)){
            return;
        }

        String instanceId = applicationInstanceService.getInstanceId(application);

        IndicatorValue indicatorValue = makeIndicator(event.getTimestamp(), "StatusChanged", event.getFrom().getStatus() +"->"+event.getTo().getStatus(), instanceId);

        Alarm alarm = new Alarm();
        alarm.setApp(applicationName);
        alarm.setCounter(indicatorValue.getId());
        alarm.setContent("状态:"+event.getFrom().getStatus()+"-->"+event.getTo().getStatus());
        alarm.setOccurTime(new Date());
        alarm.setCode("STATUS.CHANGE");
        alarm.setName("STATUS.CHANGE");
        alarm.setValue(indicatorValue.getValue());
        alarmAction.add(alarm);
    }

    //@EventListener
    public void onEvent(Object o) {

        log.info("onEvent:{}" + o);
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
