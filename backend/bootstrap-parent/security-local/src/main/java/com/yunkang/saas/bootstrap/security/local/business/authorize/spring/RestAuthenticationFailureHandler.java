package com.yunkang.saas.bootstrap.security.local.business.authorize.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {


        String username = request.getParameter("username");

        log.info("user({}) authentication fail", username);

        logger.debug("No failure URL set, sending 401 Unauthorized error");

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication456 Failed: " + exception.getMessage());

        super.onAuthenticationFailure(request, response, exception);

    }
}
