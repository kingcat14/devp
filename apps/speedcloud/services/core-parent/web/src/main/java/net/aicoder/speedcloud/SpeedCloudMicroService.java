package net.aicoder.speedcloud;

import com.spring4all.swagger.EnableSwagger2Doc;
import com.yunkang.saas.common.framework.web.controller.ApiResponseBodyHandler;
import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Import({ApiResponseBodyHandler.class, RestApiExceptionHandler.class})
@EnableSwagger2Doc
@SpringBootApplication()
@EnableScheduling
public class SpeedCloudMicroService implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(SpeedCloudMicroService.class, args);

	}

	@Override
	public int getExitCode() {
		return 0;
	}

}