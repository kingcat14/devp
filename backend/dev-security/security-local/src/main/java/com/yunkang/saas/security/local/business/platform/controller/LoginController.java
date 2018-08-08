package com.yunkang.saas.security.local.business.platform.controller;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.security.service.business.platform.domain.Account;
import com.yunkang.saas.security.service.business.platform.domain.Resource;
import com.yunkang.saas.security.service.business.platform.domain.SecurityUser;
import com.yunkang.saas.security.service.business.platform.service.*;
import com.yunkang.saas.security.model.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 管理账号
 */
@RestController
@RequestMapping(value = "/security/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AccountManageService accountManageService;

	@Autowired
	private LoginService loginService;


	@Autowired
	private AccountService accountService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ResourceManageController resourceManageController;

	@Value("${security.basic.enabled:true}")
	private boolean notInTest = false;

	/**
	 * 登陆
	 * @param loginRequest 登录请求
	 * @return
	 */
	@PostMapping("/authenticate")
	public LoginResult login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session){

		LoginResult loginResult = loginService.login(loginRequest);

		loginResult.setSessionId(session.getId());

		LOGGER.debug("{}", loginResult);

		return loginResult;

	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	@PostMapping("/account")
	public Account getAccount(){


		Account account =  SecurityUtil.getAccount();

		if(account==null){
			if(this.notInTest){
				account = new Account();
				account.setName("测试中");
				account.setNickName("虚拟用户");
				return account;
			}else {
				throw new BusinessException("security", "account", "not login", "用户未登陆");
			}
		}

		return account;


	}




	/**
	 * 获取当前登录用户
	 * @return
	 */
	@PostMapping("/updatePassword")
	public UpdatePasswordResponse updatePassword(UpdatePasswordRequest request){

		SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();

		Account account = userDetails.getAccount();
		//把账号设置成当前登录用户的账号
		request.setAccountId(account.getId());
		UpdatePasswordResponse response = accountManageService.updatePassword(request);

		return response;


	}

	/**
	 * 获取可用资源
	 * @return
	 */
	@PostMapping("/getResource")
	public List<ResourceTreeNode> getResource(){

		List<Resource> result =  null;

		boolean flag = this.notInTest;

		if(flag) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
					.getAuthentication()
					.getPrincipal();

			String username = userDetails.getUsername();
			result = accountManageService.findAllResource(username);
		}else{
			result = resourceService.findAll(null);
		}

		//return result;

		return resourceManageController.convert(result);


	}





}
