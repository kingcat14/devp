package com.yunkang.saas.bootstrap.application.config;

import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;
import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.common.config.CommonConfig;
import com.yunkang.saas.bootstrap.platform.config.PlatformManageBusinessConfig;
import com.yunkang.saas.common.framework.app.ApplicationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.yunkang.saas.bootstrap.application")
@EnableJpaRepositories(basePackages = {"com.yunkang.saas.bootstrap.application"})
@EntityScan({"com.yunkang.saas.bootstrap.application"})
@Import({CommonConfig.class, PlatformManageBusinessConfig.class})
public class BootstrapApplicationConfig {

    @ConditionalOnMissingBean
    @Bean
    public SecurityUtil securityUtil(){
        return new SecurityUtil(){

            @Override
            public Account getAccount() {

                Account account = new Account();
                account.setName("开发中使用的默认用户");
                account.setNickName("除了开发环境, 你绝对不应该看到我");
                account.setId(-1L);
                account.setTid(-1L);

                return null;
            }
        };
    }


    @Bean
    @ConfigurationProperties(prefix = "application")
    public ApplicationProperties applicationProperties(){
        return new ApplicationProperties();
    }

}
