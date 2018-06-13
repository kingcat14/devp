package net.aicoder.devp.deploy;

import com.yunkang.saas.common.framework.web.controller.ApiResponseBodyHandler;
import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ApiResponseBodyHandler.class, RestApiExceptionHandler.class})
@SpringBootApplication()
public class DeployApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(DeployApplication.class, args);

	}

	@Override
	public int getExitCode() {
		return 0;
	}

}