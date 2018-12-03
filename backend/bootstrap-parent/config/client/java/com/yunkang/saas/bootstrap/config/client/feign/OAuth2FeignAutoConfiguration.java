package com.yunkang.saas.bootstrap.config.client.feign;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

//@Configuration
public class OAuth2FeignAutoConfiguration {

//    @Autowired()
//    private OAuth2RestTemplate oAuth2RestTemplate;

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(@Qualifier("saasOAuth2RestTemplate") OAuth2RestTemplate oAuth2RestTemplate) {
        return new OAuth2FeignRequestInterceptor(oAuth2RestTemplate);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder.Default();
    }

//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }


    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}