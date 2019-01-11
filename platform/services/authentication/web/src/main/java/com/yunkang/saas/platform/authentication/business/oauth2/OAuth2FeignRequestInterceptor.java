package com.yunkang.saas.platform.authentication.business.oauth2;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.util.Assert;


@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {



    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    private final OAuth2RestTemplate oAuth2RestTemplate;

    public OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
        Assert.notNull(oAuth2RestTemplate, "Context can not be null");
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public void apply(RequestTemplate template) {
        log.debug("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
        template.header(AUTHORIZATION_HEADER,
                String.format("%s %s",
                        BEARER_TOKEN_TYPE,
                        oAuth2RestTemplate.getAccessToken().toString()));

    }

}