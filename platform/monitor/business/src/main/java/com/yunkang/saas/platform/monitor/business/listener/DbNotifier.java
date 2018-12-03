package com.yunkang.saas.platform.monitor.business.listener;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorValueService;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.model.Application;
import de.codecentric.boot.admin.notify.Notifier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;



public class DbNotifier implements Notifier {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private IndicatorValueService indicatorValueService;

    @Override
    public void notify(ClientApplicationEvent event) {
        Application application = event.getApplication();

        InstanceInfo instanceInfo = getInstanceInfo(application);
        makeIndicator(event.getTimestamp(), application.getStatusInfo().getStatus(), instanceInfo);
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
        indicatorValue.setIndicator("status.changed");
        indicatorValue.setTarget(instanceInfo.getId());
        indicatorValue.setValue(value);
        indicatorValueService.add(indicatorValue);
    }
}
