package net.aicoder.devp.maintenance.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"net.aicoder.devp.maintenance"})
@EntityScan({"net.aicoder.devp.maintenance"})
public class BusinessConfig {

}
