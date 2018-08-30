package com.yunkang.saas.bootstrap.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.yunkang.saas.bootstrap")
@EnableJpaRepositories(basePackages = {"com.yunkang.saas.bootstrap"})
@EntityScan({"com.yunkang.saas.bootstrap"})
public class PlatformConfig {

}