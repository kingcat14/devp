package  net.aicoder.speedcloud.console.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("net.aicoder.speedcloud.client")
@Import(ConsoleBusinessConfig.class)
public class SpeedCloudConfig {

}