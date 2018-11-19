package com.yunkang.saas.platform.monitor.business.collector.service;

import com.yunkang.saas.bootstrap.monitor.statistics.BusinessSeq;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class BusinessClient {

    RestTemplate restTemplate = new RestTemplate();


    @PostConstruct
    public void init(){

        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        });
    }

    public List<BusinessSeq> get(String url, String username, String password){

        HttpEntity<String> request = new HttpEntity<>(getHeaders(username, password));

        ResponseEntity<List<BusinessSeq>> response = restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<BusinessSeq>>(){});



        List<BusinessSeq> result = null;

        result = response.getBody();
        return result;
    }


    private static HttpHeaders getHeaders(String username, String password){
        String plainCredentials=username+":"+password;
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));


        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
