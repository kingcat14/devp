package com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 微服务接口
 * @author icode
 */
public class MicroServiceItfcAddRequest {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_METHOD = "method";
	public static final String PROPERTY_MICRO_SERVICE_ID = "microServiceId";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_RESPONSE_ID = "responseId";
	public static final String PROPERTY_REQUEST_ID = "requestId";

    /**
	 * 接口名称
	 * 
     */
	@NotNull(message = "接口名称不能为空")
	@Size(max = 255, message = "接口名称超长，最多255个字符")
	private String name;

    /**
	 * 接口代码
	 * 对应到方法名
     */
	@NotNull(message = "接口代码不能为空")
	@Size(max = 255, message = "接口代码超长，最多255个字符")
	private String code;

    /**
	 * 接口地址
	 * 
     */
	@NotNull(message = "接口地址不能为空")
	@Size(max = 255, message = "接口地址超长，最多255个字符")
	private String url;

    /**
	 * 接口方法
	 * POST, PUT, GET......
     */
	@NotNull(message = "接口方法不能为空")
	@Size(max = 255, message = "接口方法超长，最多255个字符")
	private String method;

    /**
	 * 所属微服务
	 * 
     */
	@NotNull(message = "所属微服务不能为空")
	private String microServiceId;
	private String microServiceName;

    /**
	 * 接口描述
	 * 
     */
	private String description;

    /**
	 * 返回对象
	 * 
     */
	private String responseId;
	private String responseName;

    /**
	 * 请求对象
	 * 
     */
	private String requestId;
	private String requestName;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod(){
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public String getMicroServiceId(){
		return microServiceId;
	}
	public void setMicroServiceId(String microServiceId) {
		this.microServiceId = microServiceId;
	}
	public String getMicroServiceName(){
		return microServiceName;
	}
	public void setMicroServiceName(String microServiceName) {
		this.microServiceName = microServiceName;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponseId(){
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	public String getResponseName(){
		return responseName;
	}
	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}

	public String getRequestId(){
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestName(){
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
