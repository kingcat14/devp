package com.kingzoo.kingcat.project.icode4.httpclient;

import org.apache.hadoop.hdfs.web.JsonUtil;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class AAA {
    @Test
    public void a(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("RichClient", "1");
        headers.add("Cache-Control", "no-cache");
        headers.add("Cookie", "JSESSIONID="+"6f27ee39-d88d-4454-965a-b0f1502a5e56");
        String body = "{\"searchCondition\":{},\"start\":0,\"limit\":10}";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        restTemplate.postForObject("http://183.63.91.140:11202/product/devpPrdLinePrd/list", requestEntity, String.class);
    }

}
