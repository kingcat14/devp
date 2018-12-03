package net.aicoder.speedcloud.asset.client.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.aicoder.speedcloud.asset.client")
@EnableFeignClients("net.aicoder.speedcloud.asset.client")
public class AssetClientConfig {

}