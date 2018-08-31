package com.yunkang.saas.bootstrap.application.business.authorize;

import com.yunkang.saas.bootstrap.business.platform.security.domain.Account;

/**
 * 获取登录用户的工具类
 */
public interface SecurityUtil {

    Account getAccount();

}