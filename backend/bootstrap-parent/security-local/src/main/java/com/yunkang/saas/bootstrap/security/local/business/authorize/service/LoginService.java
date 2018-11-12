package com.yunkang.saas.bootstrap.security.local.business.authorize.service;


import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.account.domain.AccountPassword;
import com.yunkang.saas.bootstrap.platform.business.account.dto.AccountCondition;
import com.yunkang.saas.bootstrap.platform.business.account.service.AccountPasswordService;
import com.yunkang.saas.bootstrap.platform.business.account.service.AccountService;
import com.yunkang.saas.bootstrap.security.local.business.authorize.spring.SecurityUserService;
import com.yunkang.saas.bootstrap.security.model.LoginResult;
import com.yunkang.saas.bootstrap.security.local.business.authorize.domain.SecurityUser;
import com.yunkang.saas.bootstrap.security.model.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;

/**
 * Created by gonghongrui on 2017/1/12.
 */
@Service
public class LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountPasswordService accountPasswordService;

	@Autowired
	private SecurityUserService securityUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly=true)
	public LoginResult login(LoginRequest loginRequest){

		LoginResult loginResult = new LoginResult(false, "未进行认证操作");
		Date date1 = new Date();
		LOGGER.info("{}");
		//String password = passwordEncoder.encode(loginRequest.getPassword());

		Date date2 = new Date();
		LOGGER.info(" encode password cost:{}", date2.getTime() - date1.getTime());

		AccountCondition condition = new AccountCondition();
		condition.setAccountName(loginRequest.getUsername());


		Account account = accountService.findOne(condition);
		Date date3 = new Date();
		LOGGER.info(" find account cost:{}", date3.getTime() - date2.getTime());

		if(account==null){
			LoginResult result = new LoginResult(false, "账号有误");
			return result;
		}

		AccountPassword accountPassword = accountPasswordService.findForAccountId(account.getId());

		if(passwordEncoder.matches(loginRequest.getPassword(), accountPassword.getPassword())){

			SecurityUser userDetails = securityUserService.loadUserByUsername(loginRequest.getUsername());
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.<GrantedAuthority>emptyList());
			SecurityContextHolder.getContext().setAuthentication(token);
			loginResult = new LoginResult(true, "登录成功");

			//重置密码错误次数
			accountPassword.setWrongCount(0);
			accountPasswordService.merge(accountPassword);

		}
		else{
			LOGGER.info("{} login fail with password:{}", loginRequest.getUsername(), loginRequest.getPassword());
			loginResult = new LoginResult(false, "密码错误");

			//密码错误次数+1
			accountPassword.setWrongCount(accountPassword.getWrongCount()+1);
			accountPasswordService.merge(accountPassword);
		}
		Date date4 = new Date();
		LOGGER.info(" matche cost:{}", date4.getTime() - date3.getTime());
		return loginResult;

	}
}
