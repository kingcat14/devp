package com.yunkang.saas.platform.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import de.codecentric.boot.admin.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
@Slf4j
public class MonitorConsoleApplication implements ExitCodeGenerator {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(MonitorConsoleApplication.class, args);

		EurekaClient eurekaClient = ctx.getBean(EurekaClient.class);
		Applications apps = eurekaClient.getApplications();
		List<Application> applicationList = apps.getRegisteredApplications();
		for(Application application : applicationList){
			log.info("application: {}", application.getName());
			List<InstanceInfo> instanceInfoList = application.getInstances();
			for(InstanceInfo info : instanceInfoList){
				log.info("{} url : {}:{}", info.getAppName(), info.getIPAddr(), info.getPort());
			}
		}
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