package net.aicoder.speedcloud.icode;

import com.yunkang.saas.common.framework.exception.BusinessException;
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
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

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

	private static final Logger log = LoggerFactory.getLogger(ApiResponseBodyHandler.class);

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

		ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;


		log.info("handle URI:" + request.getURI().toString());
		log.info("handle RequestURI:" + servletRequest.getServletRequest().getRequestURI());
		log.info("handle RequestURL:" + servletRequest.getServletRequest().toString());

		String uri = servletRequest.getServletRequest().getRequestURI();
		if(giveUp(uri)){
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

		log.debug(toString(result));

		return result;
	}

	public String toString(Object o) {
		return ReflectionToStringBuilder.toString(o, ToStringStyle.SHORT_PREFIX_STYLE);
	}


	public Boolean giveUp(String uri){
		Set<String> uriSet = new HashSet<>();
		uriSet.add("/health");
		uriSet.add("/env");
		uriSet.add("/loggers");
		uriSet.add("/metrics");
		uriSet.add("/dump");
		uriSet.add("/auditevents");
		uriSet.add("/trace");
		uriSet.add("/info");
		uriSet.add("/autoconfig");
		uriSet.add("/caches");
		uriSet.add("/mappings");

		return uriSet.contains(uri) || uri.startsWith("/actuator")|| uri.contains("/swagger") || uri.startsWith("/v2/api-docs");
	}

}