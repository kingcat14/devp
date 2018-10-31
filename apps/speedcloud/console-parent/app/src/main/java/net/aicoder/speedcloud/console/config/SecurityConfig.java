package  net.aicoder.speedcloud.console.config;

import com.yunkang.saas.bootstrap.security.local.config.LocalSecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({LocalSecurityAutoConfiguration.class})
public class SecurityConfig {

}