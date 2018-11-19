package com.yunkang.saas.platform.monitor.business.listener;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
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
import java.util.List;

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

    @EventListener
    public void onInstanceRegistered(ClientApplicationRegisteredEvent event) {

        Application application = event.getApplication();

        InstanceInfo instanceInfo = getInstanceInfo(application);


        log.info("onInstanceRegistered:{}" + application.getName());

        makeIndicator(event.getTimestamp(), application.getStatusInfo().getStatus(), instanceInfo);

    }

    @EventListener
    public void onInstanceDeregistered(ClientApplicationDeregisteredEvent event){

        Application application = event.getApplication();

        InstanceInfo instanceInfo = getInstanceInfo(application);

        log.info("onInstanceDeregistered:{}" + application.getName());

        makeIndicator(event.getTimestamp(), application.getStatusInfo().getStatus(), instanceInfo);

    }
    @EventListener
    public void onInstanceRegistered(ClientApplicationStatusChangedEvent event){
        log.info("onInstanceDeregistered:{}" + event.getApplication());
        Application application = event.getApplication();

        InstanceInfo instanceInfo = getInstanceInfo(application);
        makeIndicator(event.getTimestamp(), application.getStatusInfo().getStatus(), instanceInfo);

    }

//    @EventListener
//    public void onInstanceRegistered(InstanceRegisteredEvent instanceRegisteredEvent){
//        log.info("onInstanceDeregistered:{}" + instanceRegisteredEvent.getInstance());
//    }
//    @EventListener
//    public void onInstanceDeregisteredEvent(InstanceDeregisteredEvent instanceRegisteredEvent){
//        log.info("onInstanceDeregistered:{}" + instanceRegisteredEvent.getInstance());
//    }

    @EventListener
    public void onEvent(Object o) {

        log.info("onEvent:{}" + o);
    }



    private InstanceInfo getInstanceInfo(Application application){
        String appName = application.getName();

        List<InstanceInfo> instanceInfoList = eurekaClient.getApplication(appName).getInstances();

        InstanceInfo result = null;
        for(InstanceInfo instanceInfo : instanceInfoList){
            if(StringUtils.equalsIgnoreCase(application.getHealthUrl(), instanceInfo.getHealthCheckUrl())){
                result = instanceInfo;
                break;
            }
        }
        return result;
    }

    private void makeIndicator(long collectTime, String value, InstanceInfo instanceInfo) {
        IndicatorValue indicatorValue = new IndicatorValue();
        indicatorValue.setCollectTime(new Date(collectTime));
        indicatorValue.setSaveTime(new Date());
        indicatorValue.setIndicator("status.registered");
        indicatorValue.setTarget(instanceInfo.getId());
        indicatorValue.setValue(value);
        indicatorValueService.add(indicatorValue);
    }
}
