package com.yunkang.saas.bootstrap.platform.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.yunkang.saas.bootstrap.platform")
@EnableJpaRepositories(basePackages = {"com.yunkang.saas.bootstrap.platform"})
@EntityScan({"com.yunkang.saas.bootstrap.platform"})
public class PlatformConfig {

}