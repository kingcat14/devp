package  net.aicoder.speedcloud.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("net.aicoder.speedcloud")
@EnableJpaRepositories(basePackages = {"net.aicoder.speedcloud"})
@EntityScan({"net.aicoder.speedcloud"})
public class SpeedCloudBusinessConfig {

}