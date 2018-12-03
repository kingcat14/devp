package com.yunkang.saas.platform.authentication.business.security.service;


import com.yunkang.saas.platform.authentication.business.platform.security.domain.Account;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountCondition;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountDTO;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.SecurityUser;
import com.yunkang.saas.platform.authentication.business.platform.security.service.AccountService;
import org.springframework.beans.BeanUtils;
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

	/**
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {

		AccountCondition accountCondition = new AccountCondition();
		accountCondition.setAccountName(username);
		//accountCondition.setDeleted(false);

		Account account = accountService.findOne(accountCondition);
		if(account == null){
			throw new UsernameNotFoundException("用户名不存在");
		}

		AccountDTO accountDTO = new AccountDTO();
		BeanUtils.copyProperties(account, accountDTO);
		SecurityUser securityUser = new SecurityUser(accountDTO);

		return securityUser;
	}

}
