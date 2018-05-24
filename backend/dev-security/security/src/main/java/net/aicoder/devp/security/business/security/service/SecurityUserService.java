package net.aicoder.devp.security.business.security.service;


import net.aicoder.devp.security.business.security.domain.Account;
import net.aicoder.devp.security.business.security.domain.SecurityUser;
import net.aicoder.devp.security.business.security.dto.AccountCondition;
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

	@Override
	public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {

		AccountCondition accountCondition = new AccountCondition();
		accountCondition.setName(username);
		Account account = accountService.findOne(accountCondition);
		if(account == null){
			throw new UsernameNotFoundException("用户名不存在");

		}

		return new SecurityUser(account, null);
	}

}
