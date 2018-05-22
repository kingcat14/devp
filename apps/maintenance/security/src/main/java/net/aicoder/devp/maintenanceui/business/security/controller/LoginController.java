package net.aicoder.devp.maintenanceui.business.security.controller;

import com.yunkang.saas.common.framework.exception.BusinessException;
import net.aicoder.devp.maintenanceui.business.security.controller.vo.*;
import net.aicoder.devp.maintenanceui.business.security.domain.SecurityUser;
import net.aicoder.devp.maintenanceui.business.security.service.AccountManageService;
import net.aicoder.devp.maintenanceui.business.security.service.LoginService;
import net.aicoder.devp.maintenanceui.business.security.domain.Account;
import net.aicoder.devp.maintenanceui.business.security.domain.Resource;
import net.aicoder.devp.maintenanceui.business.security.service.AccountService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
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

	@Value("${security.test}")
	private boolean inTest = false;

	/**
	 * 登陆
	 * @param loginRequest 登录请求
	 * @return
	 */
	@PostMapping("/authenticate")
	public LoginResult login(@Valid LoginRequest loginRequest, HttpSession session){

		LoginResult loginResult = loginService.login(loginRequest);

		loginResult.setSessionId(session.getId());

		LOGGER.debug("{}", loginRequest);

		return loginResult;

	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	@PostMapping("/account")
	public Account getAccount(){

		if(this.inTest){
			Account account = new Account();
			account.setName("测试中");
			account.setNickName("虚拟用户");
			return account;
		}

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();

		if(principal==null || !(principal instanceof SecurityUser)){
			throw new BusinessException("security", "account", "not login", "用户未登陆");

		}

		SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();

		Account account = userDetails.getAccount();

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
		request.setAccountId(account.getId()+"");
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

		boolean flag = false;

		if(flag) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
					.getAuthentication()
					.getPrincipal();

			String username = userDetails.getUsername();
			result = accountManageService.findResourceTree(username);
		}else{
			result = accountManageService.findAllResourceTree();
		}




		//return result;

		return this.convert(result);


	}

	private List<ResourceTreeNode> convert(List<Resource> resourceList){

		List<ResourceTreeNode> result = new ArrayList<>();

		for(Resource resource : resourceList){
			result.add(convert(resource, 0));
		}

		return result;
	}

	private ResourceTreeNode convert(Resource resource, int count){



		ResourceTreeNode resourceTreeNode = new ResourceTreeNode(resource);

		//深度超过10000,则直接返回
		if(count>10000){
			return resourceTreeNode;
		}

		if(CollectionUtils.isNotEmpty(resource.getChildren())){
			for(Resource childResource : resource.getChildren()){
				resourceTreeNode.addChild(this.convert(childResource, count++));
			}
		}
		return resourceTreeNode;
	}
}
