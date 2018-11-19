package net.aicoder.speedcloud.console.config;


import com.yunkang.saas.platform.manage.config.PlatformManageAppConfig;
import net.aicoder.speedcloud.asset.client.asset.AssetClientConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("net.aicoder.speedcloud.client")
@Import({ConsoleBusinessConfig.class,AssetClientConfig.class, PlatformManageAppConfig.class})

public class SpeedCloudConfig {

}