package com.yunkang.saas.platform.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * 授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }


    @Autowired
    private TokenStore tokenStore;
    /**
     * 认证管理器
     * 注入authenticationManager 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ClientDetailsService clientDetailsService;
//    @Autowired
//    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    /**
     * 资源id
     */
    @Value("${resource.id:spring-boot-application}")
    private String resourceId;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints.tokenStore(tokenStore);
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);

        //用来映射默认url和自定义的url,参考org.springframework.security.oauth2.provider.endpoint包里的类定义的url
        //endpoints.pathMapping("/oauth/check_token", "/oauth/check_token");


//        endpoints.exceptionTranslator(webResponseExceptionTranslator);
    }

    /**
     * 配置应用名称 应用id
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
//                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

//    @Configuration
//    @EnableResourceServer
//    @Order(1)
//    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//            resources.resourceId("resource-id");
//        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.requestMatcher(new OAuthRequestedMatcher())
//                    .authorizeRequests().anyRequest().fullyAuthenticated();
//
//        }
//    }
//    private static class OAuthRequestedMatcher implements RequestMatcher {
//        public boolean matches(HttpServletRequest request) {
//            String auth = request.getHeader("Authorization");
//            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
//            boolean haveAccessToken = request.getParameter("access_token")!=null;
//            return haveOauth2Token || haveAccessToken;
//        }
//    }
}


