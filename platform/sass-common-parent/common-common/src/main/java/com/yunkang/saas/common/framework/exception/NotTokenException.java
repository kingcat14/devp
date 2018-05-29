package com.yunkang.saas.common.framework.exception;

/**
 * 请求无令牌
 */
public class NotTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final static String MSG = "请求无令牌";
	
	public NotTokenException() {
		super(MSG);
	}

	public NotTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotTokenException(String message) {
		super(message);
	}

	public NotTokenException(Throwable cause) {
		super(cause);
	}

}
