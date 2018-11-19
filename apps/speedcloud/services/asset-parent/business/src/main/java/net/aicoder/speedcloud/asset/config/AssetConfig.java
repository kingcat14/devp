package net.aicoder.speedcloud.asset.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("net.aicoder.speedcloud.asset")
@EnableJpaRepositories(basePackages = {"net.aicoder.speedcloud.asset"})
@EntityScan({"net.aicoder.speedcloud.asset"})
public class AssetConfig {

}