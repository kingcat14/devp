package net.aicoder.speedcloud.icode;

import com.spring4all.swagger.EnableSwagger2Doc;
import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import({ApiResponseBodyHandler.class, RestApiExceptionHandler.class})
@EnableSwagger2Doc
@SpringBootApplication()
@EnableTransactionManagement
public class SpeedCloudIcodeMicroService implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(SpeedCloudIcodeMicroService.class, args);
	}

	@Override
	public int getExitCode() {
		return 0;
	}


}