package net.aicoder.speedcloud.console.config;


import com.yunkang.saas.bootstrap.config.client.feign.Oauth2ClientConfig;
import com.yunkang.saas.platform.manage.config.PlatformManageAppConfig;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({ConsoleBusinessConfig.class, PlatformManageAppConfig.class, Oauth2ClientConfig.class})
@EnableFeignClients("net.aicoder.speedcloud.console.config")
public class SpeedCloudConfig {

}