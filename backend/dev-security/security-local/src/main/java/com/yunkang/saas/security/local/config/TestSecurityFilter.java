package com.yunkang.saas.security.local.config;


import com.yunkang.saas.security.local.business.domain.Account;
import com.yunkang.saas.security.local.business.domain.SecurityUser;
import com.yunkang.saas.security.local.business.service.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Collections;

@WebFilter(urlPatterns = "/**")
@Component
public class TestSecurityFilter implements Filter {

    @Value("${security.test:false}")
    private boolean inTest;

    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {


    }
  
    @Override  
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(inTest && SecurityUtil.getAccount() == null){
            this.initTestAccount();
        }
        filterChain.doFilter(servletRequest, servletResponse);  
    }  
  
    @Override  
    public void destroy() {  
  
    }

    private void initTestAccount(){
        Account account = new Account();
        account.setName("测试中");
        account.setNickName("虚拟用户");
        account.setId(1L);
        account.setTenantId(1L);

        SecurityUser userDetails = new SecurityUser(account, null);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.<GrantedAuthority>emptyList());
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}  