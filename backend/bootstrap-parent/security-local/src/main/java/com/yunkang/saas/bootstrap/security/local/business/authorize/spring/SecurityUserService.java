package com.yunkang.saas.bootstrap.security.local.business.authorize.spring;


import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.account.domain.AccountPassword;
import com.yunkang.saas.bootstrap.platform.business.account.dto.AccountCondition;
import com.yunkang.saas.bootstrap.platform.business.account.service.AccountPasswordService;
import com.yunkang.saas.bootstrap.platform.business.account.service.AccountService;
import com.yunkang.saas.bootstrap.security.local.business.authorize.domain.SecurityAuthority;
import com.yunkang.saas.bootstrap.security.local.business.authorize.domain.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by gonghongrui on 2017/1/10.
 */
//@Service
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
		SecurityUser securityUser = new SecurityUser(account, accountPassword, null);
//		securityUser.addAuthority(new SecurityAuthority("ROLE_ACTUATOR"));

		if("actuator".equals(username)){
			securityUser.addAuthority(new SecurityAuthority("ACTUATOR"));
		}
		return securityUser;
	}

}
