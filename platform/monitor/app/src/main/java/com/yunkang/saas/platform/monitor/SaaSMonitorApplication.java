package com.yunkang.saas.platform.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yunkang.saas.platform.monitor.business.notification.mail.MailService;
import de.codecentric.boot.admin.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
@Configuration
@EnableAdminServer
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableAsync
@Slf4j
public class SaaSMonitorApplication implements ExitCodeGenerator {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(SaaSMonitorApplication.class, args);

//		EurekaClient eurekaClient = ctx.getBean(EurekaClient.class);
//		Applications apps = eurekaClient.getApplications();
//		List<Application> applicationList = apps.getRegisteredApplications();
//		for(Application application : applicationList){
//			log.info("application: {}", application.getName());
//			List<InstanceInfo> instanceInfoList = application.getInstances();
//			for(InstanceInfo info : instanceInfoList){
//				log.info("{} url : {}:{}", info.getAppName(), info.getIPAddr(), info.getPort());
//			}
//		}

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo("hongruigong@qq.com");
		simpleMailMessage.setSubject("监控程序启动啦");
		simpleMailMessage.setText(new Date().toString());

		MailService mailService = ctx.getBean(MailService.class);
		mailService.send(simpleMailMessage);
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