package com.yunkang.saas.platform.monitor.business.listener;

import com.netflix.discovery.EurekaClient;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorValueService;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.model.Application;
import de.codecentric.boot.admin.notify.Notifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


@Slf4j
public class DbNotifier implements Notifier {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private IndicatorValueService indicatorValueService;

    @Autowired
    private ApplicationInstanceService applicationInstanceService;

    @Override
    public void notify(ClientApplicationEvent event) {

        Application application = event.getApplication();

        String instanceId = applicationInstanceService.getInstanceId(application);

        makeIndicator(event.getTimestamp(), application.getStatusInfo().getStatus(), instanceId);
    }

    private void makeIndicator(long collectTime, String value, String instanceId) {
        IndicatorValue indicatorValue = new IndicatorValue();
        indicatorValue.setCollectTime(new Date(collectTime));
        indicatorValue.setSaveTime(new Date());
        indicatorValue.setIndicator("DbNotifier");
        indicatorValue.setTargetType("INSTANCE");
        indicatorValue.setTarget(instanceId);
        indicatorValue.setValue(value);
        indicatorValueService.add(indicatorValue);
    }


}
