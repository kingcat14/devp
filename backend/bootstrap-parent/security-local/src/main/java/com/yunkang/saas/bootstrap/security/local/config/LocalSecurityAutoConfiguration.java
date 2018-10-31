package com.yunkang.saas.bootstrap.security.local.config;

import com.yunkang.saas.bootstrap.application.config.BootstrapApplicationConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ConditionalOnClass({ AuthorServer.class })
//@EnableConfigurationProperties(AuthorProperties.class)
@Import({WebSecurityConfig.class , BootstrapApplicationConfig.class})
@ComponentScan("com.yunkang.saas.bootstrap.security")
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