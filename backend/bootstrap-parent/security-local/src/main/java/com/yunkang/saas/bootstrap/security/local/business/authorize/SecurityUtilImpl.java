package com.yunkang.saas.bootstrap.security.local.business.authorize;

import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;
import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.security.local.business.authorize.domain.SecurityUser;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 获取登录用户的工具类
 */
@Service
public class SecurityUtilImpl implements SecurityUtil {

    public Account getAccount(){
        SecurityExpressionRoot root = null;

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof SecurityUser)){
            //throw new BusinessException("security", "account", "not login", "用户未登陆");
           return null;
        }

        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Account account = userDetails.getAccount();

        return account;

    }
}
