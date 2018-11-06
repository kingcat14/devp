package com.yunkang.saas.bootstrap.platform.business.account.service;


import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.account.domain.AccountPassword;
import com.yunkang.saas.bootstrap.platform.business.account.dto.AccountPasswordCondition;
import com.yunkang.saas.bootstrap.platform.business.account.dto.UpdatePasswordRequest;
import com.yunkang.saas.bootstrap.platform.business.account.dto.UpdatePasswordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gonghongrui on 2017/7/13.
 */
@Service
public class AccountManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountManageService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountPasswordService accountPasswordService;
	
	@Autowired
	private AccountService accountService;


	@Transactional
	public void add(Account account, AccountPassword accountPassword){
		accountService.add(account);


		accountPassword.setAccountId(account.getId());
		accountPassword.setPassword(passwordEncoder.encode(accountPassword.getPassword()));
		accountPassword.setWrongCount(0);
		accountPasswordService.add(accountPassword);
	}

	public void delete(Long accountId){
		if(accountId == null){
			return;
		}
		accountService.delete(accountId);
		AccountPassword accountPassword = accountPasswordService.findForAccountId(accountId);
		if(accountPassword != null) {
			accountPasswordService.delete(accountPassword.getId());
		}
	}

	/**
	 * 更新密码
	 * @param updatePasswordRequest
	 * @return
	 */
	public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest){

		UpdatePasswordResponse response = new UpdatePasswordResponse();

		AccountPasswordCondition accountPasswordCondition = new AccountPasswordCondition();
		accountPasswordCondition.setAccountId(updatePasswordRequest.getAccountId());
		AccountPassword accountPassword = accountPasswordService.findOne(accountPasswordCondition);

		if(accountPassword == null){
			response.setSuccess(false);
			response.setMessage("找不到当前用户");
			LOGGER.error("can not find AccountPassword for accountId: {}", updatePasswordRequest.getAccountId());
			return response;
		}

		if(!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), accountPassword.getPassword())){
			response.setSuccess(false);
			response.setMessage("旧密码不匹配");
			return response;
		}

		accountPassword.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
		accountPassword.setWrongCount(0);
		accountPasswordService.merge(accountPassword);


		response.setSuccess(true);
		response.setMessage("密码已更新");
		return response;
	}





}
