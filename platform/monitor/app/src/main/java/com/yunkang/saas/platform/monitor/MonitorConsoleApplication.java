package com.yunkang.saas.platform.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@EnableDiscoveryClient
public class MonitorConsoleApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(MonitorConsoleApplication.class, args);

	}

	@Override
	public int getExitCode() {
		return 0;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		//不这么写，不能用PATCH方法
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

	@ConditionalOnClass(ObjectMapper.class)
	@Autowired
	public void convertLongToString(ObjectMapper objectMapper){
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
	}

}