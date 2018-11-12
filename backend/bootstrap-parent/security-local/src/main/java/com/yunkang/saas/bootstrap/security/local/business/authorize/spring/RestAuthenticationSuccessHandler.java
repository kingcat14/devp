package com.yunkang.saas.bootstrap.security.local.business.authorize.spring;

import com.yunkang.saas.bootstrap.application.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceService;
import com.yunkang.saas.bootstrap.security.local.business.authorize.domain.SecurityAuthority;
import com.yunkang.saas.bootstrap.security.local.business.authorize.domain.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 登录成功后的处理
 * 1.捞到用户的所有权限
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private ResourceService resourceService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

        clearAuthenticationAttributes(request);

        SecurityUser userDetails = (SecurityUser)authentication.getPrincipal();

        List<Resource> resourceList = resourceService.findAllResourceByAccountId(userDetails.getAccount().getId());

        for(Resource resource : resourceList){
            userDetails.addAuthority(new SecurityAuthority(resource.getCode()+""));
        }
    }
}