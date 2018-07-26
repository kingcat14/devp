package net.aicoder.devp.maintenance.config;

import java.lang.reflect.Method;

import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.yunkang.saas.common.framework.exception.BusinessException;

/**
 * Created by dvnuo on 2017/6/14.
 */
public

/**
 * 返回Body处理
 */
@Configuration
// @ControllerAdvice(annotations = { Api.class })
@ControllerAdvice class ApiResponseBodyHandler implements ResponseBodyAdvice<Object> {

	private static final Logger logger = LoggerFactory.getLogger(ApiResponseBodyHandler.class);

	private static final String STARTS_WITH_SWAGGER = "springfox.documentation.swagger";

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		Method method = returnType.getMethod();
		if (method.getDeclaringClass().getName().startsWith(STARTS_WITH_SWAGGER)) {
			return false;
		}
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object object, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		//不包含RichClient的时候，直接返回;包含RichClient的时候封装
		if(!request.getHeaders().containsKey("RichClient")){
			return object;
		}


		RestResponse result = new RestResponse();

		if (object == null) { // 若返回内容为空的时候，将空Map作为返回对象
			object = result;
			result.setCode(0);
			result.setMessage("执行成功");
		}

		if (object instanceof RestResponse) {
			result = (RestResponse) object;
		} else if (object instanceof BusinessException) {
			BusinessException businessException = (BusinessException) object;
			result.setCode(RestStatus.ERROR.getCode());
			result.setMessage(businessException.getMessage());
		} else {
			result.setCode(0);
			result.setMessage("执行成功");
			result.setData(object);
		}

		logger.debug(toString(result));

		return result;
	}

	public String toString(Object o) {
		return ReflectionToStringBuilder.toString(o, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}