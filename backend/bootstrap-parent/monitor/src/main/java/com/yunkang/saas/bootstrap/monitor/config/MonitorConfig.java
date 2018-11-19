package com.yunkang.saas.bootstrap.monitor.config;

import com.yunkang.saas.bootstrap.monitor.endpoint.BusinessEndpoint;
import com.yunkang.saas.bootstrap.monitor.statistics.BusinessStatisticsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.yunkang.saas.bootstrap.monitor")
public class MonitorConfig {

    @Bean
    @ConditionalOnMissingBean(BusinessEndpoint.class)
    BusinessEndpoint businessEndpoint(){
        return new BusinessEndpoint();
    }

    @Bean
    @ConditionalOnMissingBean(BusinessStatisticsService.class)
    BusinessStatisticsService businessStatisticsService(){
        return new BusinessStatisticsService();
    }

}
