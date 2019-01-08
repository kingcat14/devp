package net.aicoder.speedcloud.console.config;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Test {


    @Autowired
    private EurekaClient eurekaClient;

    @Scheduled(cron = "*/5 * * * * MON-FRI")
    private void a(){
        String id = eurekaClient.getApplicationInfoManager().getInfo().getId();
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();

        for(Application application : applications){
            List<InstanceInfo> instances =  application.getInstancesAsIsFromEureka();
            for(InstanceInfo instanceInfo : instances){
                if(StringUtils.equals(id, instanceInfo.getId())){
                    continue;
                }
                System.out.println(instanceInfo.getMetadata());
            }
        }
    }
}