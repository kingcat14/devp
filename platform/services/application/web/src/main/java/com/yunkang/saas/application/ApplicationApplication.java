package com.yunkang.saas.application;

import com.yunkang.saas.common.framework.web.controller.ApiResponseBodyHandler;
import com.yunkang.saas.common.framework.web.controller.RestApiExceptionHandler;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ApiResponseBodyHandler.class, RestApiExceptionHandler.class})
@SpringBootApplication()
public class ApplicationApplication implements ExitCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationApplication.class, args);

	}

	@Override
	public int getExitCode() {
		return 0;
	}

}