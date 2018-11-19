package net.aicoder.speedcloud.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("net.aicoder.speedcloud")
@EnableJpaRepositories(basePackages = {"net.aicoder.speedcloud"})
@EntityScan({"net.aicoder.speedcloud"})
public class SpeedCloudBusinessConfig {

}