package com.yunkang.saas.security.service.business.platform.service;

import com.yunkang.saas.security.service.business.platform.domain.Account;
import com.yunkang.saas.security.service.business.platform.domain.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取登录用户的工具类
 */
public class SecurityUtil {

    public static Account getAccount(){

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
