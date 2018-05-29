package com.yunkang.saas.common.framework.exception;

/**
 * 没有登录
 */
public class NotLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final static String MSG = "没有登录";

	
	public NotLoginException() {
		super(MSG);
	}

	public NotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotLoginException(String message) {
		super(message);
	}

	public NotLoginException(Throwable cause) {
		super(cause);
	}

}
