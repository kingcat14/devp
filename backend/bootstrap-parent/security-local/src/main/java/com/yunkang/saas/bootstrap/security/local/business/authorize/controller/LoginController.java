package com.yunkang.saas.bootstrap.security.local.business.authorize.controller;

import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;
import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.account.dto.UpdatePasswordRequest;
import com.yunkang.saas.bootstrap.platform.business.account.dto.UpdatePasswordResponse;
import com.yunkang.saas.bootstrap.platform.business.account.service.AccountManageService;
import com.yunkang.saas.bootstrap.application.business.resource.vo.ResourceTreeNode;
import com.yunkang.saas.bootstrap.application.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceService;
import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceUtil;
import com.yunkang.saas.bootstrap.platform.business.application.domain.App;
import com.yunkang.saas.bootstrap.platform.business.application.vo.AppVO;
import com.yunkang.saas.bootstrap.platform.business.tenant.domain.Tenant;
import com.yunkang.saas.bootstrap.platform.business.tenant.service.TenantService;
import com.yunkang.saas.bootstrap.platform.business.tenant.vo.TenantVO;
import com.yunkang.saas.bootstrap.security.model.LoginResult;
import com.yunkang.saas.bootstrap.security.local.business.authorize.domain.SecurityUser;
import com.yunkang.saas.bootstrap.security.local.business.authorize.service.LoginService;
import com.yunkang.saas.bootstrap.security.model.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	private ResourceService resourceService;

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
	 * 修改当前用户密码
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
	public List<ResourceTreeNode> getResource(@AuthenticationPrincipal SecurityUser securityUser){

		Account account = securityUser.getAccount();

		List<Resource> result = resourceService.findAllResourceByAccountId(account.getId());

		//如果是测试用户, 则返回所有资源
		if(account.getId() == -1){
			result = resourceService.findAll(null);
		}

		//整理成树形结构
		return ResourceUtil.convert(result);

	}

}
