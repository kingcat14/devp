package net.aicoder.speedcloud.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.spring4all.swagger.EnableSwagger2Doc;
import com.yunkang.saas.bootstrap.security.local.config.LocalSecurityAutoConfiguration;
import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import com.yunkang.saas.common.framework.web.controller.TypedApiResponseBodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

@Import({TypedApiResponseBodyHandler.class, RestApiExceptionHandler.class, LocalSecurityAutoConfiguration.class})
@SpringBootApplication()
@EnableRedisHttpSession
@EnableCircuitBreaker
@EnableSwagger2Doc
public class SpeedCloudConsoleApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(SpeedCloudConsoleApplication.class, args);
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