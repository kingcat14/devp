package com.yunkang.saas.platform.discovery;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class SaaSDiscoveryApplication implements ExitCodeGenerator {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(SaaSDiscoveryApplication.class, args);

//
	}

	@Override
	public int getExitCode() {
		return 0;
	}


}