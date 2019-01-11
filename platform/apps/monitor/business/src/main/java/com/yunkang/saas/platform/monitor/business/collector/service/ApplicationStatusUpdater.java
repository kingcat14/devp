package com.yunkang.saas.platform.monitor.business.collector.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.domain.UnknownApp;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.app.service.UnknownAppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ApplicationStatusUpdater {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationInstanceService applicationInstanceService;

    @Autowired
    private UnknownAppService unknownAppService;

    public void update(Application application){

        if(unknownAppService.contain(application.getName())){
            unknown(application);
        }else{
            application(application);
        }
//        application(application);
    }

    public void application(Application application){

        log.info("application: {}", application.getName());

        //不存在则加入
        if(!applicationService.contain(application.getName())){
            applicationService.add(application.getName());
        }

        int aliveCount = 0;
        for(int i = 0; i < CollectionUtils.size(application.getInstances()); i++){
            InstanceInfo instanceInfo = application.getInstances().get(i);
            InstanceInfo.InstanceStatus status = instanceInfo.getStatus();
            log.info(" instance:({}), status:({})", instanceInfo.getHealthCheckUrl(), instanceInfo.getStatus());
            if(InstanceInfo.InstanceStatus.UP.equals(status)){
                ++aliveCount;
            }
        }

        applicationService.markAlive(application.getName(), aliveCount);

        List<InstanceInfo> instanceInfoList = application.getInstances();

        for(InstanceInfo info : instanceInfoList){
            instance(info);
        }
    }

    public void application(String code){
        Application application = eurekaClient.getApplication(code);
        application(application);
    }

    public void instance(InstanceInfo instance){

        ApplicationInstance applicationInstance = applicationInstanceService.find(instance.getAppName(), instance.getIPAddr(), instance.getPort());

        if(applicationInstance == null){
            log.info("new {} instance ({}:{})", instance.getAppName(), instance.getIPAddr(), instance.getPort());
            applicationInstance = new ApplicationInstance();
            applicationInstance.setAlarm(false);
            applicationInstance.setApp(instance.getAppName());
            applicationInstance.setHost(instance.getIPAddr());
            applicationInstance.setPort(instance.getPort());
            applicationInstance.fillId();
        }

        InstanceInfo.InstanceStatus status = instance.getStatus();
        applicationInstance.setAlive(InstanceInfo.InstanceStatus.UP.equals(status));
        applicationInstance.setDetectionTime(new Date());

        if(InstanceInfo.InstanceStatus.UP.equals(status)){
            applicationInstance.setAliveTime(new Date());
        }

        applicationInstanceService.merge(applicationInstance);
    }


    private void unknown(Application application){


        UnknownApp unknownApp = unknownAppService.find(application.getName());

        if(unknownApp == null){
            unknownApp = new UnknownApp();

            unknownApp.setCode(application.getName());
            unknownApp.setId(unknownApp.getCode());
            unknownApp.setRegisterTime(new Date());
            unknownApp.setMaxCount(0);
        }

        unknownApp.setAlive(true);

        unknownApp.setAliveCount(CollectionUtils.size(application.getInstances()));
        unknownApp.setMaxCount(NumberUtils.max(unknownApp.getMaxCount(), unknownApp.getAliveCount()));

        unknownAppService.merge(unknownApp);

    }
}
