package com.yunkang.saas.common.framework.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gonghongrui on 2018/4/18.
 */
public class RestResponse<T> {

	private Integer code;

	private String message;

	private T data;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@JsonIgnore
	public boolean isSuccess(){
		return this.code == 0;
	}
}
