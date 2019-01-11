package  com.yunkang.saas.platform.services.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.yunkang.saas.platform.services.core")
@EnableJpaRepositories(basePackages = {"com.yunkang.saas.platform.services.core"})
@EntityScan({"com.yunkang.saas.platform.services.core"})
public class CoreBusinessConfig {

}