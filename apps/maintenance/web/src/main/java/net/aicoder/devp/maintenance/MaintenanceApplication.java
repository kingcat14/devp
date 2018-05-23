package net.aicoder.devp.maintenance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Import({RestApiExceptionHandler.class})
@SpringBootApplication(scanBasePackages={"net.aicoder.devp.security","net.aicoder.devp.maintenance"
		,"net.aicoder.devp.product.business.product.client"})
public class MaintenanceApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceApplication.class, args);

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
	public void a(ObjectMapper objectMapper){
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
	}



}