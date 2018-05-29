package com.yunkang.saas.common.framework.exception;

/**
 * 数据不存在
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final static String MSG = "数据不存在";
	
	public ResourceNotFoundException() {
		super(MSG);
	}

	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

}
