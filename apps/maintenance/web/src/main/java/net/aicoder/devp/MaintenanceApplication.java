package net.aicoder.devp;

import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Import({RestApiExceptionHandler.class})
@SpringBootApplication()
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
}