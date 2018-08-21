package com.yunkang.saas.security.local.business.authorize;

import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import com.yunkang.saas.platform.business.platform.security.domain.Account;
import com.yunkang.saas.security.local.business.authorize.domain.SecurityUser;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 获取登录用户的工具类
 */
@Service
public class SecurityUtilImpl implements SecurityUtil {

    public Account getAccount(){
        SecurityExpressionRoot root = null;

        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if(principal==null || !(principal instanceof SecurityUser)){
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
