package com.yunkang.saas.common.framework.exception;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final static String MSG = "访问受限";

	public ForbiddenException() {
		super(MSG);
	}

	public ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForbiddenException(String message) {
		super(message);
	}

	public ForbiddenException(Throwable cause) {
		super(cause);
	}

}
