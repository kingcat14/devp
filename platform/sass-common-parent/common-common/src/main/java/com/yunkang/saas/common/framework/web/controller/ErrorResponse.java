package com.yunkang.saas.common.framework.web.controller;

/**
 * Created by kingcat on 2016/11/29.
 */
public class ErrorResponse extends RestResponse{

    public ErrorResponse(){}

    public ErrorResponse(int code, String message){
        this.setCode(code);
        this.setMessage(message);
    }
}
