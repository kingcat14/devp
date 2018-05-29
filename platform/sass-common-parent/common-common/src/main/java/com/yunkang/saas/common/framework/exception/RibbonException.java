package com.yunkang.saas.common.framework.exception;

/**
 * 访问远程服务异常
 */
public class RibbonException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String module;
	private String business;
	private String errorCode;


	public RibbonException(String serviceName, String errorMessage){
		super(errorMessage);

		this.module = "MICRO_SERVICE";
		this.business = serviceName;
		this.errorCode = "REST_ERROR";

	}


	@Override
	public String toString(){
		return module+"-"+business+"-"+errorCode+":"+this.getMessage();
	}
}
