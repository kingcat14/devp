package net.aicoder.speedcloud.console.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("net.aicoder.speedcloud.console")
@EnableJpaRepositories(basePackages = {"net.aicoder.speedcloud.console"})
@EntityScan({"net.aicoder.speedcloud.console"})
public class ConsoleBusinessConfig {

}