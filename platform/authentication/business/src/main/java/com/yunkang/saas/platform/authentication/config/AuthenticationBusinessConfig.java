package  com.yunkang.saas.platform.authentication.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.yunkang.saas.platform.authentication")
@EnableJpaRepositories(basePackages = {"com.yunkang.saas.platform.authentication"})
@EntityScan({"com.yunkang.saas.platform.authentication"})
public class AuthenticationBusinessConfig {

}