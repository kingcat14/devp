package  net.aicoder.speedcloud.icode.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.aicoder.speedcloud.icode.client")
@EnableFeignClients("net.aicoder.speedcloud.icode.client")
public class IcodeClientConfig {

}