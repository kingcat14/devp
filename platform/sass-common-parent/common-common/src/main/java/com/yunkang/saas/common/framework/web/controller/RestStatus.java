package com.yunkang.saas.common.framework.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum RestStatus {

    SUCCESS(0),
    ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常"),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "未授权访问"),
    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "禁止访问"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "内容不存在"),
    SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常");

    Integer code;
    String msg;

    RestStatus(Integer code){
        this.code = code;
        switch (code){
            case 0 : this.msg = "执行成功" ;break;
            case -1 : this.msg = "系统繁忙，请稍后再试" ;break;
            default: this.msg = "系统未定义";
        }
    }

    RestStatus(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
