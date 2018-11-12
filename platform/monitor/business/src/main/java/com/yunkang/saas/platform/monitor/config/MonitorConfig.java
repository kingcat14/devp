package  com.yunkang.saas.platform.monitor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.yunkang.saas.platform.monitor")
@EnableJpaRepositories(basePackages = {"com.yunkang.saas.platform.monitor"})
@EntityScan({"com.yunkang.saas.platform.monitor"})
public class MonitorConfig {

}