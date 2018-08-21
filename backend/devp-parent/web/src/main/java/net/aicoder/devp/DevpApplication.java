package net.aicoder.devp;

import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import com.yunkang.saas.common.framework.web.controller.TypedApiResponseBodyHandler;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({TypedApiResponseBodyHandler.class, RestApiExceptionHandler.class})
@SpringBootApplication()
public class DevpApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(DevpApplication.class, args);

	}

	@Override
	public int getExitCode() {
		return 0;
	}

}