package com.yunkang.saas.security.local.business.authorize.service;


import com.yunkang.saas.platform.business.platform.security.domain.Account;
import com.yunkang.saas.platform.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.platform.business.platform.security.dto.AccountCondition;
import com.yunkang.saas.platform.business.platform.security.service.AccountPasswordService;
import com.yunkang.saas.platform.business.platform.security.service.AccountService;

import com.yunkang.saas.security.local.business.authorize.domain.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by gonghongrui on 2017/1/10.
 */
@Service
public class SecurityUserService implements UserDetailsService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountPasswordService accountPasswordService;

	/**
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {

		AccountCondition accountCondition = new AccountCondition();
		accountCondition.setAccountName(username);
		Account account = accountService.findOne(accountCondition);
		if(account == null){
			throw new UsernameNotFoundException("用户名不存在");
		}

		AccountPassword accountPassword = accountPasswordService.findForAccountId(account.getId());
		return new SecurityUser(account, accountPassword, null);
	}

}
