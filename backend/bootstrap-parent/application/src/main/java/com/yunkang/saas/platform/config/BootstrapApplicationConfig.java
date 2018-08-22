package com.yunkang.saas.platform.config;

import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import com.yunkang.saas.platform.business.platform.security.domain.Account;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootstrapApplicationConfig {

    @ConditionalOnMissingBean @Bean
    public SecurityUtil saaSUtil(){
        return new SecurityUtil(){

            @Override
            public Account getAccount() {

                Account account = new Account();
                account.setName("开发中使用的用户默认用户");
                account.setNickName("除了开发环境, 你绝对不应该看到我");
                account.setId(1L);
                account.setTenantId(-1L);

                return null;
            }
        };
    }

}
