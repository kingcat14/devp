package com.yunkang.saas.common.framework.exception;

/**
 * 服务异常
 */
public class ParamNullPointerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final static String MSG = "参数不能为空";
	
	public ParamNullPointerException() {
		super(MSG);
	}

	public ParamNullPointerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParamNullPointerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamNullPointerException(String message) {
		super(message);
	}

	public ParamNullPointerException(Throwable cause) {
		super(cause);
	}

}
