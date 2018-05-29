package com.yunkang.saas.common.framework.exception;

/**
 * 参数错误
 */
public class ParamIllegalException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final static String MSG = "参数错误";

	public ParamIllegalException() {
		super(MSG);
	}

	public ParamIllegalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParamIllegalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamIllegalException(String message) {
		super(message);
	}

	public ParamIllegalException(Throwable cause) {
		super(cause);
	}

}
