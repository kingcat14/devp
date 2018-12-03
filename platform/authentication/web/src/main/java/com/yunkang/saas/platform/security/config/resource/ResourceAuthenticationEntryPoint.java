package com.yunkang.saas.platform.security.config.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkang.saas.platform.security.config.oauth2.AuthenticationFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问资源时, 权限不通过的处理
 */
public class ResourceAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException, IOException {


        AuthenticationFailed authenticationFailed = new AuthenticationFailed();
        authenticationFailed.setError("401");
        authenticationFailed.setMessage(authException.getMessage());
        authenticationFailed.setPath(request.getServletPath());
        authenticationFailed.setTimestamp(System.currentTimeMillis());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, objectMapper.writeValueAsString(authenticationFailed));

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, objectMapper.writeValueAsString(authenticationFailed));

    }
}
