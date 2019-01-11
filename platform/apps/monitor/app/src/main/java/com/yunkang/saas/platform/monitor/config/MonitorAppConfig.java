package com.yunkang.saas.platform.monitor.config;

import com.yunkang.saas.platform.manage.config.PlatformManageAppConfig;
import de.codecentric.boot.admin.config.NotifierConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PlatformManageAppConfig.class})
public class MonitorAppConfig {



    @Configuration
    @AutoConfigureBefore({ NotifierConfiguration.NotifierListenerConfiguration.class,
            NotifierConfiguration.CompositeNotifierConfiguration.class })
    public static class DBNotifierConfiguration {
//        @Bean
//        @ConditionalOnMissingBean
//        public DbNotifier dbNotifier() {
//            return new DbNotifier();
//        }
    }
}