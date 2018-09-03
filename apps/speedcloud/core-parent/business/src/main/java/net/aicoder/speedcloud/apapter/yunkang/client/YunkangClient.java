package net.aicoder.speedcloud.apapter.yunkang.client;

import net.aicoder.speedcloud.apapter.yunkang.CreateJobAction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YunkangClient {

    RestTemplate restTemplate = new RestTemplate();

    String yunkangHost = "10.10.40.234:8181";

    public Result create(CreateJobAction createJobAction){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job";
        return restTemplate.postForObject(url, createJobAction, Result.class);
    }
}
