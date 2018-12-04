package com.yunkang.saas.platform.monitor.business.collector.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.domain.UnknownApp;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.app.service.UnknownAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 采集应用的状态
 */
@Component
@Slf4j
public class ApplicationStatusCollectService {


    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationInstanceService applicationInstanceService;

    @Autowired
    private UnknownAppService unknownAppService;

    @Autowired
    private ApplicationStatusUpdater applicationStatusUpdater;

//    @Scheduled(cron = "*/30 * * * * *")
    @Transactional
    public void detection(){
        /*
         * 0.捞出应用列表
         * 1.判断是否在需要监控的应用中
         * 2.在的话，开始处理信息
         * 3.不在的话，放入未知应用处理
         * 4.捞出所有要监控的应用，判断哪些挂了
         * 5.捞出所有未监控的应用，判断哪些挂了
         */
        //0.
        Applications apps = eurekaClient.getApplications();

        List<Application> applicationList = apps.getRegisteredApplications();

        for(Application application : applicationList){
            applicationStatusUpdater.update(application);
        }

        //4.
        filterDownApplication(applicationList);

        //5.
        filterDownUnknownApplication(applicationList);
    }

    /**
     * 判断挂了的应用
     * @param applicationList 注册中心里的应用列表
     */
    private void filterDownApplication(List<Application> applicationList){

        /*
         *
         * 1.注册中心有的应用，处理实例
         * 2.判断注册中心没有的应用，所有的实例都修改已经挂了
         */
        Set<String> appNames = new HashSet<>();

        for(Application application : applicationList){
            appNames.add(application.getName());
            filterDownApplicationInstance(application);
        }

        applicationService.markOtherAppDead(appNames);

    }

    private void filterDownApplicationInstance(Application application){

        //捞出所有活的
        List<ApplicationInstance> applicationInstanceList = applicationInstanceService.find(application.getName(), true);

        Set<String> names = new HashSet<>();
        for(InstanceInfo info : application.getInstances()){
            names.add(info.getAppName()+"-"+info.getIPAddr()+"-"+info.getPort());
        }

        for(ApplicationInstance instance : applicationInstanceList){
            String target = instance.getApp()+"-"+instance.getHost()+"-"+instance.getPort();
            instance.setAlive(names.contains(target));
            applicationInstanceService.merge(instance);
        }

    }


    /**
     * 判断挂了的未监控应用
     * @param applicationList
     */
    private void filterDownUnknownApplication(List<Application> applicationList){
        Set<String> name = new HashSet<>();
        for(Application application : applicationList){
            name.add(application.getName());
        }

        List<UnknownApp> unknownAppList = unknownAppService.findAll(null);
        for(UnknownApp unknownApp : unknownAppList){

            unknownApp.setAlive(name.contains(unknownApp.getCode()));
        }
        unknownAppService.merge(unknownAppList);

    }

}
