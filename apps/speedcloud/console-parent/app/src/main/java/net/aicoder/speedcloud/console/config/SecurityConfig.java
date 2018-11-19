package  net.aicoder.speedcloud.console.config;

import com.yunkang.saas.bootstrap.monitor.config.MonitorConfig;
import com.yunkang.saas.bootstrap.security.local.config.LocalSecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({LocalSecurityAutoConfiguration.class, MonitorConfig.class})
public class SecurityConfig {

}