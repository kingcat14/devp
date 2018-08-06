package com.yunkang.saas.security.local.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ConditionalOnClass({ AuthorServer.class })
//@EnableConfigurationProperties(AuthorProperties.class)
@Import(WebSecurityConfig.class)
@ComponentScan("com.yunkang.saas.security.local")
public class LocalSecurityAutoConfiguration {

//    @Resource
//    private AuthorProperties authorProperties;

//    @Bean
//    @ConditionalOnMissingBean(AuthorServer.class)
//    @ConditionalOnProperty(name = "custom.author.enabled", matchIfMissing = true)
//    public AuthorServer authorResolver() {
//        AuthorServer authorServer = new AuthorServer();
//        authorServer.setAuthor(authorProperties.getAuthor());
//        return authorServer;
//    }
}