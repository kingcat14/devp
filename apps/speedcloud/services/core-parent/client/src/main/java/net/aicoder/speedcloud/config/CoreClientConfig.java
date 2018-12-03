package net.aicoder.speedcloud.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.aicoder.speedcloud.client")
@EnableFeignClients("net.aicoder.speedcloud.client")
public class CoreClientConfig {

}