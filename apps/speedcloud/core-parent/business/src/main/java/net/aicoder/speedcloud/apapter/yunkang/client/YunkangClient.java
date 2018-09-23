package net.aicoder.speedcloud.apapter.yunkang.client;

import net.aicoder.speedcloud.apapter.yunkang.client.dto.CreateJobAction;
import net.aicoder.speedcloud.apapter.yunkang.client.dto.ExecParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class YunkangClient {

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    String yunkangHost = "10.10.40.234:8181";

    public Result create(CreateJobAction createJobAction){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job";
        return restTemplate.postForObject(url, createJobAction, Result.class);
    }

    public Result delete(String jobName){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job?jobName="+jobName;
        ResponseEntity<Result> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<Result>() {});
        return response.getBody();
    }

    public Result update(CreateJobAction createJobAction){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job";
        return restTemplate.patchForObject(url, createJobAction, Result.class);
    }

    public Result exec(String jobId, List<ExecParam> execParamList){


        String url = "http://"+yunkangHost+"/api/job-admin/v1/job/build/"+jobId;
        //
        if(CollectionUtils.isNotEmpty(execParamList)){
            url = "http://"+yunkangHost+"/api/job-admin/v1/job/build/"+jobId+"/param";
        }
        return restTemplate.postForObject(url, execParamList, Result.class);

    }
}
