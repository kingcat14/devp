package com.yunkang.saas.bootstrap.config.client.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@Configuration
@ConditionalOnProperty(name = "security.oauth2.client.access-token-uri", matchIfMissing = false)
//@EnableConfigurationProperties(Oauth2ClientProperties.class)
public class Oauth2ClientConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.client-authentication-scheme}")
    private AuthenticationScheme authenticationScheme;

    @Bean
    public ClientCredentialsResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setId(applicationName);
        details.setAccessTokenUri(accessTokenUri);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAuthenticationScheme(authenticationScheme);
        return details;
    }

    @Bean("saasOAuth2RestTemplate")
    public OAuth2RestTemplate oAuth2RestTemplate(ClientCredentialsResourceDetails clientCredentialsResourceDetails) {
        final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
        oAuth2RestTemplate.setRequestFactory(new Netty4ClientHttpRequestFactory());

        return oAuth2RestTemplate;

    }
}