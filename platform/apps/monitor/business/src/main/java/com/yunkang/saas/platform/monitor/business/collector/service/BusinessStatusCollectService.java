package com.yunkang.saas.platform.monitor.business.collector.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.yunkang.saas.bootstrap.monitor.statistics.BusinessIndicator;
import com.yunkang.saas.bootstrap.monitor.statistics.BusinessSeq;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorValueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * 捞取实例的状态
 */
@Slf4j
@Component
public class BusinessStatusCollectService {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationInstanceService applicationInstanceService;

    @Autowired
    private BusinessClient businessClient;

    @Autowired
    private IndicatorValueService indicatorValueService;

//    @Scheduled(cron = "1 * * * * *")
    public void aaa(){

        Applications apps = eurekaClient.getApplications();

        List<Application> applicationList = apps.getRegisteredApplications();

        //没有这个应用, 则不处理
        for(Application application : applicationList){
            if (!applicationService.contain(application.getName())){
                continue;
            }

            application(application);
        }

    }


    private void application(Application application){

        log.info("application: {}", application.getName());

        List<InstanceInfo> instanceInfoList = application.getInstances();

        for(InstanceInfo info : instanceInfoList){
            log.info("{}", info.getMetadata());
            instance(info, "actuator", "123456");
        }
    }

    private void instance(InstanceInfo info, String username, String password){
        String url ="http://%s:%s//actuator/business";
        String target = String.format(url, info.getIPAddr(), info.getPort());
        List<BusinessSeq> businessSeqs = businessClient.get(target, username, password);
        ApplicationInstance instance = applicationInstanceService.find(info.getAppName(), info.getIPAddr(), info.getPort());
        for(BusinessSeq seq : businessSeqs){
            businessSeq(instance,seq);
        }
    }

    private void businessSeq(ApplicationInstance instance, BusinessSeq businessSeq){

        log.info(businessSeq.toString());

        Queue<BusinessIndicator> queue = businessSeq.getQueue();

        int size = queue.size();
        for(int i = 0; i< size; i++){
            BusinessIndicator businessIndicator = queue.remove();
            IndicatorValue occur = makeOccur(businessIndicator, businessSeq.getCode(), instance.getId());
            indicatorValueService.merge(occur);
            IndicatorValue error = makeOccur(businessIndicator, businessSeq.getCode(), instance.getId());
            indicatorValueService.merge(error);
        }


    }

    private IndicatorValue makeOccur(BusinessIndicator businessIndicator, String code, String instanceId){

        IndicatorValue value = new IndicatorValue();
        value.setIndicator(code+".occur");
        value.setValue(businessIndicator.getCount()+"");
        value.setTarget(instanceId);
        value.setTargetType("INSTANCE");

        try {
            value.setCollectTime(DateUtils.parseDate(businessIndicator.getDateTime(), new String[]{"yyyyMMddHHmm"}));
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        value.setSaveTime(new Date());

        value.setId(Md5Crypt.apr1Crypt((value.getIndicator()+value.getCollectTime()).getBytes(StandardCharsets.UTF_8), "1"));

        return value;
    }

    private IndicatorValue makeError(BusinessIndicator businessIndicator, String code, String instanceId){

        IndicatorValue value = new IndicatorValue();
        value.setIndicator(code+".error");
        value.setValue(businessIndicator.getCount()+"");
        value.setTarget(instanceId);
        value.setTargetType("INSTANCE");

        try {
            value.setCollectTime(DateUtils.parseDate(businessIndicator.getDateTime(), new String[]{"yyyyMMddHHmm"}));
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        value.setSaveTime(new Date());

        value.setId(Md5Crypt.apr1Crypt((value.getIndicator()+value.getCollectTime()).getBytes(StandardCharsets.UTF_8), "1"));

        return value;
    }






}
