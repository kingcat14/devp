package com.yunkang.saas.common.framework.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import com.yunkang.saas.common.framework.exception.BusinessException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 集中处理RestAPI的异常
 */
@ControllerAdvice
public class RestApiExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiExceptionHandler.class);

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleUnexpectedServerError(RuntimeException ex) {
		LOGGER.error(StringUtils.EMPTY, ex);
		return new ErrorResponse(1000, "操作失败, 请确认输入参数或联系管理员");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleInvalidRequestError(MethodArgumentNotValidException ex) {
		LOGGER.error(StringUtils.EMPTY, ex);

		List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		StringBuffer msg = new StringBuffer();
		for (ObjectError error : errorList) {
			msg.append(";").append(error.getDefaultMessage());
		}
		return new ErrorResponse(1001, msg.toString().replaceFirst(";", StringUtils.EMPTY));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		LOGGER.error(StringUtils.EMPTY, ex);
		return new ErrorResponse(1002, "输入的请求参数有误,请核实后重新输入");
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleBusinessException(BusinessException ex) {
		LOGGER.error(StringUtils.EMPTY, ex);
		return new ErrorResponse(1003, ex.getMessage());
	}

	@ExceptionHandler(HttpMediaTypeException.class)
	@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ResponseBody
	public ErrorResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeException ex) {
		LOGGER.error(StringUtils.EMPTY, ex);
		return new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage());
	}

	@ExceptionHandler(HttpStatusCodeException.class)
	@ResponseBody
	public ErrorResponse handleHttpServerErrorException(HttpStatusCodeException ex, HttpServletResponse response) {
		response.setStatus(ex.getStatusCode().value());
		LOGGER.error(StringUtils.EMPTY, ex);
		return new ErrorResponse(ex.getStatusCode().value(), ex.getResponseBodyAsString());
	}



}