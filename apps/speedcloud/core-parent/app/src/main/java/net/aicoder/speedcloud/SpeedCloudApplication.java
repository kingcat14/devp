package net.aicoder.speedcloud;

import com.yunkang.saas.common.framework.web.controller.TypedApiResponseBodyHandler;
import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({TypedApiResponseBodyHandler.class, RestApiExceptionHandler.class})
@EnableRedisHttpSession
@EnableSwagger2Doc
@SpringBootApplication()
public class SpeedCloudApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(SpeedCloudApplication.class, args);

	}

	@Override
	public int getExitCode() {
		return 0;
	}

}