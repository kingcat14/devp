package com.yunkang.saas.platform.manage.startcheck;

import com.yunkang.saas.bootstrap.application.business.security.domain.AccountRoleRelation;
import com.yunkang.saas.bootstrap.application.business.security.domain.Role;
import com.yunkang.saas.bootstrap.application.business.security.service.AccountRoleRelationService;
import com.yunkang.saas.bootstrap.application.business.security.service.RoleService;
import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.account.domain.AccountPassword;
import com.yunkang.saas.bootstrap.platform.business.account.service.AccountPasswordService;
import com.yunkang.saas.bootstrap.platform.business.account.service.AccountService;
import com.yunkang.saas.common.framework.app.ApplicationProperties;
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
public class MonitorAccountRoleConfigCheckService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorAccountRoleConfigCheckService.class);

	private static final Long ACTUATOR_ID = 2L;

	@Autowired
	private ApplicationProperties applicationProperties;

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

		Account account = new Account();
		account.setId(ACTUATOR_ID);
		account.setTid(1L);
		account.setAccountName("actuator");
		account.setName("监控账户");
		account.setEnable(true);
		check(account);

		AccountPassword accountPassword = new AccountPassword();
		accountPassword.setId(ACTUATOR_ID);
		accountPassword.setAccountName("actuator");
		accountPassword.setTid(1L);
		accountPassword.setAccountId(ACTUATOR_ID);
		//默认123456
		accountPassword.setPassword(passwordEncoder.encode("123456"));
		check(accountPassword);


		checkRole();

		AccountRoleRelation accountRoleRelation = new AccountRoleRelation();
		accountRoleRelation.setAccountId(ACTUATOR_ID);
		accountRoleRelation.setRoleId(ACTUATOR_ID);
		accountRoleRelation.setId(ACTUATOR_ID);
		check(accountRoleRelation);

    }

	private void check(Role target){
		LOGGER.info("[check role]:{}", target);
		Role entity = roleService.find(target.getId());
		if(entity == null){
			roleService.merge(target);
		}
	}

	public void checkRole(){
		Role role =new Role();
		role.setId(ACTUATOR_ID);
		role.setName("监控账号");
		role.setCode("ACTUATOR");
		role.setTid(1L);
		role.setAppCode(applicationProperties.getCode()+"");
		role.setDescription("监控账号");
		check(role);
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