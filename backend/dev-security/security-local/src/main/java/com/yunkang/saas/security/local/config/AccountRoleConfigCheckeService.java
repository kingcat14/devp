package com.yunkang.saas.security.local.config;

import com.yunkang.saas.security.service.business.platform.domain.*;
import com.yunkang.saas.security.service.business.platform.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 检查角色的初始化配置
 */
@Component
@Order(value=1)
public class AccountRoleConfigCheckeService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRoleConfigCheckeService.class);

	@Autowired
    private AccountService accountService;

	@Autowired
	private AccountPasswordService accountPasswordService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountRoleRelationService accountRoleRelationService;


	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

    	Role role =new Role();
    	role.setId(1L);
    	role.setName("超级管理员");
		role.setTenantId(1L);
		role.setDescription("超级管理员");
		checkRole(role);

		Account account = new Account();
		account.setId(1L);
		account.setTenantId(1L);
		account.setAccountName("admin");
		account.setName("超级管理员");
		account.setEnable("true");
		check(account);

		AccountPassword accountPassword = new AccountPassword();
		accountPassword.setId(1L);
		accountPassword.setAccountName("admin");
		accountPassword.setTenantId(1L);
		accountPassword.setAccountId(1L);
		//默认123456
		accountPassword.setPassword(passwordEncoder.encode("123456"));
		check(accountPassword);


		AccountRoleRelation accountRoleRelation = new AccountRoleRelation();
		accountRoleRelation.setAccountId(1L);
		accountRoleRelation.setRoleId(1L);
		accountRoleRelation.setId(1L);
		check(accountRoleRelation);


    }

    private void checkRole(Role target){
	    LOGGER.info("[check role]:{}", target);
		Role entity = roleService.find(target.getId());
    	if(entity == null){
			roleService.merge(target);
	    }
    }

	private void check(Account target){
		LOGGER.info("[check]:{}", target);
		Account entity = accountService.find(target.getId());
		if(entity == null){
			accountService.merge(target);
		}
	}

	private void check(AccountPassword target){
		LOGGER.info("[check]:{}", target);
		AccountPassword entity = accountPasswordService.find(target.getId());
		if(entity == null){
			accountPasswordService.merge(target);
		}
	}
	private void check(AccountRoleRelation target){
		LOGGER.info("[check]:{}", target);
		AccountRoleRelation entity = accountRoleRelationService.find(target.getId());
		if(entity == null){
			accountRoleRelationService.merge(target);
		}
	}
}